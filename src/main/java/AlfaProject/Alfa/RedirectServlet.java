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
        String abitiUomo = request.getParameter("redirect_uomo_abiti");
        String abitiDonna = request.getParameter("redirect_donna_abiti");
        String giaccheUomo = request.getParameter("redirect_uomo_giacche");

        String giaccheDonna = request.getParameter("redirect_donna_giacche");
        if (uomo != null) {
            List<Abbigliamento> a = new ArrayList<>();
            WearRetriving ab = new WearRetriving();
            a = ab.doRetrieveAll("M");
            request.setAttribute("generale", a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
        if (abitiUomo != null) {
            List<Abbigliamento> a = new ArrayList<>();
            WearRetriving ab = new WearRetriving();
            a = ab.doRetrieveAllbyType("M", "Completo");
            request.setAttribute("generale", a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
        if (giaccheUomo != null) {
            List<Abbigliamento> a = new ArrayList<>();
            WearRetriving ab = new WearRetriving();
            a = ab.doRetrieveAllbyType("M", "Cappotto");
            request.setAttribute("generale", a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
        if (donna != null) {
            List<Abbigliamento> a = new ArrayList<>();
            WearRetriving ab = new WearRetriving();
            a = ab.doRetrieveAll("F");
            request.setAttribute("generale", a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
        if (abitiDonna != null) {
            List<Abbigliamento> a = new ArrayList<>();
            WearRetriving ab = new WearRetriving();
            a = ab.doRetrieveAllbyType("F", "Completo");
            request.setAttribute("generale", a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
        if (giaccheDonna != null) {
            List<Abbigliamento> a = new ArrayList<>();
            WearRetriving ab = new WearRetriving();
            a = ab.doRetrieveAllbyType("F", "Cappotto");
            request.setAttribute("generale", a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
    }
}