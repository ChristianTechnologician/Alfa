package Model.Taglia;

import Model.Colore.Colore;

import java.sql.SQLException;
import java.util.List;

public interface TagliaInterface {

    List<Taglia> doRetrieveByMerce(String codice) throws SQLException;
    List<Taglia> doRetrieveByColore(int colore) throws SQLException;
    List<Taglia> doRetrieveByMerce_Colore(String merce, int colore) throws SQLException;
    List<Taglia> doRetrieveAll() throws SQLException;
    void insertTaglia(String LTaglia) throws SQLException;
    void deleteTaglia(String LTaglia) throws SQLException;
    Boolean checkTaglia(String LTaglia) throws SQLException;
}
