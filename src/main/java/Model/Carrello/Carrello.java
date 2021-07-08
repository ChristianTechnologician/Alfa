package Model.Carrello;

import Model.Merce.Merce;
import Model.Ordine.Ordine;
import Model.Utente.Utente;

public class Carrello {
    private int idCarrello,idUtente,quantita;
    private String mCodice;
    private Utente utente;
    private Ordine ordine;
    private Merce merce;

    public Carrello(){

    }

    public int  getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(int idCarrello) {
        this.idCarrello = idCarrello;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getmCodice() {
        return mCodice;
    }

    public void setmCodice(String mCodice) {
        this.mCodice = mCodice;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public Merce getMerce() {
        return merce;
    }

    public void setMerce(Merce merce) {
        this.merce = merce;
    }
}
