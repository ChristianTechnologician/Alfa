package Model.Merce;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MerceExtraction
{
    public Merce mapping(ResultSet rs) throws SQLException
    {
        Merce m = new Merce();
        m.setCodice(rs.getString(1));
        m.setNome(rs.getString(2));
        m.setDescrizione(rs.getString(3));
        m.setGenere(rs.getString(4));
        m.setPrezzo(rs.getDouble(5));
        m.setTipocategoria(rs.getString(6));
        m.setSconto(rs.getDouble(7));
        return m;
    }
}
