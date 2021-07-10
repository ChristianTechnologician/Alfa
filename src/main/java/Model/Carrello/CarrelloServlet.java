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
                    JSONObject jObj = new JSONObject(request.getParameter("rimozione"));
                    Iterator it = jObj.keys(); //gets all the keys
                    ArrayList<String> c = new ArrayList<>();
                    while (it.hasNext())
                    {
                        String key = (String) it.next(); // get key
                        Object o = jObj.get(key); // get value
                        c.add((String) o);
                        //System.out.println(key + " : " +  o); // print the key and value
                    }
                    CarrelloDAO carrelloDAO = new CarrelloDAO();
                    boolean b = carrelloDAO.deleteElementoCarrello(c.get(1),Integer.parseInt(c.get(0)),Integer.parseInt(c.get(2)));
                    CarrelloSession cs= (CarrelloSession) request.getSession().getAttribute("carrello");
                    int i=0;
                    for (String s: cs.mCodice())
                    {
                        if(s.equals(c.get(1)))
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
                        if(x==Integer.parseInt(c.get(2)))
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
                        if(x==Integer.parseInt(c.get(3)))
                        {
                            break;
                        }
                        else
                        {
                            p++;
                        }
                    }
                    cs.Fcodice().remove(j);
                    cs.mCodice().remove(i);
                    cs.Quantita().remove(p);
                    request.setAttribute("conferma", b);
                    break;

                case "/ordine":
                    UtenteSession utenteSession= (UtenteSession) request.getSession().getAttribute("accountSession");
                    CarrelloDAO carrelloDAOo = new CarrelloDAO();
                    List<Integer> Fcodici = carrelloDAOo.DoRetrieveFcodice(utenteSession.getId());
                    CarrelloSession carrelloSession=new CarrelloSession(utenteSession.getId());
                    carrelloSession.setListFcodice(Fcodici);
                    carrelloSession.setListQuantita(carrelloDAOo.DoRetrieveQuantita(utenteSession.getId()));
                    carrelloSession.setmCodice(carrelloDAOo.DoRetrieveCodici(utenteSession.getId()));
                    ArrayList<Merce> listamerci=new ArrayList<>();
                    MerceDAO md=new MerceDAO();
                    for (String s: carrelloSession.mCodice())
                    {
                        listamerci.add(md.doRetrieveByCode(s));
                    }
                    request.setAttribute("merci", listamerci);
                    request.getSession(true).setAttribute("accountSession", utenteSession);
                    request.getRequestDispatcher("/WEB-INF/views/customer/ordine.jsp").forward(request, response);
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
                    PreferitiSession ps = new PreferitiSession(customerSession.getId());
                    ps.setCodici(pD.DoRetriveCodiciByUtente(customerSession.getId()));
                    request.getSession().getAttribute("preferiti");
                    CarrelloDAO carrelloDAO = new CarrelloDAO();
                    List<Integer> Fcodici = carrelloDAO.DoRetrieveFcodice(customerSession.getId());
                    CarrelloSession carrelloSession = new CarrelloSession(customerSession.getId());
                    carrelloSession.setListFcodice(Fcodici);
                    carrelloSession.setListQuantita(carrelloDAO.DoRetrieveQuantita(customerSession.getId()));
                    carrelloSession.setmCodice(carrelloDAO.DoRetrieveCodici(customerSession.getId()));
                    request.getSession().getAttribute("carrello");
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
                    request.getRequestDispatcher("/WEB-INF/views/customer/profilo.jsp").forward(request, response);
                    break;
            }
        }catch (SQLException ex){
            log(ex.getMessage());
        }
    }
}
