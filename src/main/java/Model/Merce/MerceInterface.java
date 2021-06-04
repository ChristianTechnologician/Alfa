package Model.Merce;

import java.sql.SQLException;
import java.util.List;

public interface MerceInterface
{
    List<Merce> doRetrieveAllbyGender(String genere) throws SQLException;
    List<Merce> doRetrieveAllbyType(String genere, String type) throws SQLException;
    Merce doRetrieveByCode(String codice) throws SQLException;
}
