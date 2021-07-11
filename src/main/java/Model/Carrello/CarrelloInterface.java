package Model.Carrello;

import Model.Gestione.Paginatore;
import Model.Ordine.Ordine;

import java.sql.SQLException;
import java.util.List;

public interface CarrelloInterface
{
    List<Carrello> DoRetriveAll() throws SQLException;
    List<Carrello> DoRetrieveByUtente(int id) throws SQLException;
    List<String> DoRetrieveCodici(int id) throws SQLException;
    List<Integer> DoRetrieveQuantita(int id) throws SQLException;
    List<Integer> DoRetrieveFcodice(int id) throws SQLException;
    Carrello findElement(int id,String Mcodice,int Fcodice) throws SQLException;
    boolean updateQuantita(int quantita,int id,String Mcodice,int Fcodice) throws SQLException;
    boolean insertElemento(int id,String Mcodice,int Fcodice,int quantita) throws SQLException;
    void deleteCarrello(int id) throws SQLException;
    boolean deleteElementoCarrello(String codice,int id,int fcodice) throws SQLException;
}
