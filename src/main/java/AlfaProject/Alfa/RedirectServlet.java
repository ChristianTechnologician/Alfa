package AlfaProject.Alfa;

import Model.Abbigliamento;
import Model.WearRetriving;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/RedirectServlet")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uomo = request.getParameter("redirect_uomo");
        String donna = request.getParameter("redirect_donna");
        if(uomo != null){
            List<Abbigliamento> a = new ArrayList<>();
            WearRetriving ab = new WearRetriving();
            a = ab.doRetrieveAll("M");
            request.setAttribute("generale",a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
        if(donna != null){
            List<Abbigliamento> a = new ArrayList<>();
            WearRetriving ab = new WearRetriving();
            a = ab.doRetrieveAll("F");
            request.setAttribute("generale",a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
    }
}
