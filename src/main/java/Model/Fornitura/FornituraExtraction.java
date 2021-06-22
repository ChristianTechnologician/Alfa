package Model.Fornitura;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FornituraExtraction {
    public Fornitura mapping (ResultSet rs) throws SQLException {
        Fornitura f = new Fornitura();
        f.setCodMerce(rs.getString(1));
        f.setCodColore(rs.getInt(2));
        f.setlTaglia(rs.getString(3));
        f.setQuantita(rs.getInt(4));
        return f;
    }

}
