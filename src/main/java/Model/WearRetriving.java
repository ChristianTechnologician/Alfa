package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WearRetriving {

    public Abbigliamento doRetrieveByGender(String genere) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM merce WHERE Genere=?");
            ps.setString(1, genere);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Abbigliamento p = new Abbigliamento();
                p.setCodice(rs.getString(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setGenere(rs.getString(4));
                p.setPrezzo(rs.getDouble(5));
                p.setTipocategoria(rs.getString(6));
                p.setSconto(rs.getDouble(7));
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
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setGenere(rs.getString(4));
                p.setPrezzo(rs.getDouble(5));
                p.setTipocategoria(rs.getString(6));
                p.setSconto(rs.getDouble(7));
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
            rs = st.executeQuery("SELECT * FROM merce WHERE Genere = '"+genere+"'and TipoCategoria='"+type+"'");
            while (rs.next()) {
                p = new Abbigliamento();
                p.setCodice(rs.getString(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setGenere(rs.getString(4));
                p.setPrezzo(rs.getDouble(5));
                p.setTipocategoria(rs.getString(6));
                p.setSconto(rs.getDouble(7));
                vestiario.add(p);
            }
            con.close();
            return vestiario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Abbigliamento doRetrieveByCode(String codice) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM merce WHERE Codice=?");
            ps.setString(1, codice);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Abbigliamento p = new Abbigliamento();
                p.setCodice(rs.getString(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setGenere(rs.getString(4));
                p.setPrezzo(rs.getDouble(5));
                p.setTipocategoria(rs.getString(6));
                p.setSconto(rs.getDouble(7));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
