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
                    case "fullName":
                        conditions.add(new Condition("fullName", Operator.MATCH, value));
                        break;
                    case "countryId":
                        conditions.add(new Condition("country_fk", Operator.EQ, value));
                        break;
                    case "categoryId":
                        conditions.add(new Condition("category_fk", Operator.EQ, value));
                        break;
                    case "sinPrice":
                        conditions.add(new Condition("price", Operator.GT, value));
                        break;
                    case "maxPrice":
                        conditions.add(new Condition("price", Operator.LT, value));
                        break;
                    default:
                        break;
                }
            }
        }
        return conditions;
    }
}
