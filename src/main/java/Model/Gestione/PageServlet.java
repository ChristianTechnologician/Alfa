package Model.Gestione;

import com.sun.jdi.request.InvalidRequestStateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PageServlet", value = "/pages/*")
    public class PageServlet extends Controller{
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try{
                String path = getPath(request);
                switch(path){
                    case "/dashboard":
                        authorize(request.getSession(false));
                        request.getRequestDispatcher(view("crm/home")).forward(request,response);
                        break;
                    case "/":
                        request.getRequestDispatcher(view("site/home")).forward(request,response);
                        break;
                    default:
                        notFound();
                }
            }catch (InvalidRequestException ex){
                log(ex.getMessage());
                ex.handle(request,response);

            }
        }
    }

