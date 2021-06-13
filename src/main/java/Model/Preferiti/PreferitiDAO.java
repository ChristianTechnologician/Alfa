package Model.Preferiti;

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

    public void insertPreferiti(int id_utente, int numero) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("UPDATE preferiti SET(?) WHERE IDutente = ?")) {
                ps.setInt(1, numero);
                ps.setInt(2, id_utente);
                ResultSet rs;
                ps.executeUpdate();
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
                ResultSet rs;
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
