package Model.Fornitura;

import Model.Gestione.Paginatore;
import Model.Merce.Merce;

import java.sql.SQLException;
import java.util.List;

public interface FornituraInterface {
    List<Fornitura> doRetrieveByCode(String codice) throws SQLException;
    List<Fornitura> doRetrieveByUtenteCode(int id,String codice) throws SQLException;
    List<Fornitura> doRetrieveByAll(Paginatore paginatore) throws SQLException;
    List<Fornitura> doRetrieveBySize(String lTaglia) throws SQLException;
    List<Fornitura> doRetrieveByAmount(int quantita) throws SQLException;
    List<Integer> RetriveQuantity() throws SQLException;
    void insertFornitura(Fornitura fornitura) throws SQLException;
    Boolean updateFornitura(String Codice, Fornitura fornitura) throws  SQLException;
    Boolean deleteFornitura(String Codice) throws SQLException;
}
