package Model.Merce;

import Model.Colore.Colore;
import Model.Gestione.Controller;
import Model.Taglia.Taglia;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(name = "MerceServlet", value = "/merce/*")
public class MerceServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String path = getPath(request);
        switch (path){
            case "/insertMerce":
                request.getRequestDispatcher(view("crm/insertMerce")).forward(request,response);
                break;
            case "/updateMerce":
                request.getRequestDispatcher(view("crm/updateMerce")).forward(request,response);
                break;
            case "/deleteMerce":
                request.getRequestDispatcher(view("crm/deleteMerce")).forward(request,response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND,"Risorsa non trovata");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try{
            String path=getPath(request);
            switch (path)
            {
                case "/create":
                    MerceDAO merceDao=new MerceDAO();
                    Merce merce=new Merce();
                    merce.setCodice(request.getParameter("Codice"));
                    merce.setNome(request.getParameter("Nome"));
                    merce.setDescrizione(request.getParameter("Descrizione"));
                    merce.setGenere(request.getParameter("Genere"));
                    merce.setPrezzo(Double.parseDouble(request.getParameter("Prezzo")));
                    merce.setTipocategoria(request.getParameter("TipoCategoria"));
                    merce.setSconto(Double.parseDouble(request.getParameter("Sconto")));
                    Taglia taglia=new Taglia();
                    taglia.setlTaglia(request.getParameter("LTaglia"));
                    Colore colore=new Colore();
                    colore.setCod(Integer.parseInt(request.getParameter("Cod")));
                    int quantita=Integer.parseInt(request.getParameter("Quantita"));
                    Part filePart=request.getPart("UpImg");
                    String fileName= Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    merce.setCover(fileName);
            }
        }
    }
}
