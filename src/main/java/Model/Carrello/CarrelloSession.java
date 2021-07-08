package Model.Carrello;

public class CarrelloSession {
    private final String Mcodice;
    private final int IDCarrello,IDutente,Quantita;
    private int NumeroFattura;

    public CarrelloSession(Carrello carrello){
        this.IDCarrello = carrello.getIdCarrello();
        this.IDutente = carrello.getIdUtente();
        this.Quantita = carrello.getQuantita();
        this.Mcodice = carrello.getmCodice();
    }

    public String getMcodice() {
        return Mcodice;
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

    public int getNumeroFattura() {
        return NumeroFattura;
    }

    public void setNumeroFattura(int numeroFattura) {
        NumeroFattura = numeroFattura;
    }
}
