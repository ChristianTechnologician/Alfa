package Model.Carrello;

import Model.Gestione.Controller;
import Model.Gestione.InvalidRequestException;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

@WebServlet(name = "CarrelloServlet", value = "/carrello/*")
public class CarrelloServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/rimuovi":
                    // this parses the json
                    JSONObject jObj = new JSONObject(request.getParameter("rimozione"));
                    Iterator it = jObj.keys(); //gets all the keys
                    ArrayList<String> c = new ArrayList<>();
                    while (it.hasNext())
                    {
                        String key = (String) it.next(); // get key
                        Object o = jObj.get(key); // get value
                        c.add((String) o);
                        System.out.println(key + " : " +  o); // print the key and value
                    }
                    CarrelloDAO carrelloDAO = new CarrelloDAO();
                    boolean b = carrelloDAO.deleteElementoCarrello(c.get(1),Integer.parseInt(c.get(0)),Integer.parseInt(c.get(2)));
                    request.setAttribute("conferma", b);
                    break;
            }
        } catch (SQLException ex){
            log(ex.getMessage());
        }
    }
}
