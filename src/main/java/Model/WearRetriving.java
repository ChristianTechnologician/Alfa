package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WearRetriving {

    public Abbigliamento doRetrieveByGender(String genere) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT Codice, Taglia, Genere, Prezzo ,Categoria FROM merce WHERE Genere=?");
            ps.setString(1, genere);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Abbigliamento p = new Abbigliamento();
                p.setCodice(rs.getString(1));
                p.setTaglia(rs.getString(2));
                p.setGenere(rs.getString(3));
                p.setPrezzo(rs.getDouble(4));
                p.setCategoria(rs.getString(5));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Abbigliamento> doRetrieveAll(String genere) {
        ArrayList<Abbigliamento> vestiario =new ArrayList<>();
        Statement st;
        ResultSet rs;
        Abbigliamento p;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM merce WHERE Genere = '"+genere+"'");
            while (rs.next()) {
                p = new Abbigliamento();
                p.setCodice(rs.getString(1));
                p.setGenere(rs.getString(2));
                p.setPrezzo(rs.getDouble(3));
                p.setCategoria(rs.getString(4));
                vestiario.add(p);
            }
            con.close();
            return vestiario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Abbigliamento> doRetrieveAllbyType(String genere, String type) {
        ArrayList<Abbigliamento> vestiario =new ArrayList<>();
        Statement st;
        ResultSet rs;
        Abbigliamento p;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM merce WHERE Genere = '"+genere+"'and Categoria='"+type+"'");
            while (rs.next()) {
                p = new Abbigliamento();
                p.setCodice(rs.getString(1));
                p.setGenere(rs.getString(2));
                p.setPrezzo(rs.getDouble(3));
                p.setCategoria(rs.getString(4));
                vestiario.add(p);
            }
            con.close();
            return vestiario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
