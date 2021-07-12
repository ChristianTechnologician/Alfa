package Model.Carrello;

import Model.Gestione.Controller;
import Model.Gestione.InvalidRequestException;
import Model.Merce.Merce;
import Model.Merce.MerceDAO;
import Model.Ordine.Ordine;
import Model.Ordine.OrdineDAO;
import Model.Preferiti.PreferitiDAO;
import Model.Preferiti.PreferitiSession;
import Model.Utente.UtenteSession;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@WebServlet(name = "CarrelloServlet", value = "/carrello/*")
public class CarrelloServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/rimuovi":
                    // this parses the json
                    System.out.println("siamo qui");
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
                    System.out.println("welalala");
                    CarrelloDAO carrelloDAO = new CarrelloDAO();
                    System.out.println("azzo");
                    boolean b = carrelloDAO.deleteElementoCarrello(c.get(2),Integer.parseInt(c.get(0)),Integer.parseInt(c.get(3)));
                    System.out.println("pazzo");
                    CarrelloSession cs= (CarrelloSession) request.getSession().getAttribute("carrello");
                    System.out.println("oleee");
                    int i=0;
                    System.out.println("problema");
                    for (String s: cs.mCodice())
                    {
                        if(s.equals(c.get(2)))
                        {
                            break;
                        }
                        else
                        {
                            i++;
                        }
                    }
                    int j=0;
                    for (int x: cs.Fcodice())
                    {
                        if(x==Integer.parseInt(c.get(3)))
                        {
                            break;
                        }
                        else
                        {
                            j++;
                        }
                    }
                    int p=0;
                    for (int x: cs.Quantita())
                    {
                        if(x==Integer.parseInt(c.get(1)))
                        {
                            break;
                        }
                        else
                        {
                            p++;
                        }
                    }
                    System.out.println("opelala");
                    cs.Fcodice().remove(j);
                    cs.mCodice().remove(i);
                    cs.Quantita().remove(p);
                    request.setAttribute("conferma", b);
                    break;
                case "/aggiungi":
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
                        System.out.println("bro");
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
                                int r = car.getQuantita()+Integer.parseInt(stro.get(2));
                                carrelloSession.setQuantita(r);
                                carrelloDAOs.updateQuantita(r,ut.getId(),stro.get(3),Integer.parseInt(stro.get(4)));
                            }
                           /* int r = car.getQuantita()+1;
                            carrelloDAOs.updateQuantita(r,ut.getId(),stro.get(3),Integer.parseInt(stro.get(4)));
                            carrelloSession.setmCodice(stro.get(3));
                            carrelloSession.setFcodice(Integer.parseInt(stro.get(4)));
                            carrelloSession.setQuantita(1);*/
                            request.getSession().setAttribute("carrello",carrelloSession);
                        }else{
                            CarrelloSession carrelloSession = (CarrelloSession) request.getSession().getAttribute("carrello");
                            boolean verifica = false;
                            for(int g = 0; g<carrelloSession.mCodice().size();g++) {
                                if (carrelloSession.mCodice().get(g).equals(stro.get(3)) && carrelloSession.Fcodice().get(g) == Integer.parseInt(stro.get(4))) {
                                    int quantity = carrelloSession.Quantita().get(g);
                                    quantity += Integer.parseInt(stro.get(2));
                                    carrelloSession.Quantita().set(g,quantity);
                                    //controllare database possibili problemi oppure inutile?
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
                        /*
                        System.out.println(stro.get(3));
                        carrelloSession.setmCodice(stro.get(3));
                        System.out.println("robot");
                        System.out.println(Integer.parseInt(stro.get(4)));
                        carrelloSession.setFcodice(Integer.parseInt(stro.get(4)));
                        System.out.println("razzomissile");
                        carrelloSession.setQuantita();
                        System.out.println("dove");
                        request.getSession().removeAttribute("carrello");*/
                        request.getSession(true).setAttribute("carrello",carrelloSession);
                    }
                    break;
                case "/ordine":
                    CarrelloSession control = (CarrelloSession)request.getSession().getAttribute("carrello");
                    System.out.println("arrivati");
                    if(control.getRegistrato()==1) {
                        System.out.println("vamos");
                        UtenteSession utenteSession = (UtenteSession) request.getSession().getAttribute("accountSession");
                        CarrelloDAO carrelloDAOo = new CarrelloDAO();
                        List<Integer> Fcodici = carrelloDAOo.DoRetrieveFcodice(utenteSession.getId());
                        CarrelloSession carrelloSession = new CarrelloSession(utenteSession.getId(), 1);
                        carrelloSession.setListFcodice(Fcodici);
                        carrelloSession.setListQuantita(carrelloDAOo.DoRetrieveQuantita(utenteSession.getId()));
                        carrelloSession.setmCodice(carrelloDAOo.DoRetrieveCodici(utenteSession.getId()));
                        ArrayList<Merce> listamerci = new ArrayList<>();
                        MerceDAO md = new MerceDAO();
                        for (String s : carrelloSession.mCodice()) {
                            listamerci.add(md.doRetrieveByCode(s));
                        }
                        request.setAttribute("interi", carrelloSession);
                        request.setAttribute("merci", listamerci);
                        request.getSession(true).setAttribute("accountSession", utenteSession);
                        request.getRequestDispatcher("/WEB-INF/views/customer/ordine.jsp").forward(request, response);
                    }else{
                        request.getRequestDispatcher("/WEB-INF/views/customer/user.jsp").forward(request, response);
                    }
                    break;
            }
        } catch (SQLException ex){
            log(ex.getMessage());
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/acquista":
                    UtenteSession customerSession= (UtenteSession) request.getSession().getAttribute("accountSession");
                    List<Ordine> ordine = new ArrayList<>();
                    OrdineDAO oDAO = new OrdineDAO();
                    PreferitiDAO pD = new PreferitiDAO();
                    ordine = oDAO.DoRetriveByLast(customerSession.getId());
                    PreferitiSession ps = new PreferitiSession(customerSession.getId(),1);
                    ps.setCodici(pD.DoRetriveCodiciByUtente(customerSession.getId()));
                    request.getSession().setAttribute("preferiti",ps);
                    CarrelloDAO carrelloDAO = new CarrelloDAO();
                    List<Integer> Fcodici = carrelloDAO.DoRetrieveFcodice(customerSession.getId());
                    CarrelloSession carrelloSession = new CarrelloSession(customerSession.getId(),1);
                    carrelloSession.setListFcodice(Fcodici);
                    carrelloSession.setListQuantita(carrelloDAO.DoRetrieveQuantita(customerSession.getId()));
                    carrelloSession.setmCodice(carrelloDAO.DoRetrieveCodici(customerSession.getId()));
                    request.getSession().setAttribute("carrello",carrelloSession);
                    request.setAttribute("ordine", ordine);
                    String Via = request.getParameter("Via");
                    String NumeroCivico = request.getParameter("NumeroCivico");
                    String Citta = request.getParameter("Citta");
                    String Provincia = request.getParameter("Provincia");
                    //request.setAttribute("accountSession", customerSession);
                    Random generatore = new Random();
                    //List<String> d=new ArrayList<>();
                    String fattura="";
                    for (int i=0; i<5; i++)
                    {
                        fattura+=""+generatore.nextInt(10);
                        //d.add(String.valueOf(generatore.nextInt(10)));
                    }
                    //String fattura= String.valueOf(d);
                    int f=Integer.parseInt(fattura);
                    Ordine nuovo=new Ordine();
                    OrdineDAO od=new OrdineDAO();
                    nuovo.setNumeroFattura(f);
                    nuovo.setIdUtente(customerSession.getId());
                    nuovo.setVia(Via);
                    nuovo.setProvincia(Provincia);
                    nuovo.setCivico(Integer.parseInt(NumeroCivico));
                    nuovo.setCitta(Citta);
                    nuovo.setDate(LocalDate.now());
                    nuovo.setPrezzoTotale(Double.parseDouble(request.getParameter("total")));
                    nuovo.setStato(0);
                    nuovo.setIdCarrello(0);
                    String mer="";
                    //List<Merce> m= (List<Merce>) carrelloSession.mCodice();
                    for (String merc: carrelloSession.mCodice()) {
                        mer+=merc+",";
                    }
                    nuovo.setCodiceMerceAcquistata(mer);
                    System.out.println(nuovo.getNumeroFattura());
                    System.out.println(nuovo.getVia());
                    System.out.println(nuovo.getCivico());
                    System.out.println(nuovo.getCitta());
                    System.out.println(nuovo.getProvincia());
                    System.out.println(nuovo.getPrezzoTotale());
                    System.out.println(nuovo.getDate());
                    System.out.println(nuovo.getStato());
                    System.out.println(nuovo.getCodiceMerceAcquistata());
                    System.out.println(nuovo.getIdUtente());
                    System.out.println(nuovo.getIdCarrello());
                    od.insertOrdine(nuovo);
                    List<Ordine> ordines = new ArrayList<>();
                    OrdineDAO oDAOs = new OrdineDAO();
                    ordines = oDAOs.DoRetriveByLast(customerSession.getId());
                    request.setAttribute("ordine", ordines);
                    request.getRequestDispatcher("/WEB-INF/views/customer/profilo.jsp").forward(request, response);
                    break;
            }
        }catch (SQLException ex){
            log(ex.getMessage());
        }
    }
}
