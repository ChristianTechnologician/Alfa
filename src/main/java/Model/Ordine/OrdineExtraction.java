package Model.Ordine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrdineExtraction
{
    public Ordine mapping(ResultSet rs) throws SQLException
    {
        Ordine o=new Ordine();
        o.setNumeroFattura(rs.getInt(1));
        o.setVia(rs.getString(2));
        o.setCivico(rs.getInt(3));
        o.setCitta(rs.getString(4));
        o.setProvincia(rs.getString(5));
        o.setPrezzoTotale(rs.getDouble(6));
        o.setDate(rs.getObject( 7 , LocalDate.class ));
        o.setStato(rs.getInt(8));
        o.setCodiceMerceAcquistata(rs.getString(9));
        o.setIdUtente(rs.getInt(10));
        return o;
    }
}
