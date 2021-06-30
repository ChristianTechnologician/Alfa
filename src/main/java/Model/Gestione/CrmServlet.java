package Model.Gestione;

import Model.Merce.MerceDAO;
import Model.Ordine.OrdineDAO;
import Model.Utente.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "CrmServlet", value = "/crm/*")
public class CrmServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/home":
                    System.out.println("0");
                    authorize(request.getSession(false));
                    System.out.println("1");
                    UtenteDAO ud = new UtenteDAO();
                    MerceDAO merceDAO = new MerceDAO();
                    OrdineDAO ordineDAO = new OrdineDAO();
                    System.out.println("ok");
                    int merce = merceDAO.countAll();
                    System.out.println("ok");
                    int ordini = ordineDAO.countAll();
                    System.out.println("ok");
                    int utenti = ud.countAll();
                    System.out.println("ok");
                    request.setAttribute("merce",merce);
                    request.setAttribute("ordini",ordini);
                    request.setAttribute("utenti",utenti);
                    request.getRequestDispatcher(view("crm/home")).forward(request, response);
                    break;
                case "/merce":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher(view("crm/merce")).forward(request, response);
                    break;
                default:
                    System.out.println("04");
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Risorsa non trovata");
            }
        } catch (SQLException ex){
            log(ex.getMessage());
                    } catch (InvalidRequestException e) {
                        System.out.println("45");
                        log(e.getMessage());
                        e.handle(request, response);
                    }
            }
        }
