package Model.Ordine;

import Model.Colore.Colore;
import Model.Colore.ColoreExtraction;
import Model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO implements OrdineInterface{
    @Override
    public List<Ordine> DoRetriveAll(int start, int end) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM ordine LIMIT ?,?;")) {
                ps.setInt(1, start);
                ps.setInt(2,end);
                ResultSet rs = ps.executeQuery();
                List<Ordine> ordine = new ArrayList<>();
                OrdineExtraction ordineExtraction = new OrdineExtraction();
                while(rs.next()){
                    ordine.add(ordineExtraction.mapping(rs));
                }
                connection.close();
                return ordine;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Ordine> DoRetriveByData(LocalDate data) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM ordine WHERE DataFattura = ?;")) {
                ps.setObject(1,data);
                ResultSet rs = ps.executeQuery();
                List<Ordine> ordine = new ArrayList<>();
                OrdineExtraction ordineExtraction = new OrdineExtraction();
                while(rs.next()){
                    ordine.add(ordineExtraction.mapping(rs));
                }
                connection.close();
                return ordine;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Ordine> DoRetriveByUtente(int id_utente) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT ordine.Numerofattura, ordine.Via, ordine.Civico, ordine.Citta, ordine.Provincia, tordine.PrezzoTotale,ordine.DataFattura,ordine.Stato\n" +
                            "FROM utente,ordine\n" +
                            "WHERE utente.ID = ? AND Ordine.IDUtente = utente.ID");
            ps.setInt(1, id_utente);
            ResultSet rs = ps.executeQuery();
            List<Ordine> ordini = new ArrayList<>();
            OrdineExtraction oe = new OrdineExtraction();
            while (rs.next()) {
                ordini.add(oe.mapping(rs));
            }
            return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ordine DoRetriveByNumeroFattura(int numero_fattura) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM ordine WHERE NumeroFattura = ?;")) {
                ps.setInt(1,numero_fattura);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Ordine ordine = new Ordine();
                    OrdineExtraction ordineExtraction = new OrdineExtraction();
                    ordine = ordineExtraction.mapping(rs);
                    return ordine;
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
