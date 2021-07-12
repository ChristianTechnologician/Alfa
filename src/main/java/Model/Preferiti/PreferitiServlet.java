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
                        System.out.println(key + " : " +  o); // print the key and value
                    }
                    PreferitiDAO preferitiDAO = new PreferitiDAO();
                    PreferitiSession ps = (PreferitiSession) request.getSession().getAttribute("preferiti");
                    int p = 0;
                    int j = 0;
                    int i = 0;
                    boolean b=false;
                    System.out.println("oofofiewf");
                    if(Integer.parseInt(c.get(0))==1){
                        System.out.println("uiuiouo");
                        b = preferitiDAO.deleteElementoPreferiti(c.get(3), Integer.parseInt(c.get(1)), Integer.parseInt(c.get(4)));
                        for (String s : ps.mCodice()) {
                            if (s.equals(c.get(3))) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        for (int x : ps.Fcodice()) {
                            if (x == Integer.parseInt(c.get(4))) {
                                break;
                            } else {
                                j++;
                            }
                        }
                        for (int x : ps.Quantita()) {
                            if (x == Integer.parseInt(c.get(2))) {
                                break;
                            } else {
                                p++;
                            }
                        }
                    }else {
                        System.out.println("olololololo");
                        for (String s : ps.mCodice()) {
                            if (s.equals(c.get(3))) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        for (int x : ps.Fcodice()) {
                            if (x == Integer.parseInt(c.get(4))) {
                                break;
                            } else {
                                j++;
                            }
                        }
                        for (int q=0;q<ps.Quantita().size();q++) {
                            if (ps.Quantita().get(q)== Integer.parseInt(c.get(2))) {
                                System.out.println("11111");
                                break;
                            }
                                System.out.println("22222");
                                p++;
                        }
                    }
                    System.out.println("buuurundi");
                    System.out.println(p);
                    System.out.println(ps.Quantita().get(0));
                    ps.Quantita().remove(p);
                    System.out.println("rrrrr");
                    ps.Fcodice().remove(j);
                    System.out.println("oooooo");
                    ps.mCodice().remove(i);
                    System.out.println("xertos");
                    request.setAttribute("conferma", b);
                    break;
                case "/aggiungiPreferiti":
                    JSONObject jObject = new JSONObject(request.getParameter("aggiunta"));
                    Iterator iter = jObject.keys(); //gets all the keys
                    ArrayList<String> str = new ArrayList<>();
                    while (iter.hasNext()) {
                        String key = (String) iter.next(); // get key
                        Object o = jObject.get(key); // get value
                        str.add((String) o);
                        System.out.println(key + " : " +  o); // print the key and value
                    }
                    System.out.println("yahoooo");
                    if(Integer.parseInt(str.get(0))==1) {
                        PreferitiDAO preferitiDAO1 = new PreferitiDAO();
                        UtenteSession ut = (UtenteSession) request.getSession().getAttribute("accountSession");
                        Preferiti pref = preferitiDAO1.findElement(ut.getId(),str.get(3),Integer.parseInt(str.get(4)));
                        if(pref != null){
                            int r = pref.getNumeroPreferiti()+Integer.parseInt(str.get(2));
                            preferitiDAO1.updateQuantita(r,ut.getId(),str.get(3),Integer.parseInt(str.get(4)));
                            PreferitiSession preferitiSession = (PreferitiSession) request.getSession().getAttribute("preferiti");
                            preferitiSession.setmCodice(str.get(3));
                            preferitiSession.setFcodice(Integer.parseInt(str.get(4)));
                            preferitiSession.setQuantita(Integer.parseInt(str.get(2)));
                            request.getSession().setAttribute("preferiti",preferitiSession);
                        }else{
                            PreferitiSession preferitiSession = (PreferitiSession) request.getSession().getAttribute("preferiti");
                            preferitiSession.setmCodice(str.get(3));
                            preferitiSession.setFcodice(Integer.parseInt(str.get(4)));
                            preferitiSession.setQuantita(Integer.parseInt(str.get(2)));
                            request.getSession().setAttribute("preferiti",preferitiSession);
                            preferitiDAO1.insertElemento(Integer.parseInt(str.get(2)),ut.getId(), str.get(3),Integer.parseInt(str.get(4)));
                        }
                    }else {
                        PreferitiSession preferitiSession = (PreferitiSession) request.getSession().getAttribute("preferiti");
                        System.out.println(str.get(3));
                        System.out.println(Integer.parseInt(str.get(4)));
                        System.out.println(Integer.parseInt(str.get(2)));
                        preferitiSession.setmCodice(str.get(3));
                        preferitiSession.setFcodice(Integer.parseInt(str.get(4)));
                        preferitiSession.setQuantita(Integer.parseInt(str.get(2)));
                        request.getSession().setAttribute("preferiti",preferitiSession);
                    }
                    break;
                /*case"/aggiungiCarrello":
                    JSONObject jObjecto = new JSONObject(request.getParameter("aggiunta"));
                    Iterator itero = jObjecto.keys(); //gets all the keys
                    ArrayList<String> stro = new ArrayList<>();
                    while (itero.hasNext()) {
                        String key = (String) itero.next(); // get key
                        Object o = jObjecto.get(key); // get value
                        stro.add((String) o);
                        System.out.println(key + " : " +  o); // print the key and value
                    }
                    if(Integer.parseInt(stro.get(0))==1) {
                    CarrelloDAO carrelloDAO = new CarrelloDAO();
                    UtenteSession ut = (UtenteSession) request.getSession().getAttribute("accountSession");
                    Carrello car = carrelloDAO.findElement(ut.getId(),stro.get(2),Integer.parseInt(stro.get(3)));
                    if(car != null){
                        int r = car.getQuantita()+Integer.parseInt(stro.get(2));
                        carrelloDAO.updateQuantita(r,ut.getId(),stro.get(3),Integer.parseInt(stro.get(4)));
                        CarrelloSession carrelloSession = (CarrelloSession) request.getSession().getAttribute("carrello");
                        carrelloSession.setmCodice(stro.get(3));
                        carrelloSession.setFcodice(Integer.parseInt(stro.get(4)));
                        carrelloSession.setQuantita(Integer.parseInt(stro.get(2)));
                        request.getSession().removeAttribute("carrello");
                        request.getSession().setAttribute("carrello",carrelloSession);
                    }else{
                        CarrelloSession carrelloSession = (CarrelloSession) request.getSession().getAttribute("carrello");
                        carrelloSession.setmCodice(stro.get(3));
                        carrelloSession.setFcodice(Integer.parseInt(stro.get(4)));
                        carrelloSession.setQuantita(Integer.parseInt(stro.get(2)));
                        carrelloDAO.insertElemento(ut.getId(), stro.get(3),Integer.parseInt(stro.get(4)),Integer.parseInt(stro.get(2)));
                    }
                }else {
                    CarrelloSession carrelloSession = (CarrelloSession) request.getSession().getAttribute("carrello");
                    carrelloSession.setmCodice(stro.get(3));
                    carrelloSession.setFcodice(Integer.parseInt(stro.get(4)));
                    carrelloSession.setQuantita(Integer.parseInt(stro.get(2)));
                        request.getSession().removeAttribute("carrello");
                    request.getSession().setAttribute("carrello",carrelloSession);
                }
                    break;*/
                case "/aggiungiCarrello":
                    JSONObject jObjecto = new JSONObject(request.getParameter("aggiunta"));
                    Iterator itero = jObjecto.keys(); //gets all the keys
                    ArrayList<String> stro = new ArrayList<>();
                    while (itero.hasNext()) {
                        String key = (String) itero.next(); // get key
                        Object o = jObjecto.get(key); // get value
                        stro.add((String) o);
                        System.out.println(key + " : " +  o); // print the key and value
                    }
                    System.out.println("rieccoci");
                    if(Integer.parseInt(stro.get(0))==1) {
                        CarrelloDAO carrelloDAOs = new CarrelloDAO();
                        UtenteSession ut = (UtenteSession) request.getSession().getAttribute("accountSession");
                        Carrello car = carrelloDAOs.findElement(ut.getId(),stro.get(3),Integer.parseInt(stro.get(4)));
                        if(car != null){
                            System.out.println("bro");
                            int r = car.getQuantita()+Integer.parseInt(stro.get(2));
                            carrelloDAOs.updateQuantita(r,ut.getId(),stro.get(3),Integer.parseInt(stro.get(4)));
                            CarrelloSession carrelloSession = (CarrelloSession) request.getSession().getAttribute("carrello");
                            carrelloSession.setmCodice(stro.get(3));
                            carrelloSession.setFcodice(Integer.parseInt(stro.get(4)));
                            carrelloSession.setQuantita(Integer.parseInt(stro.get(2)));
                            request.getSession().setAttribute("carrello",carrelloSession);
                        }else{
                            CarrelloSession carrelloSession = (CarrelloSession) request.getSession().getAttribute("carrello");
                            carrelloSession.setmCodice(stro.get(3));
                            System.out.println(Integer.parseInt(stro.get(4)));
                            carrelloSession.setFcodice(Integer.parseInt(stro.get(4)));
                            carrelloSession.setQuantita(Integer.parseInt(stro.get(2)));
                            request.getSession().setAttribute("carrello",carrelloSession);
                            carrelloDAOs.insertElemento(ut.getId(), stro.get(3),Integer.parseInt(stro.get(4)),Integer.parseInt(stro.get(2)));
                        }
                    }else {
                        System.out.println("mazinga");
                        CarrelloSession carrelloSession = (CarrelloSession) request.getSession().getAttribute("carrello");
                        System.out.println(stro.get(3));
                        carrelloSession.setmCodice(stro.get(3));
                        System.out.println("robot");
                        carrelloSession.setFcodice(Integer.parseInt(stro.get(4)));
                        System.out.println("razzomissile");
                        carrelloSession.setQuantita(Integer.parseInt(stro.get(2)));
                        System.out.println("dove");
                        request.getSession(true).setAttribute("carrello",carrelloSession);
                    }
                    break;
            }
        } catch (SQLException ex) {
            log(ex.getMessage());
        }
    }
}