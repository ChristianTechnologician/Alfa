package Model.Taglia;

import Model.Gestione.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagliaDAO implements TagliaInterface {
    @Override
    public List<Taglia> doRetrieveByMerce(String codice) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT taglia.LTaglia\n" +
                                 "FROM taglia,fornitura,merce\n" +
                                 "WHERE merce.Codice = ? AND merce.Codice = fornitura.CodiceMerce AND taglia.LTaglia = fornitura.LTaglia")) {
                ps.setString(1, codice);
                ResultSet rs = ps.executeQuery();
                List<Taglia> taglie = new ArrayList<>();
                TagliaExtraction te = new TagliaExtraction();
                while (rs.next()) {
                    taglie.add(te.mapping(rs));
                }
                return taglie;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Taglia> doRetrieveByColore(int colore) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT taglia.LTaglia\n" +
                                 "FROM colore,fornitura,taglia\n" +
                                 "WHERE colore.Cod = ? AND colore.Cod = fornitura.CodColore AND fornitura.LTaglia = taglia.LTaglia")) {
                ps.setInt(1, colore);
                ResultSet rs = ps.executeQuery();
                List<Taglia> taglie = new ArrayList<>();
                TagliaExtraction te = new TagliaExtraction();
                while (rs.next()) {
                    taglie.add(te.mapping(rs));
                }
                return taglie;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Taglia> doRetrieveByMerce_Colore(String merce, int colore) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps =
                         con.prepareStatement("SELECT taglia.LTaglia\n" +
                                 "FROM colore,fornitura,merce,taglia\n" +
                                 "WHERE merce.Codice = ? AND colore.Cod = ? AND fornitura.CodiceMerce = merce.Codice AND colore.Cod = fornitura.CodColore" +
                                 "AND fornitura.LTaglia = taglia.LTaglia")) {
                ps.setString(1, merce);
                ps.setInt(2, colore);
                ResultSet rs = ps.executeQuery();
                List<Taglia> taglie = new ArrayList<>();
                TagliaExtraction te = new TagliaExtraction();
                while (rs.next()) {
                    taglie.add(te.mapping(rs));
                }
                return taglie;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Taglia> doRetrieveAll() throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try(PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM taglia")){
                ResultSet rs = ps.executeQuery();
                List<Taglia> taglie = new ArrayList<>();
                TagliaExtraction te = new TagliaExtraction();
                while (rs.next()) {
                    taglie.add(te.mapping(rs));
                }
                return taglie;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void insertTaglia(String LTaglia) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO taglia VALUES (?)")) {
                ps.setString(1, LTaglia);
                ResultSet rs;
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteTaglia(String LTaglia) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM taglia WHERE LTaglia = ?")) {
                ps.setString(1, LTaglia);
                ResultSet rs;
                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
