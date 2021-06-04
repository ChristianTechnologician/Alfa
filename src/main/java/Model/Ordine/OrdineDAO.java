package Model.Ordine;

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
    public Ordine DoRetriveByUtente(int id_utente) throws SQLException {
        return null;
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
