package Model.Merce;

import Model.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MerceDAO implements MerceInterface{

    public List<Merce> doRetrieveAllbyGender(String genere) {
        ArrayList<Merce> vestiario =new ArrayList<>();
        Statement st;
        ResultSet rs;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM merce WHERE Genere = '"+genere+"'");
            while (rs.next())
            {
                MerceExtraction p=new MerceExtraction();
                vestiario.add(p.mapping(rs));
            }
            con.close();
            return vestiario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Merce> doRetrieveAllbyType(String genere, String type) {
        ArrayList<Merce> vestiario =new ArrayList<>();
        Statement st;
        ResultSet rs;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM merce WHERE Genere = '"+genere+"'and TipoCategoria='"+type+"'");
            while (rs.next())
            {
                MerceExtraction p=new MerceExtraction();
                vestiario.add(p.mapping(rs));
            }
            con.close();
            return vestiario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Merce doRetrieveByCode(String codice) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM merce WHERE Codice=?");
            ps.setString(1, codice);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                MerceExtraction p=new MerceExtraction();
                Merce m=new Merce();
                m=p.mapping(rs);
                return m;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertMerce(Merce merce) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        try (Connection con = ConPool.getConnection()) {
            ps = con.prepareStatement("INSERT INTO merce VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, merce.getCodice());
            ps.setString(2, merce.getNome());
            ps.setString(3, merce.getDescrizione());
            ps.setString(4, merce.getGenere());
            ps.setDouble(5, merce.getPrezzo());
            ps.setString(6, merce.getTipocategoria());
            ps.setDouble(7, merce.getSconto());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMerce(String Codice,Merce merce) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        try (Connection con = ConPool.getConnection()) {
            ps = con.prepareStatement("UPDATE merce VALUES (?,?,?,?,?,?,?) WHERE Codice = ?");
            ps.setString(8, Codice);
            ps.setString(1, merce.getCodice());
            ps.setString(2, merce.getNome());
            ps.setString(3, merce.getDescrizione());
            ps.setString(4, merce.getGenere());
            ps.setDouble(5, merce.getPrezzo());
            ps.setString(6, merce.getTipocategoria());
            ps.setDouble(7, merce.getSconto());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMerce(String Codice) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        try (Connection con = ConPool.getConnection()) {
            ps = con.prepareStatement("DELETE merce WHERE Codice = ?");
            ps.setString(1, Codice);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
