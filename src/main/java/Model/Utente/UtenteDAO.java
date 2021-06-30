package Model.Utente;

import Model.Gestione.ConPool;
import Model.Gestione.Paginatore;


import java.security.NoSuchAlgorithmException;
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
            try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM utente WHERE isAdministration = ? LIMIT ?,?")){
                ps.setBoolean(1,false);
                ps.setInt(2,paginatore.getOffset());
                ps.setInt(3,paginatore.getLimit());
                ResultSet rs = ps.executeQuery();
                List<Utente> utenti = new ArrayList<>();
                UtenteExtraction utenteExtraction = new UtenteExtraction();
                while(rs.next()){
                    utenti.add(utenteExtraction.mapping(rs));
                }
                rs.close();
                return utenti;
            } catch (SQLException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Utente> fetchAdmin() throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM utente WHERE IsAdministration = ?")){
                ps.setBoolean(1,true);
                ResultSet rs = ps.executeQuery();
                ArrayList<Utente> utenti = new ArrayList<>();
                UtenteExtraction utenteExtraction = new UtenteExtraction();
                while(rs.next()){
                    utenti.add(utenteExtraction.mapping(rs));
                }
                rs.close();
                return utenti;
            } catch (SQLException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public Utente fetchUtente(String email) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE Email = ?")) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                Utente utente= new Utente();
                UtenteExtraction utenteExtraction = new UtenteExtraction();
                if(rs.next()){
                    utente = utenteExtraction.mapping(rs);
                }
                return utente;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Boolean createUtente(Utente utente) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("INSERT INTO utente(ID,Nome,Cognome,Email,PW,IsAdministration) VALUES (?,?,?,?,?,?);")){
                ps.setInt(1,utente.getId());
                ps.setString(2,utente.getNome());
                ps.setString(3,utente.getCognome());
                ps.setString(4,utente.getEmail());
                ps.setString(5,utente.getPassword());
                ps.setBoolean(6,utente.getIsAdministration());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public Boolean updateUtente(Utente utente) throws SQLException {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("UPDATE utente SET ID=?, Nome=?, Cognome=?, Email=?, PW=?, IsAdministration=? WHERE ID=?")){
                ps.setInt(1,utente.getId());
                System.out.println(utente.getId());
                ps.setString(2,utente.getNome());
                System.out.println(utente.getNome());
                ps.setString(3,utente.getCognome());
                System.out.println(utente.getCognome());
                ps.setString(4,utente.getEmail());
                System.out.println(utente.getEmail());
                ps.setString(5,utente.getPassword());
                System.out.println(utente.getPassword());
                ps.setBoolean(6,utente.getIsAdministration());
                System.out.println(utente.getIsAdministration());
                ps.setInt(7,utente.getId());
                System.out.println(utente.getId());
                int rows = ps.executeUpdate();
                System.out.println(rows);
                return rows == 1;
            }
        }
    }

    @Override
    public Boolean deleteUtente(String email) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            try(PreparedStatement ps = con.prepareStatement("DELETE FROM utente WHERE Email = ?")){
                ps.setString(1, email);
                int rows = ps.executeUpdate();
                return rows == 1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Utente loginUtente(String email, String password, boolean admin) throws SQLException {
        Utente utente;
        try(Connection con = ConPool.getConnection()) {
            try(PreparedStatement ps = con.prepareStatement("SELECT *  FROM utente WHERE Email=? AND PW=? AND IsAdministration = ?")){
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setBoolean(3, admin);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    utente = new UtenteExtraction().mapping(rs);
                    return utente;
                }
                //return Optional.ofNullable(utente);
               // return null;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            //return Optional.empty();
            return null;
        }
    }

    public int countAll() throws SQLException{
        try(Connection con = ConPool.getConnection()){
            try(PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM utente")){
                ResultSet rs = ps.executeQuery();
                int size = 0;
                if(rs.next()){
                    size = rs.getInt(1);
                }
                System.out.println(size);
                return  size;
            }
        }
    }

    @Override
    public Optional<Utente> fetchUtente(int id) throws SQLException {
        try(Connection con = ConPool.getConnection()){
            try(PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE ID = ?")){
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                Utente utente = null;
                if(rs.next()){
                    utente = new UtenteExtraction().mapping(rs);
                }
                return Optional.ofNullable(utente);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
}
