package Model.Carrello;

import java.util.List;

public class CarrelloSession {
    private List<String> mCodice;
    private List<Integer> Quantita,Fcodice;
    private final int IDCarrello,IDutente,registrato;

    public CarrelloSession(int id,int reg){
        this.IDCarrello = id;
        this.IDutente = id;
        this.registrato = reg;
    }

    public int getRegistrato() {
        return registrato;
    }

    public void setQuantita(int quantita){this.Quantita.add(quantita);}

    public void setListQuantita(List<Integer> quantita){
        this.Quantita=quantita;
    }

    public void setFcodice(int Fcodice){this.Fcodice.add(Fcodice);}

    public void setListFcodice(List<Integer> Fcodice){
        this.Fcodice=Fcodice;
    }

    public List<Integer> Fcodice() {
        return Fcodice;
    }

    public List<Integer> Quantita() {
        return Quantita;
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

    public void setmCodice(String mCodice) {
        this.mCodice.add(mCodice);
    }

    public void setmCodice(List<String> codici) {
        this.mCodice=codici;
    }


}
