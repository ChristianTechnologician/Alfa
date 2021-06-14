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
        default:
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"Risorsa non trovata");
    }
    }
}
