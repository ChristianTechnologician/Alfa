package Model.Fornitura;

import Model.Colore.Colore;
import Model.Colore.ColoreDAO;
import Model.Gestione.Controller;
import Model.Gestione.InvalidRequestException;
import Model.Gestione.Paginatore;
import Model.Merce.Merce;
import Model.Merce.MerceDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "FornituraServlet", value = "/fornitura/*")
    public class FornituraServlet extends Controller {
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                String path = getPath(request);
                switch (path) {
                    case "/inserisci":
                        authorize(request.getSession(false));
                        String Codice=request.getParameter("Codice");
                        String Colore=request.getParameter("Colore");
                        String Taglia=request.getParameter("Taglia");
                        String Quantita=request.getParameter("Quantita");
                        Fornitura f=new Fornitura();
                        FornituraDAO fd=new FornituraDAO();
                        Colore colore=new Colore();
                        ColoreDAO coloreDAO=new ColoreDAO();
                        colore.setTipoColore(Colore);
                        if (coloreDAO.doRetrieveByType(colore.getTipoColore())==null) {
                            coloreDAO.insertColor(colore.getTipoColore());
                        }
                        System.out.println("0000");
                        colore=coloreDAO.doRetrieveByType(colore.getTipoColore());
                        f.setCodMerce(Codice);
                        f.setlTaglia(Taglia);
                        f.setQuantita(Integer.parseInt(Quantita));
                        f.setCodColore(colore.getCod());
                        System.out.println("0000");
                        fd.insertFornitura(f);
                        System.out.println("aaaaa");
                        List<Merce> listas=new ArrayList<>();
                        MerceDAO mdoo=new MerceDAO();
                        Paginatore pgg=new Paginatore(1, 50);
                        listas=mdoo.fetchProductsWithRelations(pgg);
                        request.setAttribute("merce", listas);
                        request.getRequestDispatcher(view("crm/merce")).forward(request, response);
                        break;

                    default:
                        break;
                }
            }catch (SQLException ex) {
                log(ex.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
            }
            catch (InvalidRequestException e){
                log(e.getMessage());
                e.handle(request,response);
            }
        }
}
