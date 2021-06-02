package Model.Preferiti;

import Model.Merce.Merce;
import Model.Utente.Utente;

public class Preferiti {
    private int numeroPreferiti,idUtente;
    private String mCodice;
    private Utente utente;
    private Merce merce;

    public Preferiti(){

    }

    public int getNumeroPreferiti() {
        return numeroPreferiti;
    }

    public void setNumeroPreferiti(int numeroPreferiti) {
        this.numeroPreferiti = numeroPreferiti;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
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

    public Merce getMerce() {
        return merce;
    }

    public void setMerce(Merce merce) {
        this.merce = merce;
    }
}
