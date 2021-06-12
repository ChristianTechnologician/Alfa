package Model.Merce;

import Model.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MerceDAO implements MerceInterface{


    @Override
    public List<Merce> doRetrieveAllbyGender(String genere) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try(PreparedStatement ps = con.prepareStatement("SELECT * FROM merce WHERE Genere = '"+genere+"'")){
                ResultSet rs;
                rs = ps.executeQuery();
                List<Merce> vestiario =new ArrayList<>();
                while (rs.next())
                {
                    MerceExtraction p=new MerceExtraction();
                    vestiario.add(p.mapping(rs));
                }
                rs.close();
                return vestiario;
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Merce> doRetrieveAllbyType(String genere, String type) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM merce WHERE Genere = '" + genere + "'and TipoCategoria='" + type + "'")) {
                ResultSet rs;
                rs = ps.executeQuery();
                ArrayList<Merce> vestiario =new ArrayList<>();
                while (rs.next()) {
                    MerceExtraction p = new MerceExtraction();
                    vestiario.add(p.mapping(rs));
                }
                rs.close();
                return vestiario;
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Merce doRetrieveByCode(String codice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM merce WHERE Codice=?")){
                ps.setString(1, codice);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    MerceExtraction p = new MerceExtraction();
                    Merce m = new Merce();
                    m = p.mapping(rs);
                    return m;
                }
                return null;
            }catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void insertMerce(Merce merce) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO merce VALUES (?,?,?,?,?,?,?)")) {
                ps.setString(1, merce.getCodice());
                ps.setString(2, merce.getNome());
                ps.setString(3, merce.getDescrizione());
                ps.setString(4, merce.getGenere());
                ps.setDouble(5, merce.getPrezzo());
                ps.setString(6, merce.getTipocategoria());
                ps.setDouble(7, merce.getSconto());
                ResultSet rs;
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void updateMerce(String Codice, Merce merce) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("UPDATE merce VALUES (?,?,?,?,?,?,?) WHERE Codice = ?")) {
                ps.setString(8, Codice);
                ps.setString(1, merce.getCodice());
                ps.setString(2, merce.getNome());
                ps.setString(3, merce.getDescrizione());
                ps.setString(4, merce.getGenere());
                ps.setDouble(5, merce.getPrezzo());
                ps.setString(6, merce.getTipocategoria());
                ps.setDouble(7, merce.getSconto());
                ResultSet rs;
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteMerce(String Codice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("DELETE merce WHERE Codice = ?")) {
                ps.setString(1, Codice);
                ResultSet rs;
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}











