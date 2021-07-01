package Model.Merce;

import Model.Gestione.ConPool;
import Model.Gestione.Paginatore;
import Model.Search.Condition;

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
    public Boolean insertMerce(Merce merce) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO merce VALUES (?,?,?,?,?,?,?)")) {
                ps.setString(1, merce.getCodice());
                ps.setString(2, merce.getNome());
                ps.setString(3, merce.getDescrizione());
                ps.setString(4, merce.getGenere());
                ps.setDouble(5, merce.getPrezzo());
                ps.setString(6, merce.getTipocategoria());
                ps.setDouble(7, merce.getSconto());
                int rows = ps.executeUpdate();
                return rows == 1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Boolean updateMerce(String Codice, Merce merce) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("UPDATE merce SET Codice=?, Nome=?, Descrizione=?, Genere=?, Prezzo=?, TipoCategoria=?, Sconto=? WHERE Codice=?")) {
                ps.setString(1, merce.getCodice());
                ps.setString(2, merce.getNome());
                ps.setString(3, merce.getDescrizione());
                ps.setString(4, merce.getGenere());
                ps.setDouble(5, merce.getPrezzo());
                ps.setString(6, merce.getTipocategoria());
                ps.setDouble(7, merce.getSconto());
                ps.setString(8, Codice);
                int rows = ps.executeUpdate();
                return rows == 1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //UPDATE merce SET(?,?,?,?,?,?,?) WHERE Codice = ?
    @Override
    public Boolean deleteMerce(String Codice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM merce WHERE Codice = ?")) {
                ps.setString(1, Codice);
                ResultSet rs;
                int rows = ps.executeUpdate();
                return rows == 1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int countAll() throws SQLException{
        try(Connection con = ConPool.getConnection()){
            try(PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM merce")){
                ResultSet rs = ps.executeQuery();
                int size = 0;
                if(rs.next()){
                    size = rs.getInt(1);
                }
                return  size;
            }
        }
    }

    @Override
    public List<Merce> search(List<String> query) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("?")) {
                for (int i = 0; i < query.size(); i++) {
                    ps.setObject(i + 1, query.get(i));
                }
                ResultSet rs = ps.executeQuery();
                List<Merce> merci = new ArrayList<>();
                while (rs.next()) {
                    Merce merce = new MerceExtraction().mapping(rs);
                    //Product.setCategory(new CategoryExtractor().extract(set));
                    //Product.setCountry(new CountryExtractor().extract(set));
                    merci.add(merce);
                }
                return merci;
            }

        }

    }


    public List<Merce> fetchProductsWithRelations(Paginatore paginatore) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try(PreparedStatement ps = con.prepareStatement("SELECT * FROM merce LIMIT ?,?")){
               ps.setInt(1,paginatore.getOffset());
               ps.setInt(2,paginatore.getLimit());
               ResultSet rs = ps.executeQuery();
               MerceExtraction merceExtraction = new MerceExtraction();
               List<Merce> merci = new ArrayList<>();
               while(rs.next()){
                   Merce merce = merceExtraction.mapping(rs);
                   merci.add(merce);
               }
               return merci;
            }
        }
    }
}











