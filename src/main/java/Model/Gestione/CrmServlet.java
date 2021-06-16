package Model.Gestione;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CrmServlet", value = "/crm/*")
public class CrmServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = getPath(request);
    switch (path){
        case "/dashboard":
            request.getRequestDispatcher(view("crm/home")).forward(request,response);
            break;
        case "/merce":
            request.getRequestDispatcher(view("crm/merce")).forward(request,response);
            break;
        case "/showMerce":
            request.getRequestDispatcher(view("crm/showMerce")).forward(request,response);
            break;
        case "/insertMerce":
            request.getRequestDispatcher(view("crm/insertMerce")).forward(request,response);
            break;
        case "/updateMerce":
            request.getRequestDispatcher(view("crm/updateMerce")).forward(request,response);
            break;
        case "/deleteMerce":
            request.getRequestDispatcher(view("crm/deleteMerce")).forward(request,response);
            break;
        default:
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"Risorsa non trovata");
    }
    }
}
