package Model.Utente;

import Model.Connessione.Manager;
import Model.ConPool;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtenteDAO implements UtenteD
{
    @Override
    public List<Utente> fetchUtenti(int start, int end) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM utente LIMIT ?,?;")){
                ps.setInt(1,start);
                ps.setInt(2,end);
                ResultSet set = ps.executeQuery();
                List<Utente> utenti = new ArrayList<>();
                while(set.next()){
                    Utente utente = new Utente();
                    utente.setAdministration(set.getBoolean("isAdministration"));
                    utente.setId(set.getInt("ID"));
                    utente.setNome(set.getString("Nome"));
                    utente.setCognome(set.getString("Cognome"));
                    utente.setEmail(set.getString("Email"));
                    utente.setPassword(set.getString("PW"));
                    utenti.add(utente);
                }
                set.close();
                return  utenti;
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
        return null;
    }

    @Override
    public Integer deleteUtente(String email) throws SQLException {
        return null;
    }
}
