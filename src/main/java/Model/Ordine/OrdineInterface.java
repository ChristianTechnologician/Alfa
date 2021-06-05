package Model.Ordine;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OrdineInterface {

    List<Ordine> DoRetriveAll (int start, int end) throws SQLException;
    List<Ordine> DoRetriveByData (LocalDate data) throws SQLException;
    List<Ordine> DoRetriveByUtente (int id_utente) throws SQLException;
    Ordine DoRetriveByNumeroFattura (int numero_fattura) throws SQLException;
}
