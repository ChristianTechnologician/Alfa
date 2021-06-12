package Model.Utente;

import Model.ConPool;
import Model.Gestione.Paginatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtenteDAO implements UtenteInterface
{
    @Override
    public List<Utente> fetchUtenti(Paginatore paginatore) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM utente LIMIT ?,?;")){
                ps.setInt(1,paginatore.getOffset());
                ps.setInt(2,paginatore.getLimit());
                ResultSet rs = ps.executeQuery();
                List<Utente> utenti = new ArrayList<>();
                UtenteExtraction utenteExtraction = new UtenteExtraction();
                while(rs.next()){
                    utenti.add(utenteExtraction.mapping(rs));
                }
                rs.close();
                return utenti;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Optional<Utente> fetchUtente(String email) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Integer createUtente(Utente utente) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("INSERT INTO utente(ID,Nome,Cognome,Email,PW,IsAdministration) VALUES (?,?,?,?,?,?);")){
                ps.setInt(1,utente.getId());
                ps.setString(2,utente.getNome());
                ps.setString(3,utente.getCognome());
                ps.setString(4,utente.getEmail());
                ps.setString(5,utente.getPassword());
                ps.setBoolean(6,utente.getIsAdministration());
                return ps.executeUpdate();
            }
        }
    }

    @Override
    public Integer updateUtente(Utente utente) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("UPDATE utente SET (?,?,?,?,?,?) WHERE ID=?")){
                ps.setInt(1,utente.getId());
                ps.setString(2,utente.getNome());
                ps.setString(3,utente.getCognome());
                ps.setString(4,utente.getEmail());
                ps.setString(5,utente.getPassword());
                ps.setBoolean(6,utente.getIsAdministration());
                ps.setInt(7,utente.getId());
                return ps.executeUpdate();
            }
        }
    }

    @Override
    public Integer deleteUtente(String email) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            try(PreparedStatement ps = con.prepareStatement("DELETE FROM utente WHERE Email = ?")){
                ps.setString(1, email);
                ResultSet rs;
                return ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
