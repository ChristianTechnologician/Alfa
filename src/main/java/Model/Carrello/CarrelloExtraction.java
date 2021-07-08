package Model.Carrello;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarrelloExtraction
{
    public Carrello mapping(ResultSet rs) throws SQLException
    {
        Carrello c=new Carrello();
        c.setIdCarrello(rs.getInt(1));
        c.setIdUtente(rs.getInt(2));
        c.setmCodice(rs.getString(3));
        c.setQuantita(rs.getInt(4));
        return c;
    }
}
