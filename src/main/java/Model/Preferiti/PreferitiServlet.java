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
                        System.out.println("insert");
                        if(pref != null){
                            System.out.println("bum");
                            //int r = pref.getNumeroPreferiti()+Integer.parseInt(str.get(2));
                            //preferitiDAO1.updateQuantita(r,ut.getId(),str.get(3),Integer.parseInt(str.get(4)));
                            PreferitiSession preferitiSession = (PreferitiSession) request.getSession().getAttribute("preferiti");
                            boolean verifica = false;
                            for(int g = 0; g<preferitiSession.mCodice().size();g++){
                                if(preferitiSession.mCodice().get(g).equals(str.get(3))&&preferitiSession.Fcodice().get(g)==Integer.parseInt(str.get(4))){
                                    System.out.println("0");
                                    int quantity = preferitiSession.Quantita().get(g);// + pref.getNumeroPreferiti();
                                    quantity += Integer.parseInt(str.get(2));
                                    preferitiSession.Quantita().set(g,quantity);
                                    preferitiDAO1.updateQuantita(quantity,ut.getId(),str.get(3),Integer.parseInt(str.get(4)));
                                    verifica=true;
                                    System.out.println("1");
                                    break;
                                }
                            }
                            if(!verifica) {
                                System.out.println("3");
                                preferitiSession.setmCodice(str.get(3));
                                preferitiSession.setFcodice(Integer.parseInt(str.get(4)));
                                preferitiSession.setQuantita(Integer.parseInt(str.get(2)));
                                int quantity = Integer.parseInt(str.get(2)) + pref.getNumeroPreferiti();
                                preferitiDAO1.updateQuantita(quantity,ut.getId(),str.get(3),Integer.parseInt(str.get(4)));
                                System.out.println("2");
                            }
                            request.getSession().setAttribute("preferiti",preferitiSession);
                        }else{
                            PreferitiSession preferitiSession = (PreferitiSession) request.getSession().getAttribute("preferiti");
                            boolean verifica = false;
                            for(int g = 0; g<preferitiSession.mCodice().size();g++){
                                System.out.println("5");
                                if(preferitiSession.mCodice().get(g).equals(str.get(3))&&preferitiSession.Fcodice().get(g)==Integer.parseInt(str.get(4))){
                                    System.out.println("4");
                                    System.out.println(preferitiSession.Quantita().get(g));
                                    int quantity = preferitiSession.Quantita().get(g);
                                    quantity += Integer.parseInt(str.get(2));
                                    preferitiSession.Quantita().set(g,quantity);
                                    System.out.println("535363");
                                    preferitiDAO1.insertElemento(Integer.parseInt(str.get(2)), ut.getId(), str.get(3), Integer.parseInt(str.get(4)));
                                    //preferitiDAO1.updateQuantita(quantity,ut.getId(),str.get(3),Integer.parseInt(str.get(4)));
                                    verifica=true;
                                    System.out.println("6");
                                    break;
                                }
                            }
                            if(!verifica) {
                                System.out.println("90");
                                preferitiSession.setmCodice(str.get(3));
                                preferitiSession.setFcodice(Integer.parseInt(str.get(4)));
                                preferitiSession.setQuantita(Integer.parseInt(str.get(2)));
                                System.out.println(str.get(3));
                                System.out.println(Integer.parseInt(str.get(4)));
                                System.out.println(Integer.parseInt(str.get(2)));
                                System.out.println(ut.getId());
                                System.out.println("44");
                                preferitiDAO1.insertElemento(Integer.parseInt(str.get(2)), ut.getId(), str.get(3), Integer.parseInt(str.get(4)));
                                System.out.println("99");
                            }
                            request.getSession().setAttribute("preferiti",preferitiSession);
                        }
                    }else {
                        PreferitiSession preferitiSession = (PreferitiSession) request.getSession().getAttribute("preferiti");
                        boolean verifica = false;
                        System.out.println("80");
                        for(int g = 0; g<preferitiSession.mCodice().size();g++){
                            System.out.println("88");
                            if(preferitiSession.mCodice().get(g).equals(str.get(3))&&preferitiSession.Fcodice().get(g)==Integer.parseInt(str.get(4))){
                                int quantity = preferitiSession.Quantita().get(g);
                                quantity += Integer.parseInt(str.get(2));
                                preferitiSession.Quantita().set(g,quantity);
                                verifica=true;
                                System.out.println("89");
                                break;
                            }
                        }
                        if(!verifica) {
                            System.out.println("78");
                            preferitiSession.setmCodice(str.get(3));
                            preferitiSession.setFcodice(Integer.parseInt(str.get(4)));
                            preferitiSession.setQuantita(Integer.parseInt(str.get(2)));
                            System.out.println("79");
                        }
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
                            CarrelloSession carrelloSession = (CarrelloSession) request.getSession().getAttribute("carrello");
                            boolean verifica = false;
                            for(int g = 0; g<carrelloSession.mCodice().size();g++) {
                                if (carrelloSession.mCodice().get(g).equals(stro.get(3)) && carrelloSession.Fcodice().get(g) == Integer.parseInt(stro.get(4))) {
                                    int quantity = carrelloSession.Quantita().get(g);
                                    quantity += Integer.parseInt(stro.get(2));
                                    carrelloSession.Quantita().set(g,quantity);
                                    carrelloDAOs.updateQuantita(quantity,ut.getId(),stro.get(3),Integer.parseInt(stro.get(4)));
                                    verifica=true;
                                    break;
                                }
                            }
                            if(!verifica) {
                                carrelloSession.setmCodice(stro.get(3));
                                carrelloSession.setFcodice(Integer.parseInt(stro.get(4)));
                                carrelloSession.setQuantita(Integer.parseInt(stro.get(2)));
                                int r = car.getQuantita()+Integer.parseInt(stro.get(2));
                                carrelloDAOs.updateQuantita(r,ut.getId(),stro.get(3),Integer.parseInt(stro.get(4)));
                            }
                            request.getSession().setAttribute("carrello",carrelloSession);
                        }else{
                            CarrelloSession carrelloSession = (CarrelloSession) request.getSession().getAttribute("carrello");
                            boolean verifica = false;
                            for(int g = 0; g<carrelloSession.mCodice().size();g++) {
                                if (carrelloSession.mCodice().get(g).equals(stro.get(3)) && carrelloSession.Fcodice().get(g) == Integer.parseInt(stro.get(4))) {
                                    int quantity = carrelloSession.Quantita().get(g);
                                    quantity += Integer.parseInt(stro.get(2));
                                    carrelloSession.Quantita().set(g,quantity);
                                    carrelloDAOs.insertElemento(ut.getId(), stro.get(3),Integer.parseInt(stro.get(4)),Integer.parseInt(stro.get(2)));
                                    verifica=true;
                                    break;
                                }
                            }
                            if(!verifica) {
                                carrelloSession.setmCodice(stro.get(3));
                                carrelloSession.setFcodice(Integer.parseInt(stro.get(4)));
                                carrelloSession.setQuantita(Integer.parseInt(stro.get(2)));
                                carrelloDAOs.insertElemento(ut.getId(), stro.get(3),Integer.parseInt(stro.get(4)),Integer.parseInt(stro.get(2)));
                            }
                            request.getSession().setAttribute("carrello",carrelloSession);
                        }
                    }else {
                        System.out.println("mazinga");
                        CarrelloSession carrelloSession = (CarrelloSession) request.getSession().getAttribute("carrello");
                        boolean verifica = false;
                        for(int g = 0; g<carrelloSession.mCodice().size();g++) {
                            if (carrelloSession.mCodice().get(g).equals(stro.get(3)) && carrelloSession.Fcodice().get(g) == Integer.parseInt(stro.get(4))) {
                                int quantity = carrelloSession.Quantita().get(g);
                                quantity += Integer.parseInt(stro.get(2));
                                carrelloSession.Quantita().set(g,quantity);
                                verifica=true;
                                break;
                            }
                        }
                        if(!verifica) {
                            carrelloSession.setmCodice(stro.get(3));
                            carrelloSession.setFcodice(Integer.parseInt(stro.get(4)));
                            carrelloSession.setQuantita(Integer.parseInt(stro.get(2)));
                        }
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