package Model.Merce;

import Model.Colore.Colore;
import Model.Colore.ColoreDAO;
import Model.Fornitura.Fornitura;
import Model.Fornitura.FornituraDAO;
import Model.Gestione.Controller;
import Model.Gestione.InvalidRequestException;
import Model.Gestione.Paginatore;
import Model.Search.Condition;
import Model.Search.MerceSearch;
import Model.Search.Query;
import Model.Taglia.Taglia;
import Model.Taglia.TagliaDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MerceServlet", value = "/merce/*")
@MultipartConfig
public class MerceServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/merce":
                    authorize(request.getSession(false));
                    HttpSession merceSession = request.getSession();
                    List<Merce> lista=new ArrayList<>();
                    MerceDAO mdo=new MerceDAO();
                    Paginatore pg=new Paginatore(1, 50);
                    lista=mdo.fetchProductsWithRelations(pg);
                    request.setAttribute("merce", lista);
                    request.getRequestDispatcher(view("crm/merce")).forward(request,response);
                    break;
                case "/dettaglio":
                    authorize(request.getSession(false));
                    //String codice = (String)request.getSession().getAttribute("id");
                    String codice=request.getParameter("id");
                    MerceDAO mercedao=new MerceDAO();
                    FornituraDAO fdo=new FornituraDAO();
                    Merce merce = mercedao.doRetrieveByCode(codice);
                    List<Fornitura> fornituras = fdo.doRetrieveByCode(codice);
                    ColoreDAO cdao = new ColoreDAO();
                    List<Colore> colori = cdao.doRetrieveAll();
                    request.setAttribute("merce",merce);
                    request.setAttribute("fornitura",fornituras);
                    request.setAttribute("colore",colori);
                    request.getRequestDispatcher(view("crm/dettaglioMerce")).forward(request,response);
                    break;
                case "/insertMerce":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher(view("crm/insertMerce")).forward(request, response);
                    break;
                case "/updateMerce":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher(view("crm/updateMerce")).forward(request, response);
                    break;
                case "/deleteMerce":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher(view("crm/deleteMerce")).forward(request, response);
                    break;
                case "/search":
                    MerceDAO md = new MerceDAO();
                    List<Condition> conditions = new MerceSearch().buildSearch(request);
                    Query q = new Query();
                    List<String> query = q.selectQuery(conditions);
                    List<Merce> searchMerci = conditions.isEmpty() ?
                            md.fetchProductsWithRelations(new Paginatore(1, 50)) :
                            md.search(query);
                    request.setAttribute("merce", searchMerci);
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            log(ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
        catch (InvalidRequestException e){
            log(e.getMessage());
            e.handle(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
                switch (path) {
                    case "/create":
                        authorize(request.getSession(false));
                        MerceDAO merceDao = new MerceDAO();
                        Merce merce = new Merce();
                        merce.setCodice(request.getParameter("Codice"));
                        merce.setNome(request.getParameter("Nome"));
                        merce.setDescrizione(request.getParameter("Descrizione"));
                        merce.setGenere(request.getParameter("Genere"));
                        merce.setPrezzo(Double.parseDouble(request.getParameter("Prezzo")));
                        merce.setTipocategoria(request.getParameter("TipoCategoria"));
                        merce.setSconto(Double.parseDouble(request.getParameter("Sconto")));
                        System.out.println(merce.getCodice());
                        Taglia taglia = new Taglia();
                        taglia.setlTaglia(request.getParameter("Taglia"));
                        TagliaDAO tagliaDAO = new TagliaDAO();
                        System.out.println(taglia.getlTaglia());
                        Colore colore = new Colore();
                        colore.setCod(Integer.parseInt(request.getParameter("Colore")));
                        //colore.setTipoColore(request.getParameter("TipoColore"));
                        ColoreDAO coloreDAO = new ColoreDAO();
                        System.out.println(colore.getCod());
                        int quantita = Integer.parseInt(request.getParameter("Quantita"));
                        Fornitura f = new Fornitura();
                        FornituraDAO fornituraDAO = new FornituraDAO();
                        f.setQuantita(quantita);
                        f.setlTaglia(taglia.getlTaglia());
                        f.setCodColore(colore.getCod());
                        f.setCodMerce(merce.getCodice());
                        //Part filePart = request.getPart("upImg");
                        //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                        //merce.setUpImg(fileName);
                        if (coloreDAO.doRetrieveByCode(colore.getCod()) == null) {
                            coloreDAO.insertColor(colore.getCod(), colore.getTipoColore());
                        }
                        if (!tagliaDAO.checkTaglia(taglia.getlTaglia())) {
                            tagliaDAO.insertTaglia(taglia.getlTaglia());
                        }
                        if (merceDao.insertMerce(merce) && fornituraDAO.insertFornitura(f)) {
                            request.getRequestDispatcher("crm/resultInsert").forward(request, response);
                            String uploadRoot = getUploadPath();
                            //merce.writeCover(uploadRoot, filePart);
                        } else {
                            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore Server");
                        }
                        break;
                    case "/update":
                        authorize(request.getSession(false));
                        MerceDAO md = new MerceDAO();
                        Merce m = new Merce();
                        m.setCodice(request.getParameter("Codice"));
                        m.setNome(request.getParameter("Nome"));
                        m.setDescrizione(request.getParameter("Descrizione"));
                        m.setGenere(request.getParameter("Genere"));
                        m.setPrezzo(Double.parseDouble(request.getParameter("Prezzo")));
                        m.setTipocategoria(request.getParameter("TipoCategoria"));
                        m.setSconto(Double.parseDouble(request.getParameter("Sconto")));
                        Taglia t = new Taglia();
                        t.setlTaglia(request.getParameter("LTaglia"));
                        TagliaDAO td = new TagliaDAO();
                        Colore c = new Colore();
                        c.setCod(Integer.parseInt(request.getParameter("Cod")));
                        c.setTipoColore(request.getParameter("TipoColore"));
                        ColoreDAO cd = new ColoreDAO();
                        int q = Integer.parseInt(request.getParameter("Quantita"));
                        Fornitura fornitura = new Fornitura();
                        FornituraDAO fd = new FornituraDAO();
                        fornitura.setQuantita(q);
                        fornitura.setlTaglia(t.getlTaglia());
                        fornitura.setCodColore(c.getCod());
                        fornitura.setCodMerce(m.getCodice());
                        Part fp = request.getPart("upImg");
                        String fname = Paths.get(fp.getSubmittedFileName()).getFileName().toString();
                        m.setUpImg(fname);
                        if (md.updateMerce(m.getCodice(), m) && fd.updateFornitura(m.getCodice(), fornitura)) {
                            request.getRequestDispatcher("/index.jsp").forward(request, response);
                            String uploadRoot = getUploadPath();
                            m.writeCover(uploadRoot, fp);
                        } else {
                            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore Server");
                        }
                        break;
                    case "/deleteMerce":
                        authorize(request.getSession(false));
                        String idMerce=request.getParameter("IdMerce");
                        MerceDAO mdd=new MerceDAO();
                        FornituraDAO fdd=new FornituraDAO();
                        boolean df= fdd.deleteFornitura(idMerce);
                        boolean delete=mdd.deleteMerce(idMerce);
                        request.setAttribute("deletem", delete);
                        request.setAttribute("deletef", df);
                        request.getRequestDispatcher(view("crm/resultDelete")).forward(request, response);
                        break;
                    default:
                        break;
                }
            } catch (SQLException ex) {
                log(ex.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
            }catch (InvalidRequestException e) {
                log(e.getMessage());
                e.handle(request, response);
            }
        }

}