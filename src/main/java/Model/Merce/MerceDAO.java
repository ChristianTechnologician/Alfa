package Model.Merce;

import Model.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MerceDAO implements MerceInterface{

    public List<Merce> doRetrieveAllbyGender(String genere) {
        ArrayList<Merce> vestiario =new ArrayList<>();
        Statement st;
        ResultSet rs;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM merce WHERE Genere = '"+genere+"'");
            while (rs.next())
            {
                MerceExtraction p=new MerceExtraction();
                vestiario.add(p.mapping(rs));
            }
            con.close();
            return vestiario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Merce> doRetrieveAllbyType(String genere, String type) {
        ArrayList<Merce> vestiario =new ArrayList<>();
        Statement st;
        ResultSet rs;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM merce WHERE Genere = '"+genere+"'and TipoCategoria='"+type+"'");
            while (rs.next())
            {
                MerceExtraction p=new MerceExtraction();
                vestiario.add(p.mapping(rs));
            }
            con.close();
            return vestiario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Merce doRetrieveByCode(String codice) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM merce WHERE Codice=?");
            ps.setString(1, codice);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                MerceExtraction p=new MerceExtraction();
                Merce m=new Merce();
                m=p.mapping(rs);
                return m;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
