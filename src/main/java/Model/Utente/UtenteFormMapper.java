package Model.Utente;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

public class UtenteFormMapper {
    public Utente map (HttpServletRequest request, boolean bool,int id) throws NoSuchAlgorithmException {
        Utente utente = new Utente();
        utente.setId(id);
        utente.setNome(request.getParameter("nome"));
        utente.setCognome(request.getParameter("cognome"));
        utente.setEmail(request.getParameter("email"));
        utente.setPassword(request.getParameter("PW"));
        utente.setIsAdministration(bool);
        return utente;
    }
}
