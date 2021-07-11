package Controller;

import Model.Colore.Colore;
import Model.Colore.ColoreDAO;
import Model.Fornitura.Fornitura;
import Model.Fornitura.FornituraDAO;
import Model.Merce.Merce;
import Model.Merce.MerceDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DettaglioServlet", value = "/DettaglioServlet")
public class DettaglioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codice = request.getParameter("prodotto");
        MerceDAO wr = new MerceDAO();
        Merce ab = new Merce();
        List<Fornitura> f = new ArrayList<>();
        FornituraDAO fornituraDAO = new FornituraDAO();
        List<Colore> c = new ArrayList<>();
        ColoreDAO coloreDAO = new ColoreDAO();
        try {
            c=coloreDAO.doRetrieveAll();
            f=fornituraDAO.doRetrieveByCode(codice);
            ab = wr.doRetrieveByCode(codice);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("prodotto",ab);
        request.setAttribute("forniture",f);
        request.setAttribute("colori",c);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Dettaglio.jsp");
        dispatcher.forward(request,response);
    }

}
