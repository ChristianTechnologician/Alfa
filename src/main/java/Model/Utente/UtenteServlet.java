package Model.Utente;

import Model.Gestione.*;
import com.sun.jdi.request.InvalidRequestStateException;
import com.sun.tools.javac.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "UtenteServlet", value = "/utente/*")
public class UtenteServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
           String path = getPath(request);//(request.getPathInfo() != null) ? request.getPathInfo() : "/";
           switch (path) {
               case "/":
                   authorize(request.getSession());
                   request.setAttribute("back",view("crm/accounts"));
                   validate(CommonValidator.validatePage(request));
                   int page = parsePage(request);
                   Paginatore paginator = new Paginatore(page,50);
                   int size = UtenteDAO.countAll();
                   request.setAttribute("pages",paginator.getPages(size));
                   List<Utente> utenti = UtenteDAO.fetchUtenti(paginator);
                   request.setAttribute("utenti",utenti);
                   request.getRequestDispatcher(view("crm/accounts")).forward(request, response);
                   break;
               case "/signinCliente":
                   request.getRequestDispatcher(view("account/loginform")).forward(request, response);//"site/signin"
                   break;
               case "/create":
                   authorize(request.getSession(false));
                   request.getRequestDispatcher(view("crm/account")).forward(request, response);
                   break;
               case "/show":
                   authorize(request.getSession(false));
                   validate(CommonValidator.validateId(request));
                   int id = Integer.parseInt(request.getParameter("id"));
                   Optional<Utente> optUtente = UtenteDAO.fetchUtente(id);
                   if(optUtente.isPresent()){
                       request.setAttribute("utente",optUtente.get());
                       request.getRequestDispatcher(view("crm/account")).forward(request, response);
                   }else{
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
                   int profileId = getUtenteSession(request.getSession(false)).getId();
                   Optional<Utente> profileUtente = UtenteDAO.fetchUtente(profileId);
                   if(profileUtente.isPresent()){
                       request.setAttribute("profile", profileUtente.get());
                       request.getRequestDispatcher(view("site/profile")).forward(request, response);
                   } else{
                       notFound();
                   }
                   break;
               case "/logout":
                   HttpSession session = request.getSession(false);
                   authenticate(session);
                   UtenteSession utenteSession = (UtenteSession) session.getAttribute("utenteSession");
                   String redirect = utenteSession.isAdmin() ? "/Alfa/accounts/signinAdmin" : "/Alfa/accouts/signinCliente";
                   session.removeAttribute("utenteSession");
                   session.invalidate();
                   response.sendRedirect(redirect);
                   break;
               default:
                   notFound();                      //response.sendError(HttpServletResponse.SC_NOT_FOUND,"Risorsa non trovata");
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
            String path =   getPath(request);                      //(request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path){
            case "/signinAdmin": //login admin(ricerca nel db)
                request.setAttribute("back", view("crm/secret"));
                validate(UtenteValidator.validateSignin(request));
                Utente tmpUtente = new Utente();
                tmpUtente.setEmail(request.getParameter("email"));
                tmpUtente.setPassword(request.getParameter("password"));
                Optional<Utente> optUtente = UtenteDAO.findUtente(tmpUtente.getEmail(), tmpUtente.getPassword(),true );
                if(optUtente.isPresent()){
                    UtenteSession accountSession = new UtenteSession(optUtente.get());
                    request.getSession(true).setAttribute("accountSession", accountSession);
                    response.sendRedirect("/Grocer/pages/dashboard");
                }else{
                    throw new InvalidRequestStateException("Credenziali non valide",
                            List.of(("Credenziali non valide"), HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
            case "/create": //create new customer
                authorize(request.getSession(false));
                request.setAttribute("back", view("crm/account"));
                validate(UtenteValidator.validateForm(request, false);
                Utente utente = new UtenteFormMapper().map(request, false);
                utente.setPassword(request.getParameter("password"));
                if (UtenteDAO.createUtente(utente)){
                    request.setAttribute("alert", new Alert(List.of("Account creato!"),"success"));
                    request.getRequestDispatcher(view("crm/account")).forward(request,response);
                } else{
                    internalError();
                }
                break;
            case "/update": //update customer info(admin)
                authorize(request.getSession(false));
                request.setAttribute("back",view("crm/account"));
                validate(UtenteValidator.validateForm(request, true));
                Utente updateUtente = new UtenteFormMapper().map(request, true);
                if(UtenteDAO.updateUtente(updatedUtente)){
                    request.setAttribute("account", updatedUtente);
                    request.setAttribute("alert", new Alert (List.of("Account aggiornato"),"success"));
                    request.getRequestDispatcher(view("crm/account")).forward(request,response);
                } else{
                    internalError();
                }
                break;
            case "/signupCliente": //registrazione cliente
                validate(UtenteValidator.validateForm(request, false));
                Utente customer = new UtenteFormMapper().map(request, false);
                if(UtenteDAO.createUtente(customer)){
                    response.sendRedirect("./accounts/signin");
                } else{
                    internalError();
                }
                break;
            case "signinCliente": // ci mancano le prime righe di codice
                request.setAttribute("back",view("site/signin"));
                validate(UtenteValidator.validateSignIn(request));
                Utente tmpCustomer = new Utente();
                tmpCustomer.setEmail(request.getParameter("email"));
                tmpCustomer.setPassword(request.getParameter("password"));
                Optional <Utente> optCustomer = UtenteDAO.findUtente(tmpCustomer.getEmail(), tmpCustomer.getPassword(),false );
                if(optCustomer.isPresent()){
                    UtenteSession customerSession = new UtenteSession(optCustomer.get());
                            request.getSession(true).setAttribute("accountSession", customerSession);
                            response.sendRedirect("/site/home");
                } else{
                    throw new InvalidRequestException("Credenziali non valide",
                            List.of("Credenziali non valide"), HttpServletResponse.SC_BAD_REQUEST);
                }
                break;
            default:
             notAllowed();                       // response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"Operazione non consentita");
        }
    } catch (SQLException | NoSuchAlgorithmException ex){
            log(ex.getMessage());
        }catch (InvalidRequestException e) {
            log(e.getMessage());
            e.handle(request,response);
        }
    }
}