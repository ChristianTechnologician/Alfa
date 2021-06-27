package Controller;

import Model.Merce.Merce;
import Model.Merce.MerceDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DettaglioServlet", value = "/DettaglioServlet")
public class DettaglioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codice = request.getParameter("prodotto");
        MerceDAO wr = new MerceDAO();
        Merce ab = new Merce();
        try {
            ab = wr.doRetrieveByCode(codice);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("prodotto",ab);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Dettaglio.jsp");
        dispatcher.forward(request,response);
    }

}
