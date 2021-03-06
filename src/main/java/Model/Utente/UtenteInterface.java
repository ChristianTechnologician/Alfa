package Model.Utente;

import Model.Gestione.Paginatore;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UtenteInterface {
    List<Utente> fetchUtenti(Paginatore paginatore) throws SQLException;
    List<Utente> fetchAdmin() throws SQLException;
    Utente fetchUtente(String email) throws SQLException;
    Boolean createUtente(Utente utente) throws SQLException;
    Boolean updateUtente(Utente utente) throws SQLException;
    Boolean deleteUtente(int id) throws SQLException;
    Utente loginUtente(String email, String password, boolean admin) throws SQLException;
    int countAll() throws SQLException;
    Optional<Utente> fetchUtente(int id) throws SQLException;
}
