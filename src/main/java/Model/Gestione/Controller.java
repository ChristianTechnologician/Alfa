package Model.Gestione;

import Model.Utente.UtenteSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.File;

public abstract class Controller extends HttpServlet implements ErrorHandler {
    /*Resource(name = "jdbc/Alfa")
    protected static DataSource source;*/

    protected String getPath(HttpServletRequest request){
        return request.getPathInfo() != null ? request.getPathInfo() : "/";
    }

    protected  String view(String viewPath){
        String basePath = getServletContext().getInitParameter("basePath");
        String engine = getServletContext().getInitParameter("engine");
        return  basePath + viewPath + engine;
    }

    protected String back(HttpServletRequest request){
        return request.getServletPath() + request.getPathInfo();
    }

    protected void validate(RequestValidator validator) throws InvalidRequestException{
        if(validator.hasErrors()){
            throw  new InvalidRequestException("Validation Error",validator.getErrors(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected String getUploadPath(){
        /*return System.getenv("CATALINA_HOME") + File.separator + "webapps" +
                File.separator + "Alfa" + File.separator + "uploads" + File.separator;*/
        return  System.getenv("CATALINA_HOME")+File.separator + "uploads" + File.separator;
    }

    protected int parsePage(HttpServletRequest request){
        return  Integer.parseInt(request.getParameter("page"));
    }

    protected UtenteSession getAccountSession(HttpSession session){
        return (UtenteSession) session.getAttribute("accountSession");
    }

   /* protected Cart getSessionCart(HttpSession session){
        return (Cart) session.getAttribute("accountCart");
    }*/


}
