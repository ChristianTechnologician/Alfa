package Model.Preferiti;

import Model.Carrello.Carrello;
import Model.Carrello.CarrelloDAO;
import Model.Carrello.CarrelloSession;
import Model.Gestione.Controller;
import Model.Merce.Merce;
import Model.Merce.MerceDAO;
import Model.Utente.UtenteSession;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "PreferitiServlet", value = "/preferiti/*")
public class PreferitiServlet extends Controller {

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
                    while (it.hasNext()) {
                        String key = (String) it.next(); // get key
                        Object o = jObj.get(key); // get value
                        c.add((String) o);
                        //System.out.println(key + " : " +  o); // print the key and value
                    }
                    PreferitiDAO preferitiDAO = new PreferitiDAO();
                    boolean b = preferitiDAO.deleteElementoPreferiti(c.get(1), Integer.parseInt(c.get(0)), Integer.parseInt(c.get(2)));
                    PreferitiSession ps = (PreferitiSession) request.getSession().getAttribute("preferiti");
                    int i = 0;
                    for (String s : ps.mCodice()) {
                        if (s.equals(c.get(1))) {
                            break;
                        } else {
                            i++;
                        }
                    }
                    int j = 0;
                    for (int x : ps.Fcodice()) {
                        if (x == Integer.parseInt(c.get(2))) {
                            break;
                        } else {
                            j++;
                        }
                    }
                    /*int p = 0;
                    for (int x : ps.Quantita()) {
                        if (x == Integer.parseInt(c.get(3))) {
                            break;
                        } else {
                            p++;
                        }
                    }*/
                    ps.Fcodice().remove(j);
                    ps.mCodice().remove(i);
                    request.setAttribute("conferma", b);
                    break;
                case "/aggiungiCarrello":
                    JSONObject jObject = new JSONObject(request.getParameter("aggiunta"));
                    Iterator iter = jObject.keys(); //gets all the keys
                    ArrayList<String> str = new ArrayList<>();
                    while (iter.hasNext()) {
                        String key = (String) iter.next(); // get key
                        Object o = jObject.get(key); // get value
                        str.add((String) o);
                        //System.out.println(key + " : " +  o); // print the key and value
                    }
                    if(Integer.parseInt(str.get(0))==1) {
                        CarrelloDAO carrelloDAO = new CarrelloDAO();
                        UtenteSession ut = (UtenteSession) request.getSession().getAttribute("accountSession");
                        Carrello car = carrelloDAO.findElement(ut.getId(),str.get(2),Integer.parseInt(str.get(3)));
                        if(car != null){
                            int r = car.getQuantita()+1;
                            carrelloDAO.updateQuantita(r,ut.getId(),str.get(2),Integer.parseInt(str.get(3)));
                            CarrelloSession carrelloSession = (CarrelloSession) request.getSession().getAttribute("carrello");
                            carrelloSession.setmCodice(str.get(2));
                            carrelloSession.setFcodice(Integer.parseInt(str.get(3)));
                            carrelloSession.setQuantita(1);
                            request.getSession().setAttribute("carrello",carrelloSession);
                        }else{
                            carrelloDAO.insertElemento(ut.getId(), str.get(2),Integer.parseInt(str.get(3)),1);
                        }
                    }else {
                        CarrelloSession carrelloSession = (CarrelloSession) request.getSession().getAttribute("carrello");
                        carrelloSession.setmCodice(str.get(2));
                        carrelloSession.setFcodice(Integer.parseInt(str.get(3)));
                        carrelloSession.setQuantita(1);
                        request.getSession().setAttribute("carrello",carrelloSession);
                    }
                    /*if(Integer.parseInt(str.get(0))==1) {
                        CarrelloDAO carrelloDAO = new CarrelloDAO();
                        carrelloDAO.insertElemento(carrelloSession.getIDutente(),str.get(2),Integer.parseInt(str.get(3)),1);
                    }*/
                    break;
            }
        } catch (SQLException ex) {
            log(ex.getMessage());
        }
    }
}