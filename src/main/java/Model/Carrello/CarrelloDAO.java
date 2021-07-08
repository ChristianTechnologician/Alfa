package Model.Carrello;

import Model.Gestione.ConPool;
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

}
