package Model.Colore;

import Model.Gestione.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColoreDAO implements ColoreInterface {
    @Override
    public Colore doRetrieveByCode(int code) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM colore WHERE Cod = '" + code + "'")) {
                ResultSet rs;
                rs = ps.executeQuery();
                Colore c = new Colore();
                if (rs.next()) {
                    ColoreExtraction ce = new ColoreExtraction();
                    c = ce.mapping(rs);
                    System.out.println(c);
                    return c;
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Colore doRetrieveByType(String Type) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM colore WHERE TipoColore = '" + Type + "'")) {
                ResultSet rs = ps.executeQuery();
                Colore c = new Colore();
                if (rs.next()) {
                    ColoreExtraction ce = new ColoreExtraction();
                    c = ce.mapping(rs);
                    return c;
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public List<Colore> doRetrieveByMerce(String codice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT colore.TipoColore\n" +
                                 "FROM colore,fornitura\n" +
                                 "WHERE fornitura.CodiceMerce = ? AND colore.Cod = fornitura.CodColore")) {
                ps.setString(1, codice);
                ResultSet rs = ps.executeQuery();
                List<Colore> colori = new ArrayList<>();
                ColoreExtraction ce = new ColoreExtraction();
                while (rs.next()) {
                    colori.add(ce.mapping(rs));
                }
                return colori;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Colore> doRetrieveByTaglia(String lTaglia) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT colore.TipoColore\n" +
                                 "FROM colore,fornitura\n" +
                                 "WHERE fornitura.LTaglia = ? AND colore.Cod = fornitura.CodColore")) {
                ps.setString(1, lTaglia);
                ResultSet rs = ps.executeQuery();
                List<Colore> colori = new ArrayList<>();
                ColoreExtraction ce = new ColoreExtraction();
                while (rs.next()) {
                    colori.add(ce.mapping(rs));
                }
                return colori;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Colore> doRetrieveByMerce_Taglia(String codice, String LTaglia) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT colore.TipoColore\n" +
                                 "FROM colore,fornitura,merce,taglia\n" +
                                 "WHERE fornitura.LTaglia = ? AND fornitura.CodiceMerce = ? AND fornitura.CodiceMerce = merce.Codice AND colore.Cod = fornitura.CodColore")) {
                ps.setString(1, codice);
                ps.setString(2, LTaglia);
                ResultSet rs = ps.executeQuery();
                List<Colore> colori = new ArrayList<>();
                ColoreExtraction ce = new ColoreExtraction();
                while (rs.next()) {
                    colori.add(ce.mapping(rs));
                }
                return colori;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Colore> doRetrieveAll() throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM colore")) {
                ResultSet rs = ps.executeQuery();
                List<Colore> colori = new ArrayList<>();
                ColoreExtraction ce = new ColoreExtraction();
                while (rs.next()) {
                    colori.add(ce.mapping(rs));
                }
                return colori;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
     public void  insertColor(String colore) throws SQLException{
         try (Connection con = ConPool.getConnection()) {
             try(PreparedStatement ps = con.prepareStatement("INSERT INTO colore (TipoColore) VALUES (?)")){
                 ps.setString(1, colore);
                 ps.executeUpdate();
             } catch (SQLException e) {
                 throw new RuntimeException(e);
             }
         }
     }

    @Override
     public void deleteColor(int codice) throws SQLException{
         try (Connection con = ConPool.getConnection()) {
             try (PreparedStatement ps = con.prepareStatement("DELETE FROM colore WHERE Cod = ?")) {
                 ps.setInt(1, codice);
                 ps.executeUpdate();
             } catch (SQLException e) {
                 throw new RuntimeException(e);
             }
         }
     }

}




