package Model.Colore;

import Model.Merce.Merce;
import Model.Taglia.Taglia;

import java.util.List;

public class Colore {
    private int cod;
    private String tipoColore;
    private List<Taglia> tagliaList;
    private List<Merce> merceList;

    public Colore(){

    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getTipoColore() {
        return tipoColore;
    }

    public void setTipoColore(String tipoColore) {
        this.tipoColore = tipoColore;
    }

    public List<Taglia> getTagliaList() {
        return tagliaList;
    }

    public void setTagliaList(List<Taglia> tagliaList) {
        this.tagliaList = tagliaList;
    }

    public List<Merce> getMerceList() {
        return merceList;
    }

    public void setMerceList(List<Merce> merceList) {
        this.merceList = merceList;
    }
}
