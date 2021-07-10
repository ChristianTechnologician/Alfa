package Model.Utente;

import Model.Gestione.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

final class UtenteValidator {
    static RequestValidator validateForm(HttpServletRequest request,boolean update) {
        RequestValidator validator = new RequestValidator(request);
        validator.assertMatch("name", Pattern.compile("^\\w{4,20}$"),"il nome utente deve iniziare con una lettera e  avere lunghezza compresa tra i 4 e i 20 caratteri");
        if(update){
            validator.assertInt("id","Id deve essere un numero intero");
        }
        return validator;
    }

    public static RequestValidator validateSignIn(HttpServletRequest request) {
        RequestValidator validator = new RequestValidator(request);
        validator.assertEmail("email","Inserire l'email corrispondente al tuo account");
        validator.assertPassword("password", "Inserire la password corrispondente al tuo account");
        System.out.println("email " + request.getParameter("email"));
        System.out.println("password " + request.getParameter("password"));
        return validator;
    }
}
