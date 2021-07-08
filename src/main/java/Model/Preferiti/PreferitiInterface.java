package Model.Preferiti;

import Model.Ordine.Ordine;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface PreferitiInterface {
    List<Preferiti> DoRetriveAll () throws SQLException;
    List<Preferiti> DoRetriveByUtente (int id_utente) throws SQLException;
    List<String> DoRetriveCodiciByUtente(int id_utente) throws SQLException;
    boolean insertPreferiti(int id_utente, int numero,String codice) throws SQLException;
    void deletePreferiti(int id_utente) throws SQLException;
}
