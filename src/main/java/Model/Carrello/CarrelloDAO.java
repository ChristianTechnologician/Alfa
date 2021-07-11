package Model.Carrello;

import Model.Gestione.ConPool;
import Model.Merce.Merce;
import Model.Ordine.Ordine;
import Model.Ordine.OrdineExtraction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDAO implements CarrelloInterface
{

    @Override
    public List<Carrello> DoRetriveAll() throws SQLException {
        return null;
    }

    @Override
    public List<Carrello> DoRetrieveByUtente(int id) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM carrello WHERE IDutente = ?;")) {
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                List<Carrello> carrelloList = new ArrayList<>();
                CarrelloExtraction carrelloExtraction = new CarrelloExtraction();
                while(rs.next()){
                    carrelloList.add(carrelloExtraction.mapping(rs));
                }
                connection.close();
                if(carrelloList.isEmpty())
                {
                    return carrelloList=null;
                }
                return carrelloList;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<String> DoRetrieveCodici(int id) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("SELECT carrello.Mcodice FROM carrello WHERE IDutente = ?;")) {
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                List<String> carrelloCodici = new ArrayList<>();
                while(rs.next()){
                    carrelloCodici.add(rs.getString("Mcodice"));
                }
                connection.close();
                if(carrelloCodici.isEmpty())
                {
                    return carrelloCodici=null;
                }
                return carrelloCodici;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Integer> DoRetrieveQuantita(int id) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("SELECT carrello.Quantita FROM carrello WHERE IDutente = ?;")) {
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                List<Integer> carrelloQuantita = new ArrayList<>();
                while(rs.next()){
                    carrelloQuantita.add(rs.getInt("Quantita"));
                }
                connection.close();
                if(carrelloQuantita.isEmpty())
                {
                    return carrelloQuantita=null;
                }
                return carrelloQuantita;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Integer> DoRetrieveFcodice(int id) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("SELECT carrello.Fcodice FROM carrello WHERE IDutente = ?;")) {
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                List<Integer> cod = new ArrayList<>();
                while(rs.next()){
                    cod.add(rs.getInt("Fcodice"));
                }
                connection.close();
                if(cod.isEmpty())
                {
                    return cod=null;
                }
                return cod;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Carrello findElement(int id,String Mcodice,int Fcodice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM carrello WHERE IDutente=? AND Mcodice=? AND Fcodice=?")) {
                ps.setInt(1, id);
                ps.setString(2, Mcodice);
                ps.setInt(3, Fcodice);
               ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Carrello c = new Carrello();
                    CarrelloExtraction carrelloExtraction = new CarrelloExtraction();
                    c = carrelloExtraction.mapping(rs);
                    return  c;
                }
                return  null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean updateQuantita(int quantita,int id,String Mcodice,int Fcodice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("UPDATE carrello SET Quantita=? WHERE IDutente=? AND Mcodice=? AND Fcodice=?")) {
                ps.setInt(1, quantita);
                ps.setInt(2, id);
                ps.setString(3, Mcodice);
                ps.setInt(4, Fcodice);
                int rows = ps.executeUpdate();
                return rows == 1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean insertElemento(int id,String Mcodice,int Fcodice,int quantita) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO carrello (IDutente,Mcodice,Fcodice,Quantita) VALUES(?,?,?,?)")) {
                ps.setInt(1, id);
                ps.setString(2, Mcodice);
                ps.setInt(3, Fcodice);
                ps.setInt(4, quantita);
                int rows = ps.executeUpdate();
                return rows == 1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteCarrello(int id) throws SQLException{
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM carrello WHERE IDutente = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean deleteElementoCarrello(String codice,int id,int fcodice) throws SQLException{
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM carrello WHERE Mcodice = ? AND IDutente=? AND Fcodice=?")) {
                ps.setString(1, codice);
                ps.setInt(2, id);
                ps.setInt(3, fcodice);
                int rows = ps.executeUpdate();
                return rows == 1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
