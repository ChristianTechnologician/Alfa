package AlfaProject.Alfa;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/research-servlet")
public class ResearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputRicerca = request.getParameter("keyword");
        switch(inputRicerca) {
            case "catalogo":
               RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
               dispatcher.forward(request, response);break;
            case "home":RequestDispatcher dispatcher1 = request.getRequestDispatcher("HomePage.html");
                dispatcher1.forward(request, response);break;
            default : RequestDispatcher dispatcher2 = request.getRequestDispatcher("/WEB-INF/index.jsp");
                dispatcher2.forward(request,response);break;
        }
    }
}