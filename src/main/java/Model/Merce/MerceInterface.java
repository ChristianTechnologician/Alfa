package Model.Merce;

import Model.Search.Condition;

import java.sql.SQLException;
import java.util.List;

public interface MerceInterface
{
    List<Merce> doRetrieveAllbyGender(String genere) throws SQLException;
    List<Merce> doRetrieveAllbyType(String genere, String type) throws SQLException;
    Merce doRetrieveByCode(String codice) throws SQLException;
    void insertMerce(Merce merce) throws SQLException;
    void updateMerce(String Codice,Merce merce) throws  SQLException;
    void deleteMerce(String Codice) throws SQLException;
    int countAll() throws SQLException;
    List<Merce> search(List<Condition> conditions) throws SQLException;
}
