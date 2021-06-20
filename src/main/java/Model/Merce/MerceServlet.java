package Model.Merce;

import Model.Colore.Colore;
import Model.Colore.ColoreDAO;
import Model.Gestione.Controller;
import Model.Gestione.InvalidRequestException;
import Model.Gestione.Paginatore;
import Model.Search.Condition;
import Model.Search.MerceSearch;
import Model.Taglia.Taglia;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MerceServlet", value = "/merce/*")
@MultipartConfig
public class MerceServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/insertMerce":
                    request.getRequestDispatcher(view("crm/insertMerce")).forward(request, response);
                    break;
                case "/updateMerce":
                    request.getRequestDispatcher(view("crm/updateMerce")).forward(request, response);
                    break;
                case "/deleteMerce":
                    request.getRequestDispatcher(view("crm/deleteMerce")).forward(request, response);
                    break;
                case "/search":
                    MerceDAO md = new MerceDAO();
                    List<Condition> conditions = new MerceSearch().buildSearch(request);
                    List<Merce> searchMerci = conditions.isEmpty() ?
                            md.fetchProductsWithRelations(new Paginatore(1, 50)) :
                            md.search(conditions);
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
        /*catch (InvalidRequestException e){
            log(e.getMessage());
            e.handle(request,response);
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/create":
                    MerceDAO merceDao = new MerceDAO();
                    Merce merce = new Merce();
                    merce.setCodice(request.getParameter("Codice"));
                    merce.setNome(request.getParameter("Nome"));
                    merce.setDescrizione(request.getParameter("Descrizione"));
                    merce.setGenere(request.getParameter("Genere"));
                    merce.setPrezzo(Double.parseDouble(request.getParameter("Prezzo")));
                    merce.setTipocategoria(request.getParameter("TipoCategoria"));
                    merce.setSconto(Double.parseDouble(request.getParameter("Sconto")));
                    Taglia taglia = new Taglia();
                    taglia.setlTaglia(request.getParameter("LTaglia"));
                    Colore colore = new Colore();
                    colore.setCod(Integer.parseInt(request.getParameter("Cod")));
                    ColoreDAO coloreDAO = new ColoreDAO();
                    int quantita = Integer.parseInt(request.getParameter("Quantita"));
                    Part filePart = request.getPart("upImg");
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    merce.setUpImg(fileName);
                    if (merceDao.insertMerce(merce)){// && fornituraDAO.insertQuantita(quantita)/* && coloreDAO.insertColor(colore.getCod(),colore.getTipoColore()) && tagliaDao.*/) {
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                        String uploadRoot = getUploadPath();
                        merce.writeCover(uploadRoot, filePart);
                    } else {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore Server");
                    }
                    break;
                case "/update":
                    break;
                default:
                    break;
                }
            } catch (SQLException ex){
            log(ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        } /*catch (InvalidRequestException e){
            log(e.getMessage());
            e.handle(request,response);
        }*/
    }
}