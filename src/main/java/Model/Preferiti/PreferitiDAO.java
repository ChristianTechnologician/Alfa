package Model.Preferiti;

import Model.Carrello.Carrello;
import Model.Carrello.CarrelloExtraction;
import Model.Gestione.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreferitiDAO implements PreferitiInterface {
    @Override
    public List<Preferiti> DoRetriveAll() throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM preferiti ")) {
                ResultSet rs = ps.executeQuery();
                List<Preferiti> preferiti = new ArrayList<>();
                PreferitiExtraction pe = new PreferitiExtraction();
                while(rs.next()){
                    preferiti.add(pe.mapping(rs));
                }
                connection.close();
                return preferiti;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Preferiti> DoRetriveByUtente(int id_utente) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT preferiti.NumeroPreferiti\n" +
                                 "FROM preferiti,utente\n" +
                                 "WHERE utente.ID = ? AND preferiti.IDUtente = utente.ID")) {
                ps.setInt(1, id_utente);
                ResultSet rs = ps.executeQuery();
                List<Preferiti> preferiti = new ArrayList<>();
                PreferitiExtraction pe = new PreferitiExtraction();
                while (rs.next()) {
                    preferiti.add(pe.mapping(rs));
                }
                return preferiti;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<String> DoRetriveCodiciByUtente(int id_utente)  throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT preferiti.Mcodice\n" +
                                 "FROM preferiti,utente\n" +
                                 "WHERE utente.ID = ? AND preferiti.IDUtente = utente.ID")) {
                ps.setInt(1, id_utente);
                ResultSet rs = ps.executeQuery();
                List<String> codici = new ArrayList<>();
                while (rs.next()) {
                    codici.add(rs.getString("Mcodice"));
                }
                return codici;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Integer> DoRetriveFCodiciByUtente(int id_utente)  throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT preferiti.Fcodice\n" +
                                 "FROM preferiti,utente\n" +
                                 "WHERE utente.ID = ? AND preferiti.IDUtente = utente.ID")) {
                ps.setInt(1, id_utente);
                ResultSet rs = ps.executeQuery();
                List<Integer> fcodici = new ArrayList<>();
                while (rs.next()) {
                    fcodici.add(rs.getInt("Fcodice"));
                }
                return fcodici;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean insertPreferiti(int id_utente, int numero,String codice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("UPDATE preferiti SET(?,?,?)")) {
                ps.setInt(1, numero);
                ps.setInt(2, id_utente);
                ps.setString(3, codice);
                int rows = ps.executeUpdate();
                return rows == 1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deletePreferiti(int id_utente) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM preferiti WHERE IDutente = ?")) {
                ps.setInt(1, id_utente);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean deleteElementoPreferiti(String codice,int id,int fcodice) throws SQLException{
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM preferiti WHERE Mcodice = ? AND IDutente=? AND Fcodice=?")) {
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

    @Override
    public Preferiti findElement(int id, String Mcodice, int Fcodice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM carrello WHERE IDutente=? AND Mcodice=? AND Fcodice=?")) {
                ps.setInt(1, id);
                ps.setString(2, Mcodice);
                ps.setInt(3, Fcodice);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Preferiti p = new Preferiti();
                    PreferitiExtraction preferitiExtraction = new PreferitiExtraction();
                    p = preferitiExtraction.mapping(rs);
                    return  p;
                }
                return  null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean insertElemento(int numeroPreferiti ,int id,String Mcodice,int Fcodice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO carrello (NumeroPreferiti,IDutente,Mcodice,Fcodice) VALUES(?,?,?,?)")) {
                ps.setInt(1, numeroPreferiti);
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
    public boolean updateQuantita(int numeroPreferiti,int id,String Mcodice,int Fcodice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("UPDATE carrello SET NumeroPreferiti=? WHERE IDutente=? AND Mcodice=? AND Fcodice=?")) {
                ps.setInt(1, numeroPreferiti);
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


}
