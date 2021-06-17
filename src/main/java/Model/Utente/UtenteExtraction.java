package Model.Utente;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteExtraction
{
    public Utente mapping(ResultSet rs) throws SQLException, NoSuchAlgorithmException {
        Utente u=new Utente();
        u.setId(rs.getInt(1));
        u.setNome(rs.getString(2));
        u.setCognome(rs.getString(3));
        u.setEmail(rs.getString(4));
        u.setPassword(rs.getString(5));
        u.setIsAdministration(rs.getBoolean(6));
        return u;
    }
}
