package Model.Preferiti;

import java.util.List;

public class PreferitiSession {
    private List<String> mCodice;
    private List<Integer> Quantita,Fcodice;
    private final int idUtente,registrato;

    public PreferitiSession(int id,int reg){
        this.idUtente = id;
        this.registrato = reg;
    }

    public int getRegistrato() {
        return registrato;
    }

    public void setmCodice(String mCodice) {
        this.mCodice.add(mCodice);
    }

    public void setCodici(List<String> mcodice){
        this.mCodice=mcodice;
    }

    public List<String> mCodice() {
        return mCodice;
    }

    public int getIdUtente() {
        return idUtente;
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
}
