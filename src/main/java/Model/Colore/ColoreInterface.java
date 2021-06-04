package Model.Colore;

import java.sql.SQLException;
import java.util.List;

public interface ColoreInterface
{
    Colore doRetrieveByCode(int code) throws SQLException;
    List<Colore> doRetrieveAll() throws SQLException;
    List<Colore> doRetrieveAllByMerce() throws SQLException;
}
