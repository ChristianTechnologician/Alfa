package Model.Fornitura;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FornituraExtraction {
    public Fornitura mapping (ResultSet rs) throws SQLException {
        Fornitura f = new Fornitura();
        f.setCodMerce(rs.getString("CodiceMerce"));
        f.setCodColore(rs.getInt("CodColore"));
        f.setlTaglia(rs.getString("LTaglia"));
        f.setIdentificatore(rs.getInt("Identificatore"));
        f.setQuantita(rs.getInt("Quantità"));
        return f;
    }

}
