package Model.Search;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MerceSearch  implements SearchBuilder {
    @Override
    public List<Condition> buildSearch(HttpServletRequest request) {
        List <Condition> conditions = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String param = parameterNames.nextElement();
            String value = request.getParameter(param);
            if(value != null && !value.isBlank()){
                switch(param){
                    case "nome":
                        conditions.add(new Condition("Nome", Operator.MATCH, value));
                        break;
                    case "tipoCategoria":
                        conditions.add(new Condition("TipoCategoria", Operator.EQ, value));
                        break;
                    case "taglia":
                        conditions.add(new Condition("LTaglia", Operator.EQ, value));
                        break;
                    case "Sconto":
                        conditions.add(new Condition("Sconto", Operator.EQ, value));
                        break;
                    case "minPrice":
                        conditions.add(new Condition("MinPrice", Operator.GT, value));
                        break;
                    case "maxPrice":
                        conditions.add(new Condition("MaxPrice", Operator.LT, value));
                        break;
                    case "range":
                        conditions.add(new Condition("Range", Operator.RANGE, value));
                        break;
                    default:
                        break;
                }
            }
        }
        return conditions;
    }
}
