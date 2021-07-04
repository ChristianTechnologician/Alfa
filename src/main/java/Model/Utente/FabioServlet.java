package Model.Utente;

import Model.Gestione.Alert;
import Model.Gestione.Controller;
import Model.Gestione.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/FabioServlet")
public class FabioServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
                    //authorize(request.getSession(false));
                    System.out.println("Fabio è epico");
                    UtenteDAO udao = new UtenteDAO();
                    //request.setAttribute("back", view("crm/account"));
                    validate(UtenteValidator.validateForm(request, false));
                    //Utente utente = new UtenteFormMapper().map(request , false);
                    Utente utente = new Utente();
                    utente.setNome(request.getParameter("nome"));
                    utente.setNome(request.getParameter("cognome"));
                    utente.setNome(request.getParameter("email"));
                    utente.setPassword(request.getParameter("password"));
                    if (udao.createUtente(utente)) {
                        request.setAttribute("alert", new Alert(List.of("Account creato!"), "success"));
                        request.getRequestDispatcher("/WEB-INF/views/customer/user.jsp").forward(request, response);
                    } else {
                        internalError();
                    }
            } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InvalidRequestException invalidRequestException) {
            invalidRequestException.printStackTrace();
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
        }
    }
}