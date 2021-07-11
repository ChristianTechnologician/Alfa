package Model.Preferiti;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PreferitiExtraction
{
    public Preferiti mapping(ResultSet rs) throws SQLException
    {
        Preferiti p=new Preferiti();
        p.setNumeroPreferiti(rs.getInt(1));
        p.setIdUtente(rs.getInt(2));
        p.setmCodice(rs.getString(3));
        p.setFcodice(rs.getInt(4));
        return p;
    }
}
