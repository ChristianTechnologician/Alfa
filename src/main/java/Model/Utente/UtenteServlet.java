package Model.Utente;

import Model.Gestione.*;
import Model.Merce.MerceDAO;
import Model.Ordine.Ordine;
import Model.Ordine.OrdineDAO;
import com.mysql.cj.Session;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "UtenteServlet", value = "/utente/*")
public class UtenteServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
           String path = getPath(request);//(request.getPathInfo() != null) ? request.getPathInfo() : "/";
           switch (path) {
               case "/prova":
                   request.getRequestDispatcher(view("crm/prova")).forward(request, response);
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
                   request.setAttribute("utente",utente);
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
                   session.invalidate();
                   request.getRequestDispatcher(redirect).forward(request, response);
                   break;
               case "/user":
                   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/user.jsp");
                   dispatcher.forward(request, response);
                   break;
               default:
                   UserError();
                   break;
           }
       }catch (SQLException ex){
               log(ex.getMessage());
           } catch (InvalidRequestException e){
               log(e.getMessage());
               e.handle(request,response);
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
                if(optCustomer.getEmail().equals(tmpCustomer.getEmail())){
                    UtenteSession customerSession = new UtenteSession(optCustomer);
                    customerSession.setEmail(optCustomer.getEmail());
                            request.getSession(true).setAttribute("accountSession", customerSession);
                            List<Ordine> ordine = new ArrayList<>();
                            OrdineDAO oDAO = new OrdineDAO();
                            ordine = oDAO.DoRetriveByLast(customerSession.getId());
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