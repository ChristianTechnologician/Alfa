package Model.Fornitura;

import Model.Gestione.ConPool;
import Model.Gestione.Paginatore;
import Model.Merce.Merce;
import Model.Merce.MerceExtraction;
import Model.Ordine.Ordine;
import Model.Ordine.OrdineExtraction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornituraDAO implements  FornituraInterface{

    @Override
    public Fornitura doRetrieveByCode(String codice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM fornitura WHERE CodiceMerce=?")){
                ps.setString(1, codice);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    FornituraExtraction fe = new FornituraExtraction();
                  Fornitura f = new Fornitura() ;
                  f = fe.mapping(rs);
                    return f;
                }
                return null;
            }catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Fornitura> doRetrieveByAll(Paginatore paginatore) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM fornitura LIMIT ?,?;")) {
                ps.setInt(1, paginatore.getOffset());
                ps.setInt(2,paginatore.getLimit());
                ResultSet rs = ps.executeQuery();
                List<Fornitura> forniture = new ArrayList<>();
                FornituraExtraction fe = new FornituraExtraction();
                while(rs.next()){
                    forniture.add(fe.mapping(rs));
                }
                connection.close();
                return forniture;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Fornitura> doRetrieveBySize(String lTaglia) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM fornitura WHERE LTaglia=?")){
                ps.setString(1, lTaglia);
                ResultSet rs = ps.executeQuery();

                    FornituraExtraction fe = new FornituraExtraction();
                    List<Fornitura> forniture = new ArrayList<>();
                    while(rs.next()) {
                        forniture.add(fe.mapping(rs));
                    }
                        con.close();
                        return forniture;
                    } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
    }

    @Override
    public List<Fornitura> doRetrieveByAmount(int quantita) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM fornitura WHERE Quantita=?")){
                ps.setInt(1, quantita);
                ResultSet rs = ps.executeQuery();
                FornituraExtraction fe = new FornituraExtraction();
                List<Fornitura> forniture = new ArrayList<>();
                while(rs.next()) {
                    forniture.add(fe.mapping(rs));
                }
                con.close();
                return forniture;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Boolean insertFornitura(Fornitura fornitura) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO fornitura VALUES (?,?,?,?)")) {
                ps.setString(1,fornitura.getCodMerce());
                ps.setInt(2, fornitura.getCodColore());
                ps.setString(3, fornitura.getlTaglia());
                ps.setInt(4, fornitura.getQuantita());
                ResultSet rs;
                int rows = ps.executeUpdate();
                return rows == 1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Boolean updateFornitura(String Codice, Fornitura fornitura) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO fornitura VALUES (?,?,?,?) WHERE CodiceMerce = ?")) {
                ps.setString(1,fornitura.getCodMerce());
                ps.setInt(2, fornitura.getCodColore());
                ps.setString(3, fornitura.getlTaglia());
                ps.setInt(4, fornitura.getQuantita());
                ps.setString(5, Codice);
                ResultSet rs;
                int rows = ps.executeUpdate();
                return rows == 1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
