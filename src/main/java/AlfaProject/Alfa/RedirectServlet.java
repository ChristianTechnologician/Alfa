package AlfaProject.Alfa;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RedirectServlet", value = "/RedirectServlet")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uomo = request.getParameter("redirect_uomo");
        String donna = request.getParameter("redirect_donna");
        if(uomo != null){
            request.setAttribute("#uomo",uomo);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
        if(donna != null){
            request.setAttribute("#donna",donna);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
    }
}
