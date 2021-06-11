package Model.Ordine;

import Model.Gestione.Paginatore;
import Model.Merce.Merce;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OrdineInterface {

    List<Ordine> DoRetriveAll (Paginatore paginatore) throws SQLException;
    List<Ordine> DoRetriveByData (LocalDate data) throws SQLException;
    List<Ordine> DoRetriveByUtente (int id_utente) throws SQLException;
    Ordine DoRetriveByNumeroFattura (int numero_fattura) throws SQLException;
    void insertMerce(Ordine ordine) throws SQLException;
    void deleteMerce(int numeroFattura) throws SQLException;
}
