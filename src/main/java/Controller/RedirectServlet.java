package Controller;

import Model.Merce.Merce;
import Model.Merce.MerceDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
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
            List<Merce> a = new ArrayList<>();
            MerceDAO ab = new MerceDAO();
            try {
                a = ab.doRetrieveAllbyGender("M");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("generale", a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
        if (abitiUomo != null) {
            List<Merce> a = new ArrayList<>();
            MerceDAO ab = new MerceDAO();
            try {
                a = ab.doRetrieveAllbyType("M", "Completo");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("generale", a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
        if (giaccheUomo != null) {
            List<Merce> a = new ArrayList<>();
            MerceDAO ab = new MerceDAO();
            try {
                a = ab.doRetrieveAllbyType("M", "Cappotto");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("generale", a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
        if (donna != null) {
            List<Merce> a = new ArrayList<>();
            MerceDAO ab = new MerceDAO();
            try {
                a = ab.doRetrieveAllbyGender("F");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("generale", a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
        if (abitiDonna != null) {
            List<Merce> a = new ArrayList<>();
            MerceDAO ab = new MerceDAO();
            try {
                a = ab.doRetrieveAllbyType("F", "Completo");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("generale", a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
        if (giaccheDonna != null) {
            List<Merce> a = new ArrayList<>();
            MerceDAO ab = new MerceDAO();
            try {
                a = ab.doRetrieveAllbyType("F", "Cappotto");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("generale", a);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Catalogo.jsp");
            dispatcher.forward(request, response);
        }
    }
}