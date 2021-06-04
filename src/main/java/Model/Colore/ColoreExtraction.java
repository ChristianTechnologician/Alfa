package Model.Colore;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColoreExtraction
{
    public Colore mapping(ResultSet rs) throws SQLException
    {
        Colore c=new Colore();
        c.setCod(rs.getInt(1));
        c.setTipoColore(rs.getString(2));
        return c;
    }
}
