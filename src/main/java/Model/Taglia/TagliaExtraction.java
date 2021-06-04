package Model.Taglia;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagliaExtraction
{
    public Taglia mapping(ResultSet rs) throws SQLException
    {
        Taglia t=new Taglia();
        t.setlTaglia(rs.getString(1));
        return t;
    }
}
