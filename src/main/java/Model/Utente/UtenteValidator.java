package Model.Utente;

import Model.Gestione.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

final class UtenteValidator {
    static RequestValidator validateForm(HttpServletRequest request,boolean update) {
        RequestValidator validator = new RequestValidator(request);
        validator.assertMatch("label", Pattern.compile("^\\w{4,20}$"),"il nome utente deve avere lunghezza compresa tra i quattro e i 20 caratteri");
        if(update){
            validator.assertInt("id","Id deve essere un numero intero");
        }
        return validator;
    }

    public static RequestValidator validateSignIn(HttpServletRequest request) {
        RequestValidator validator = new RequestValidator(request);
        validator.assertEmail("label","Inserire l'email corrispondente al tuo account");
        return validator;
    }
}
