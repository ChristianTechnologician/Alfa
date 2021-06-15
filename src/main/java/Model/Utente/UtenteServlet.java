package Model.Utente;

import Model.Gestione.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UtenteServlet", value = "/utente/*")
public class UtenteServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request);//(request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path){
            case "/":
                request.getRequestDispatcher(view("crm/accounts")).forward(request, response);
                break;
            case "/signinCliente":
                request.getRequestDispatcher(view("account/loginform")).forward(request, response);//"site/signin"
                break;
            case "/create":
                request.getRequestDispatcher(view("crm/account")).forward(request, response);
                break;
            case "/show":
                request.getRequestDispatcher(view("crm/account")).forward(request, response);
                break;
            case "/signinAdmin":
               request.getRequestDispatcher(view("crm/secret")).forward(request, response);
                break;
            case "/signup":
                request.getRequestDispatcher(view("site/signup")).forward(request, response);
                break;
            case "/profile":
                request.getRequestDispatcher(view("site/profile")).forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND,"Risorsa non trovata");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path){
            case "/signinAdmin":
                break;
            case "/create":
                break;
            case "/update":
                break;
            case "/logout":
                break;
            case "/signupCliente":
                break;
            case "signinCliente":
                break;
            default:
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"Operazione non consentita");
        }
    }
}
