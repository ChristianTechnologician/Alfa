package Model.Utente;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UtenteInterface {

    List<Utente> fetchUtenti(int start, int end) throws SQLException;

    Optional<Utente> fetchUtente(String email) throws SQLException;

    Integer createUtente(Utente utente) throws SQLException;

    Integer updateUtente(Utente utente) throws SQLException;

    Integer deleteUtente(String email) throws SQLException;
}
