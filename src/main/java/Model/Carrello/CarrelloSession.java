package Model.Carrello;

import java.util.List;

public class CarrelloSession {
    private List<String> mCodice;
    private final int IDCarrello,IDutente,Quantita;

    public CarrelloSession(Carrello carrello){
        this.IDCarrello = carrello.getIdCarrello();
        this.IDutente = carrello.getIdUtente();
        this.Quantita = carrello.getQuantita();
    }

    public List<String> mCodice() {
        return mCodice;
    }

    public int getIDCarrello() {
        return IDCarrello;
    }

    public int getIDutente() {
        return IDutente;
    }

    public int getQuantita() {
        return Quantita;
    }

    public void setmCodice(String mCodice) {
        this.mCodice.add(mCodice);
    }

}
