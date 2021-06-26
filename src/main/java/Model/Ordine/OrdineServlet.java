package Model.Ordine;

import Model.Gestione.Controller;
import Model.Gestione.InvalidRequestException;
import Model.Gestione.Paginatore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrdineServlet", value = "/ordine/*")
public class OrdineServlet extends Controller {
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        try {
            String path = getPath(request);
            switch (path) {
                case "/ordine":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher(view("crm/ordine")).forward(request,response);
                    break;
                case "/mostraOrdini":
                    authorize(request.getSession(false));
                    OrdineDAO ordineDAO = new OrdineDAO();
                    List<Ordine> ordineL = new ArrayList<>();
                    Paginatore paginatore= new Paginatore(1,30);
                    ordineL = ordineDAO.DoRetriveAll(paginatore);
                    request.setAttribute("ordini",ordineL);
                    request.getRequestDispatcher(view("crm/ordini")).forward(request,response);
                    break;
                case "/scegliOrdine":
                    authorize(request.getSession(false));
                    String numeroFattura = (String)request.getParameter("nFattura");
                    OrdineDAO odao = new OrdineDAO();
                    Ordine ordine = new Ordine();
                    System.out.println(numeroFattura);
                    ordine = odao.DoRetriveByNumeroFattura(Integer.parseInt(numeroFattura));
                    request.setAttribute("ordine",ordine);
                    request.getRequestDispatcher(view("crm/dettaglioOrdine")).forward(request,response);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            log(ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (InvalidRequestException e){
            log(e.getMessage());
            e.handle(request,response);
        }
    }

}

