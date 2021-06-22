package Model.Fornitura;

import Model.Gestione.Paginatore;
import Model.Merce.Merce;

import java.sql.SQLException;
import java.util.List;

public interface FornituraInterface {
    Fornitura doRetrieveByCode(String codice) throws SQLException;
    List<Fornitura> doRetrieveByAll(Paginatore paginatore) throws SQLException;
    List<Fornitura> doRetrieveBySize(String lTaglia) throws SQLException;
    List<Fornitura> doRetrieveByAmount(int quantita) throws SQLException;
    Boolean insertFornitura(Fornitura fornitura) throws SQLException;
    Boolean updateFornitura(String Codice, Fornitura fornitura) throws  SQLException;
}
