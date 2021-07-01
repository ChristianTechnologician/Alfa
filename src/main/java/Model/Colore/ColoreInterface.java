package Model.Colore;

import java.sql.SQLException;
import java.util.List;

public interface ColoreInterface
{
    Colore doRetrieveByCode(int code) throws SQLException;
    List<Colore> doRetrieveByMerce(String codice) throws SQLException;
    List<Colore> doRetrieveByTaglia(String lTaglia) throws SQLException;
    List<Colore> doRetrieveByMerce_Taglia(String merce, String taglia) throws SQLException;
    List<Colore> doRetrieveAll() throws SQLException;
    void insertColor(String colore) throws SQLException;
    Colore doRetrieveByType(String Type) throws SQLException;
    void deleteColor(int codice) throws SQLException;
}
