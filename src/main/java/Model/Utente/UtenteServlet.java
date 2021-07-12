package Model.Utente;

import Model.Carrello.Carrello;
import Model.Carrello.CarrelloDAO;
import Model.Carrello.CarrelloGuest;
import Model.Carrello.CarrelloSession;
import Model.Colore.Colore;
import Model.Colore.ColoreDAO;
import Model.Fornitura.Fornitura;
import Model.Fornitura.FornituraDAO;
import Model.Gestione.*;
import Model.Merce.Merce;
import Model.Merce.MerceDAO;
import Model.Ordine.Ordine;
import Model.Ordine.OrdineDAO;
import Model.Preferiti.Preferiti;
import Model.Preferiti.PreferitiDAO;
import Model.Preferiti.PreferitiSession;
import com.mysql.cj.Session;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "UtenteServlet", value = "/utente/*")
public class UtenteServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
           String path = getPath(request);//(request.getPathInfo() != null) ? request.getPathInfo() : "/";
           switch (path) {
               case "/home":
                   PreferitiSession preferitiGuest = new PreferitiSession(0,0);
                   request.getSession(true).setAttribute("preferiti", preferitiGuest);
                   CarrelloSession carrelloGuest = new CarrelloSession(0,0);
                   request.getSession(true).setAttribute("carrello", carrelloGuest);
                   request.getRequestDispatcher("/WEB-INF/HomePage.jsp").forward(request, response);
                   break;
               case "/homePage":
                   request.getRequestDispatcher("/WEB-INF/HomePage.jsp").forward(request, response);
                   break;
               case "/visualizza":
                   authorize(request.getSession(false));
                   //request.setAttribute("back",view("crm/home"));
                   //validate(CommonValidator.validatePage(request));
                   //int page = parsePage(request);
                   Paginatore paginator = new Paginatore(1, 3);
                   UtenteDAO ud = new UtenteDAO();
                   int size = ud.countAll();
                   request.setAttribute("pages", paginator.getPages(size));
                   List<Utente> admin = ud.fetchAdmin();
                   System.out.println(admin.get(0).getId());
                   request.setAttribute("admin", admin);
                   List<Utente> utenti = ud.fetchUtenti(paginator);
                   request.setAttribute("utenti", utenti);
                   request.getRequestDispatcher(view("crm/utenti")).forward(request, response);
                   break;
               case "/utente":
                   authorize(request.getSession(false));
                   request.getRequestDispatcher(view("crm/utente")).forward(request, response);
                   break;
               case "/visualizzaUtente":
                   authorize(request.getSession(false));
                   String eml = request.getParameter("email");
                   UtenteDAO utd = new UtenteDAO();
                   Utente utente = utd.fetchUtente(eml);
                   request.setAttribute("utente", utente);
                   request.getRequestDispatcher(view("crm/resultUtente")).forward(request, response);
                   break;
               case "/signinCliente":
                   request.getRequestDispatcher(view("account/loginform")).forward(request, response);
                   break;
               case "/create":
                   authorize(request.getSession(false));
                   request.getRequestDispatcher(view("crm/account")).forward(request, response);
                   break;
               case "/show":
                   UtenteDAO utenteDAO = new UtenteDAO();
                   authorize(request.getSession(false));
                   validate(CommonValidator.validateId(request));
                   int id = Integer.parseInt(request.getParameter("id"));
                   Optional<Utente> optUtente = utenteDAO.fetchUtente(id);
                   String email = request.getParameter("email");
                   if (optUtente.isPresent()) {
                       request.setAttribute("utente", optUtente.get());
                       request.getRequestDispatcher(view("crm/account")).forward(request, response);
                   } else {
                       notFound();
                   }
                   break;
               case "/signinAdmin":
                   request.getRequestDispatcher(view("crm/secret")).forward(request, response);
                   break;
               case "/signup":
                   request.getRequestDispatcher(view("site/signup")).forward(request, response);
                   break;
               case "/profile": //show profile(cliente)
                   UtenteDAO udao = new UtenteDAO();
                   int profileId = getAccountSession(request.getSession(false)).getId();
                   Optional<Utente> profileUtente = udao.fetchUtente(profileId);
                   if (profileUtente.isPresent()) {
                       request.setAttribute("profile", profileUtente.get());
                       request.getRequestDispatcher(view("site/profile")).forward(request, response);
                   } else {
                       notFound();
                   }
                   break;
               case "/profileAdmin": //show profile(amministratore)
                   UtenteDAO uDAO = new UtenteDAO();
                   int profileID = getAccountSession(request.getSession(false)).getId();
                   Optional<Utente> profileU = uDAO.fetchUtente(profileID);
                   if (profileU.isPresent()) {
                       request.setAttribute("profileAdmin", profileU.get());
                       request.getRequestDispatcher(view("crm/profileAdmin")).forward(request, response);
                   } else {
                       notFound();
                   }
                   break;
               case "/logout":
                   HttpSession session = request.getSession(false);
                   authenticate(session);
                   UtenteSession utenteSession = (UtenteSession) session.getAttribute("accountSession");
                   String redirect = utenteSession.isAdmin() ? "/WEB-INF/views/crm/secret.jsp" : "/WEB-INF/views/customer/user.jsp";
                   session.removeAttribute("accountSession");
                   session.removeAttribute("preferiti");
                   session.removeAttribute("carrello");
                   //session.invalidate();
                   PreferitiSession preferitiGuests = new PreferitiSession(0,0);
                   request.getSession(true).setAttribute("preferiti", preferitiGuests);
                   CarrelloSession carrelloGuests = new CarrelloSession(0,0);
                   request.getSession(true).setAttribute("carrello", carrelloGuests);
                   request.getRequestDispatcher(redirect).forward(request, response);
                   break;
               case "/user":
                   UtenteSession us = (UtenteSession) request.getSession(false).getAttribute("accountSession");
                   if (us != null) {
                       List<Ordine> ordine = new ArrayList<>();
                       OrdineDAO oDAO = new OrdineDAO();
                       ordine = oDAO.DoRetriveByLast(us.getId());
                       request.setAttribute("ordine", ordine);
                       RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/profilo.jsp");
                       dispatcher.forward(request, response);
                   } else {
                       RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/user.jsp");
                       dispatcher.forward(request, response);
                   }
                   break;
               case "/preferiti":
               List<Merce> me = new ArrayList<>();
               List<Colore> colore = new ArrayList<>();
               ColoreDAO coloreDAO = new ColoreDAO();
               MerceDAO mDAO = new MerceDAO();
               List<Fornitura> fo = new ArrayList<>();
               List<Integer> quantita = new ArrayList<>();
               FornituraDAO fornituraDAO = new FornituraDAO();
               PreferitiDAO preferitiDAO = new PreferitiDAO();
               PreferitiSession preferiti = (PreferitiSession) request.getSession(false).getAttribute("preferiti");
              /* if(preferiti.getRegistrato()==1) {
                   List<Preferiti> preferitiSession = preferitiDAO.DoRetrieveByUtente(preferiti.getIdUtente());
                   if (preferitiSession == null || preferitiSession.isEmpty()) {
                       for (int q = 0; q < preferiti.mCodice().size(); q++) {
                           preferitiDAO.insertElemento(preferiti.Quantita().get(q), preferiti.getIdUtente(), preferiti.mCodice().get(q), preferiti.Fcodice().get(q));
                       }
                   } else {
                       for (int i = 0; i < preferitiSession.size(); i++) {
                           for (int q = 0; q < preferiti.mCodice().size(); q++) {
                               if (preferitiSession.get(i).getmCodice().equals(preferiti.mCodice().get(q)) && preferitiSession.get(i).getFcodice() == preferiti.Fcodice().get(q)) {
                                   preferiti.Fcodice().remove(q);
                                   preferiti.mCodice().remove(q);
                                   preferiti.Quantita().remove(q);
                               }
                           }
                       }
                       for (int q = 0; q < preferiti.mCodice().size(); q++) {
                           preferitiDAO.insertElemento(preferiti.Quantita().get(q), preferiti.getIdUtente(), preferiti.mCodice().get(q), preferiti.Fcodice().get(q));
                       }
                   }
               }*/
               System.out.println(preferiti);
               if (preferiti.getRegistrato()==1) {
                   System.out.println("2");
                   if (preferiti.mCodice() == null/*cg.mCodice().isEmpty()*/) {
                       System.out.println("5");
                       me = null;
                   } else {
                       for (String s : preferiti.mCodice()) {
                           System.out.println("ok");
                           me.add(mDAO.doRetrieveByCode(s));
                           System.out.println("pt");
                       }
                   }
                   System.out.println("qui");
                   if (preferiti.Fcodice() == null/*cg.Fcodice().isEmpty()*/) {
                       fo = null;
                       colore=null;
                       quantita=null;
                       System.out.println("8");
                   }else {
                       for (int i : preferiti.Fcodice()) {
                           System.out.println("super");
                           fo.add(fornituraDAO.doRetrieveByUtenteCode(i));
                           System.out.println("fatto");
                       }
                       for (int x = 0; x < fo.size(); x++) {
                           colore.add(coloreDAO.doRetrieveByCode(fo.get(x).getCodColore()));
                           System.out.println("risolto");
                       }
                       for (int w :preferiti.Quantita()) {
                           quantita.add(w);
                           System.out.println("risolto");
                       }
                   }
               } else {
                   System.out.println("3");
                   System.out.println("4");
                   List<String> str = new ArrayList<>();
                   System.out.println("qui");
                   if (preferiti.mCodice() == null/*cg.mCodice().isEmpty()*/) {
                       System.out.println("5");
                       me = null;
                   } else {
                       System.out.println("forse");
                       str = preferiti.mCodice();
                       for (String s : str) {
                           System.out.println(s);
                           me.add(mDAO.doRetrieveByCode(s));
                           System.out.println("6");
                       }
                   }
                   System.out.println("7");
                   if (preferiti.Fcodice() == null/*cg.Fcodice().isEmpty()*/) {
                       fo = null;
                       colore=null;
                       System.out.println("8");
                   } else {
                       System.out.println("9");
                       for (int i : preferiti.Fcodice()) {
                           System.out.println("robertoooo");
                           fo.add(fornituraDAO.doRetrieveByUtenteCode(i));
                       }
                       for (int x = 0; x < fo.size(); x++) {
                           System.out.println(fo.get(x).getCodColore());
                           System.out.println(fo.get(x).getIdentificatore());
                           colore.add(coloreDAO.doRetrieveByCode(fo.get(x).getCodColore()));
                           System.out.println("risolto");
                       }
                       for (int w :preferiti.Quantita()) {
                           System.out.println(w);
                           quantita.add(w);
                           System.out.println("risolto");
                       }
                   }
               }
               for(int i = 0;i<preferiti.Fcodice().size();i++){
                   System.out.println(preferiti.Fcodice().get(i));
               }
               for(int i = 0;i<preferiti.Fcodice().size();i++){
                   System.out.println(preferiti.Fcodice().get(i));
               }
               System.out.println(me);
               System.out.println(fo);
               System.out.println(colore);
               request.setAttribute("preferitiMerce",me);
               request.setAttribute("preferitiFornitura",fo);
               request.setAttribute("preferitiColore",colore);
               request.setAttribute("preferitiQuantita",quantita);
               System.out.println("rssaxdwqcbh");
               request.getRequestDispatcher("/WEB-INF/views/customer/favorites.jsp").forward(request,response);
               break;
               case "/carrello":
                   System.out.println("1");
                   List<Merce> mes = new ArrayList<>();
                   List<Colore> colores = new ArrayList<>();
                   ColoreDAO coloreDAOs = new ColoreDAO();
                   MerceDAO mDAOs = new MerceDAO();
                   List<Fornitura> fos = new ArrayList<>();
                   FornituraDAO fornituraDAOs = new FornituraDAO();
                   CarrelloSession carrello = (CarrelloSession) request.getSession(false).getAttribute("carrello");
                   System.out.println(carrello);
                   if (carrello.getRegistrato()==1) {
                       System.out.println("2");
                       if (carrello.mCodice() == null/*cg.mCodice().isEmpty()*/) {
                           System.out.println("5");
                           mes = null;
                       } else {
                           for (String s : carrello.mCodice()) {
                               System.out.println("ok");
                               mes.add(mDAOs.doRetrieveByCode(s));
                               System.out.println("pt");
                           }
                       }
                       System.out.println("qui");
                       if (carrello.Fcodice() == null/*cg.Fcodice().isEmpty()*/) {
                           fos = null;
                           colores=null;
                           System.out.println("8");
                       }else {
                           for (int i : carrello.Fcodice()) {
                               System.out.println("super");
                               fos.add(fornituraDAOs.doRetrieveByUtenteCode(i));
                               System.out.println("fatto");
                           }
                           for (int x = 0; x < fos.size(); x++) {
                               System.out.println(fos.get(x).getCodColore());
                               colores.add(coloreDAOs.doRetrieveByCode(fos.get(x).getCodColore()));
                               System.out.println("risolto");
                           }
                       }
                   } else {
                       System.out.println("4");
                       List<String> str = new ArrayList<>();
                       System.out.println("qui");
                       if (carrello.mCodice() == null/*cg.mCodice().isEmpty()*/) {
                           System.out.println("5");
                           mes = null;
                       } else {
                           System.out.println("forse");
                           str = carrello.mCodice();
                           for (String s : str) {
                               mes.add(mDAOs.doRetrieveByCode(s));
                               System.out.println("6");
                           }
                       }
                       System.out.println("7");
                       if (carrello.Fcodice() == null/*cg.Fcodice().isEmpty()*/) {
                           fos= null;
                           colores=null;
                           System.out.println("8");
                       } else {
                           System.out.println("9");
                           int xt =0;
                           for (int i : carrello.Fcodice()) {
                               System.out.println(i);
                               System.out.println("robertoooo");
                               fos.add(fornituraDAOs.doRetrieveByUtenteCode(i));
                               System.out.println(fos.get(xt).getCodColore());
                               xt++;
                           }
                           for (int x = 0; x < fos.size(); x++) {
                               System.out.println(fos.get(x).getCodColore());
                               colores.add(coloreDAOs.doRetrieveByCode(fos.get(x).getCodColore()));
                               System.out.println("risolto");
                           }
                       }
                   }
                   System.out.println(mes);
                   System.out.println(fos);
                   System.out.println(colores);
                   System.out.println(carrello);
                   request.setAttribute("carrelloMerce",mes);
                   request.setAttribute("carrelloFornitura",fos);
                   request.setAttribute("carrelloColori",colores);
                   System.out.println("rssaxdwqcbh");
                   request.getRequestDispatcher("/WEB-INF/views/customer/shoppingcart.jsp").forward(request,response);
                   break;
               default:
                   UserError();
                   break;
           }} catch (SQLException e) {
           System.out.println("errore");
           e.printStackTrace();
       } catch (InvalidRequestException invalidRequestException) {
           System.out.println("siumproblem");
           invalidRequestException.printStackTrace();
       }
       }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String path = getPath(request);                      //(request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path){
            case "/signinAdmin": //login admin(ricerca nel db)
                UtenteDAO ud = new UtenteDAO();
                //request.setAttribute("back", view("crm/secret"));
                validate(UtenteValidator.validateSignIn(request));
                Utente tmpUtente = new Utente();
                tmpUtente.setEmail(request.getParameter("email"));
                tmpUtente.setPassword(request.getParameter("password"));
                Utente optUtente = ud.loginUtente(tmpUtente.getEmail(), /*tmpUtente.getPassword()*/ request.getParameter("password"),true );
                MerceDAO merceDAO = new MerceDAO();
                OrdineDAO ordineDAO = new OrdineDAO();
                int merce = merceDAO.countAll();
                int ordini = ordineDAO.countAll();
                int utenti = ud.countAll();
                if(optUtente.getEmail().equals(tmpUtente.getEmail())){
                    UtenteSession accountSession = new UtenteSession(optUtente);
                    accountSession.setEmail(optUtente.getEmail());
                    request.getSession(true).setAttribute("accountSession", accountSession);
                    //response.sendRedirect("/crm/dashboard");
                    request.setAttribute("merce",merce);
                    request.setAttribute("ordini",ordini);
                    request.setAttribute("utenti",utenti);
                    request.getRequestDispatcher(view("crm/home")).forward(request, response);
                }else{
                   throw new InvalidRequestException("Credenziali non valide",List.of("Credenziali non valide"),HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
            case "/create": //create new customer
                //authorize(request.getSession(false));
                UtenteDAO udao = new UtenteDAO();
                //request.setAttribute("back", view("crm/account"));
                validate(UtenteValidator.validateForm(request, false));
                //Utente utente = new UtenteFormMapper().map(request , false);
                Utente utente = new Utente();
                utente.setNome(request.getParameter("name"));
                utente.setCognome(request.getParameter("cognome"));
                utente.setEmail(request.getParameter("email"));
                utente.setPassword(request.getParameter("password"));
                if (udao.createUtente(utente)){
                    request.setAttribute("alert", new Alert(List.of("Account creato!"),"success"));
                    request.getRequestDispatcher("/WEB-INF/views/customer/user.jsp").forward(request,response);
                } else{
                    internalError();
                }
                break;
            case "/update": //update customer info(admin)
                authorize(request.getSession(false));
               // request.setAttribute("back",view("crm/account"));
              //  validate(UtenteValidator.validateForm(request, true));
                UtenteSession us = (UtenteSession) request.getSession().getAttribute("accountSession");
                Utente updateUtente = new UtenteFormMapper().map(request, true, us.getId());
                UtenteDAO utenteDAO = new UtenteDAO();
                if(utenteDAO.updateUtente(updateUtente)){
                    request.setAttribute("account", updateUtente);
                    request.setAttribute("alert", new Alert (List.of("Account aggiornato"),"success"));
                    request.getRequestDispatcher(view("crm/secret")).forward(request,response);
                } else{
                    internalError();
                }
                break;
            case "/updateUtente": //update customer
                //authorize(request.getSession(false));
                // request.setAttribute("back",view("crm/account"));
                //validate(UtenteValidator.validateForm(request, true));
                UtenteSession uts = (UtenteSession) request.getSession().getAttribute("accountSession");
                Utente updateUt = new UtenteFormMapper().map(request, false, uts.getId());
                UtenteDAO utDAO = new UtenteDAO();
                if(utDAO.updateUtente(updateUt)){
                    request.setAttribute("account", updateUt);
                    request.setAttribute("alert", new Alert (List.of("Account aggiornato"),"success"));
                    request.getRequestDispatcher("/WEB-INF/views/customer/user.jsp").forward(request,response);
                } else{
                    internalError();
                }
                break;
            /*case "/signupCliente": //registrazione cliente
                validate(UtenteValidator.validateForm(request, false));
                Utente customer = new UtenteFormMapper().map(request , false);
                UtenteDAO utdao = new UtenteDAO();
                if(utdao.createUtente(customer)){
                    response.sendRedirect("./accounts/signin");
                } else{
                    internalError();
                }
                break;*/
            case "/signinCliente":
                //request.setAttribute("back",view("site/signin"));
                validate(UtenteValidator.validateSignIn(request));
                Utente tmpCustomer = new Utente();
                tmpCustomer.setEmail(request.getParameter("email"));
                tmpCustomer.setPassword(request.getParameter("password"));
                UtenteDAO utentedao = new UtenteDAO();
                Utente optCustomer = utentedao.loginUtente(tmpCustomer.getEmail(), request.getParameter("password"),false );
                System.out.println("siamo spacciati");
                if(optCustomer.getEmail().equals(tmpCustomer.getEmail())){
                    System.out.println("werty");
                    UtenteSession customerSession = new UtenteSession(optCustomer);
                    System.out.println("werty");
                    customerSession.setEmail(optCustomer.getEmail());
                    request.getSession(true).setAttribute("accountSession", customerSession);
                    List<Ordine> ordine = new ArrayList<>();
                    OrdineDAO oDAO = new OrdineDAO();
                    PreferitiDAO pD = new PreferitiDAO();
                    ordine = oDAO.DoRetriveByLast(customerSession.getId());
                    PreferitiSession preferitiSession = (PreferitiSession)request.getSession(false).getAttribute("preferiti");
                    System.out.println("werty");
                    if(preferitiSession == null || preferitiSession.mCodice().isEmpty()) {
                        System.out.println("vabene");
                        PreferitiSession ps = new PreferitiSession(customerSession.getId(), 1);
                        List<Preferiti> preferitiList = new ArrayList<>();
                        System.out.println("vabffene");
                        preferitiList=pD.DoRetrieveByUtente(customerSession.getId());
                        System.out.println("rr");
                        if(preferitiList == null || preferitiList.isEmpty()) {
                            pD.insertElemento(0,customerSession.getId(),"niente",0);
                            System.out.println("okbro");
                        }else{
                            System.out.println("ss");
                            for (Preferiti p : preferitiList) {
                                ps.setmCodice(p.getmCodice());
                                ps.setFcodice(p.getFcodice());
                                ps.setQuantita(p.getNumeroPreferiti());
                            /*ps.setCodici(pD.DoRetriveCodiciByUtente(customerSession.getId()));
                            ps.setListFcodice(pD.DoRetriveFCodiciByUtente(customerSession.getId()));
                            ps.setListQuantita(pD.D);*/
                                System.out.println("ok");
                            }
                        }
                        request.getSession().setAttribute("preferiti", ps);
                    }else{
                        System.out.println("siamo forti");
                        PreferitiSession ps = new PreferitiSession(customerSession.getId(), 1);
                        for(int i=0;i<preferitiSession.mCodice().size();i++){
                            ps.setmCodice(preferitiSession.mCodice().get(i));
                            ps.setFcodice(preferitiSession.Fcodice().get(i));
                            ps.setQuantita(preferitiSession.Quantita().get(i));
                        }
                        System.out.println("qui");
                        List<Preferiti> preferitiList = new ArrayList<>();
                        System.out.println("qui");
                        preferitiList=pD.DoRetrieveByUtente(customerSession.getId());
                        System.out.println("qui");
                        int count = 0;
                        if(preferitiList == null || preferitiList.isEmpty()) {
                            pD.insertElemento(0,customerSession.getId(),"niente",0);
                        }else {
                            for (Preferiti p : preferitiList) {
                                System.out.println("qui");
                                for (int i = 0; i < ps.mCodice().size(); i++) {
                                    System.out.println("qui");
                                    if (p.getmCodice().equals(ps.mCodice().get(i)) && p.getFcodice() == ps.Fcodice().get(i)) {
                                        int quantita = p.getNumeroPreferiti() + ps.Quantita().get(i);
                                        ps.Quantita().set(i, quantita);
                                        preferitiList.remove(count);
                                        break;
                                    }
                                }
                                count++;
                            }
                            System.out.println("qui");
                            for (Preferiti p : preferitiList) {
                                System.out.println("qui");
                                ps.setmCodice(p.getmCodice());
                                ps.setFcodice(p.getFcodice());
                                ps.setQuantita(p.getNumeroPreferiti());
                            }
                        }
                        System.out.println("qui");
                        request.getSession().setAttribute("preferiti", ps);
                        /*
                        ps.setCodici(pD.DoRetriveCodiciByUtente(customerSession.getId()));
                        ps.setListFcodice(pD.DoRetriveFCodiciByUtente(customerSession.getId()));
                        for (String c:preferitiSession.mCodice()) {
                            ps.setmCodice(c);
                        }
                        for (int fcodici:preferitiSession.Fcodice()) {
                            ps.setFcodice(fcodici);
                        }
                        request.getSession(false).removeAttribute("preferiti");*/
                    }
                    CarrelloDAO carrelloDAO = new CarrelloDAO();
                    System.out.println("davide");
                    CarrelloSession carrelloSessione = (CarrelloSession) request.getSession().getAttribute("carrello");
                    if(carrelloSessione == null || carrelloSessione.mCodice().isEmpty()) {
                        CarrelloSession carrelloSession = new CarrelloSession(customerSession.getId(), 1);
                        List<Carrello> carrelloList = new ArrayList<>();
                        carrelloList=carrelloDAO.DoRetrieveByUtente(customerSession.getId());
                        if(carrelloList == null || carrelloList.isEmpty()) {
                            carrelloDAO.insertElemento(customerSession.getId(),"niente",0,0);
                        }else {
                            for (Carrello c : carrelloList) {
                                carrelloSession.setmCodice(c.getmCodice());
                                carrelloSession.setFcodice(c.getfCodice());
                                carrelloSession.setQuantita(c.getQuantita());
                            /*ps.setCodici(pD.DoRetriveCodiciByUtente(customerSession.getId()));
                            ps.setListFcodice(pD.DoRetriveFCodiciByUtente(customerSession.getId()));
                            ps.setListQuantita(pD.D);*/
                            }
                        }
                        request.getSession().setAttribute("carrello", carrelloSession);
                    }else{
                        System.out.println("siamo forti");
                        CarrelloSession carrelloSession = new CarrelloSession(customerSession.getId(), 1);
                        for(int i=0;i<carrelloSessione.mCodice().size();i++){
                            carrelloSession.setmCodice(carrelloSessione.mCodice().get(i));
                            carrelloSession.setFcodice(carrelloSessione.Fcodice().get(i));
                            carrelloSession.setQuantita(carrelloSessione.Quantita().get(i));
                        }
                        List<Carrello> carrelloList = new ArrayList<>();
                        carrelloList=carrelloDAO.DoRetrieveByUtente(customerSession.getId());
                        int count=0;
                        if(carrelloList == null || carrelloList.isEmpty()) {
                            carrelloDAO.insertElemento(customerSession.getId(),"niente",0,0);
                        }else {
                            for (Carrello c : carrelloList) {
                                for (int i = 0; i < carrelloSession.mCodice().size(); i++) {
                                    if (c.getmCodice().equals(carrelloSession.mCodice().get(i)) && c.getfCodice() == carrelloSession.Fcodice().get(i)) {
                                        int quantita = c.getQuantita() + carrelloSession.Quantita().get(i);
                                        carrelloSession.Quantita().set(i, quantita);
                                        carrelloList.remove(count);
                                        break;
                                    }
                                }
                                count++;
                            }
                            for (Carrello c : carrelloList) {
                                carrelloSession.setmCodice(c.getmCodice());
                                carrelloSession.setFcodice(c.getfCodice());
                                carrelloSession.setQuantita(c.getQuantita());
                            }
                        }
                        request.getSession().setAttribute("carrello", carrelloSession);
                        /*System.out.println("ererrrr");
                        carrelloSession.setListFcodice(Fcodici);
                        System.out.println("magasium");
                        carrelloSession.setListQuantita(carrelloDAO.DoRetrieveQuantita(customerSession.getId()));
                        carrelloSession.setmCodice(carrelloDAO.DoRetrieveCodici(customerSession.getId()));*/
                    /*List<Carrello> carrello = new ArrayList<>();
                    CarrelloDAO carrelloDAO = new CarrelloDAO();
                    carrello = carrelloDAO.DoRetrieveByUtente(customerSession.getId());
                        request.getSession(false).removeAttribute("carrello");
                    }else{
                        List<Integer> Fcodici = carrelloDAO.DoRetrieveFcodice(customerSession.getId());
                        CarrelloSession carrelloSession = new CarrelloSession(customerSession.getId(), 1);
                        System.out.println("ererrrr");
                        carrelloSession.setListFcodice(Fcodici);
                        System.out.println("magasium");
                        carrelloSession.setListQuantita(carrelloDAO.DoRetrieveQuantita(customerSession.getId()));
                        carrelloSession.setmCodice(carrelloDAO.DoRetrieveCodici(customerSession.getId()));
                        for (String c:carrelloSessione.mCodice()) {
                            carrelloSession.setmCodice(c);
                        }
                        for (int fcodici:carrelloSessione.Fcodice()) {
                            carrelloSession.setFcodice(fcodici);
                        }
                        for (int quantita:carrelloSessione.Quantita()) {
                            carrelloSession.setQuantita(quantita);
                        }*/
                    /*List<Carrello> carrello = new ArrayList<>();
                    CarrelloDAO carrelloDAO = new CarrelloDAO();
                    carrello = carrelloDAO.DoRetrieveByUtente(customerSession.getId());
                        request.getSession(false).removeAttribute("carrello");*/
                    }
                    request.setAttribute("ordine",ordine);
                    request.getRequestDispatcher("/WEB-INF/views/customer/profilo.jsp").forward(request, response);
                } else{
                    throw new InvalidRequestException("Credenziali non valide",
                            List.of("Credenziali non valide"), HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
            case "/elimina":
                UtenteSession ut= (UtenteSession) request.getSession().getAttribute("accountSession");
                UtenteDAO utdao= new UtenteDAO();
                utdao.deleteUtente(ut.getId());
                PreferitiDAO preferitiDAO = new PreferitiDAO();
                preferitiDAO.deletePreferiti(ut.getId());
                OrdineDAO od = new OrdineDAO();
                od.deleteOrdini(ut.getId());
                CarrelloDAO carrelloDAO = new CarrelloDAO();
                carrelloDAO.deleteCarrello(ut.getId());
                HttpSession session = request.getSession(false);
                authenticate(session);
                session.removeAttribute("accountSession");
                session.removeAttribute("preferiti");
                session.removeAttribute("carrello");
                //session.invalidate();
                PreferitiSession preferitiGuest = new PreferitiSession(0,0);
                request.getSession(true).setAttribute("preferiti", preferitiGuest);
                CarrelloSession carrelloGuest = new CarrelloSession(0,0);
                request.getSession(true).setAttribute("carrello", carrelloGuest);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/user.jsp");
                dispatcher.forward(request, response);
                break;
            default:
                UserError();
        }
    } catch (SQLException | NoSuchAlgorithmException ex){
            log(ex.getMessage());
        }catch (InvalidRequestException e) {
            log(e.getMessage());
            e.handle(request,response);
        }
    }
}