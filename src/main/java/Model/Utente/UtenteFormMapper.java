package Model.Utente;

import javax.servlet.http.HttpServletRequest;

public class UtenteFormMapper {
    public Utente map (HttpServletRequest request, boolean bool){
        Utente utente = new Utente();
        utente.setNome(request.getParameter("nome"));
        utente.setCognome(request.getParameter("cognome"));
        utente.setEmail(request.getParameter("email"));
        utente.setIsAdministration(bool);
        return utente;
    }
}
