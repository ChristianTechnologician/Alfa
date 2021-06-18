package Model.Utente;

import Model.Gestione.Paginatore;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UtenteInterface {

    List<Utente> fetchUtenti(Paginatore paginatore) throws SQLException;

    Optional<Utente> fetchUtente(String email) throws SQLException;

    Integer createUtente(Utente utente) throws SQLException;

    Integer updateUtente(Utente utente) throws SQLException;

    Integer deleteUtente(String email) throws SQLException;

    Optional <Utente> findUtente(String email, String password, boolean admin) throws SQLException;

    int countAll() throws SQLException;
}
