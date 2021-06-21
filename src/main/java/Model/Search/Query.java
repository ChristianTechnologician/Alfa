package Model.Search;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Query {
    public List<String> selectQuery(List<Condition> conditions) throws SQLException{
        List<String> query = new ArrayList<>();
        for (Condition c: conditions) {
            switch (c.getName()) {
                case "Nome":String q = "SELECT * FROM merce WHERE Nome '"+c.getOperator()+" '%'"+c.getValue()+"'%'";
                    query.add(q);
                    break;
                case "TipoCategoria":String u = "SELECT * FROM merce WHERE TipoCategoria '"+c.getOperator()+"' '"+c.getValue()+"'";
                    query.add(u);
                    break;
                case "LTaglia":String e = "SELECT merce.me\n" +
                        "FROM taglia,fornitura,merce\n" +
                        "WHERE taglia.LTaglia '"+c.getOperator()+"' '"+c.getValue()+"'AND fornitura.LTaglia '"+c.getOperator()+"' taglia.LTaglia AND merce.Codice '"+c.getOperator()+"' fornitura.CodiceMerce ";
                    query.add(e);
                    break;
                case "Sconto":String r = "SELECT * FROM merce WHERE Sconto '"+c.getOperator()+"' '"+c.getValue()+"'";
                    query.add(r);
                    break;
                case  "MinPrice":String y = "SELECT * FROM merce WHERE Prezzo '"+c.getOperator()+"' '"+c.getValue()+"'";
                    query.add(y);
                    break;
                case  "MaxPrice":String s = "SELECT * FROM merce WHERE Prezzo '"+c.getOperator()+"' '"+c.getValue()+"'";
                    query.add(s);
                    break;
                case  "Range":
                    String split = (String) c.getValue();
                    String[] a = split.split(",");
                    String range = "SELECT * FROM merce WHERE Prezzo >= a[0] AND Prezzo <= a[1]";
                    query.add(range);
                    break;
            }
        }
        return query;
    }

}
