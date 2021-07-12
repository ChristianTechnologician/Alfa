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
    public List<Fornitura> doRetrieveByCode(String codice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT * FROM fornitura WHERE CodiceMerce=?")){
                ps.setString(1, codice);
                ResultSet rs = ps.executeQuery();
                FornituraExtraction fe = new FornituraExtraction();
                List<Fornitura> f = new ArrayList<>();
                while (rs.next()) {
                  f.add(fe.mapping(rs));
                }
                return f;
            }catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Fornitura doRetrieveByUtenteCode(int codice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT DISTINCT fornitura.CodiceMerce, fornitura.CodColore, fornitura.LTaglia,fornitura.Identificatore,fornitura.Quantità FROM fornitura WHERE fornitura.Identificatore=?")){
                ps.setInt(1, codice);
                System.out.println(codice);
                ResultSet rs = ps.executeQuery();
                FornituraExtraction fe = new FornituraExtraction();
                Fornitura f = new Fornitura();
                System.out.println("aaaaaa");
                if(rs.next()) {
                    System.out.println("wwww");
                    f= fe.mapping(rs);
                    System.out.println("eeeeee");
                    System.out.println(f.getCodColore());
                    System.out.println(f.getQuantita());
                    System.out.println(f.getIdentificatore()+f.getCodColore()+f.getQuantita()+f.getCodMerce()+f.getlTaglia());
                    System.out.println("rrrrrr");
                }
                System.out.println("zzzzzz");
                return f;
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
    public List<Integer> RetriveQuantity() throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT Quantità FROM fornitura")){
                ResultSet rs = ps.executeQuery();
                List<Integer> q = new ArrayList<>();;
                while(rs.next()) {
                    q.add(rs.getInt("Quantità"));
                }
                con.close();
                return q;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void insertFornitura(Fornitura fornitura) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO fornitura SET CodiceMerce=?, CodColore=?, LTaglia=?, Quantità=?")) {
                ps.setString(1,fornitura.getCodMerce());
                ps.setInt(2, fornitura.getCodColore());
                ps.setString(3, fornitura.getlTaglia());
                ps.setInt(4, fornitura.getQuantita());
                ps.executeUpdate();
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

    public Boolean deleteFornitura(String Codice) throws SQLException {
        int count=0;
        try (Connection con = ConPool.getConnection()) {
            try(PreparedStatement pss = con.prepareStatement("SELECT * FROM fornitura WHERE CodiceMerce = ?")){
                pss.setString(1, Codice);
                ResultSet rs=pss.executeQuery();
                ArrayList<Fornitura> f=new ArrayList<>();
                while (rs.next())
                {
                    FornituraExtraction x=new FornituraExtraction();
                    f.add(x.mapping(rs));
                    count++;
                }
                System.out.println(count);
            }
         catch (SQLException e) {
            throw new RuntimeException(e);
        }
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM fornitura WHERE CodiceMerce = ?")) {
                ps.setString(1, Codice);
                int rows = ps.executeUpdate();
                return rows == count;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
