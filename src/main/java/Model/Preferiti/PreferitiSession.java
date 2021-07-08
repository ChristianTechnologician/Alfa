package Model.Preferiti;

import java.util.List;

public class PreferitiSession {
    private List<String> mCodice;
    private final int idUtente;

    public PreferitiSession(int id){
        this.idUtente = id;

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
}
