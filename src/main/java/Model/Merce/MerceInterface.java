package Model.Merce;

import Model.Gestione.Paginatore;
import Model.Search.Condition;

import java.sql.SQLException;
import java.util.List;

public interface MerceInterface
{
    List<Merce> doRetrieveAllbyGender(String genere) throws SQLException;
    List<Merce> doRetrieveAllbyType(String genere, String type) throws SQLException;
    Merce doRetrieveByCode(String codice) throws SQLException;
    Boolean insertMerce(Merce merce) throws SQLException;
    Boolean updateMerce(String Codice, Merce merce) throws  SQLException;
    Boolean deleteMerce(String Codice) throws SQLException;
    int countAll() throws SQLException;
    List<Merce> search(List<String> query) throws SQLException;
    List<Merce> fetchProductsWithRelations(Paginatore paginatore) throws SQLException;
}
