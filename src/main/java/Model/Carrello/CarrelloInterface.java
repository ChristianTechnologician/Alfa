package Model.Carrello;

import Model.Gestione.Paginatore;
import Model.Ordine.Ordine;

import java.sql.SQLException;
import java.util.List;

public interface CarrelloInterface
{
    List<Carrello> DoRetriveAll() throws SQLException;
    List<Carrello> DoRetrieveByUtente(int id) throws SQLException;
    void deleteCarrello(int id) throws SQLException;
}
