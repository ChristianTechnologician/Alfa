package Model.Taglia;

import Model.Colore.Colore;
import Model.Merce.Merce;

import java.util.List;

public class Taglia {
    private String lTaglia;
    private List<Colore> coloreList;
    private List<Merce> MerceList;

    public Taglia(){

    }

    public String getlTaglia() {
        return lTaglia;
    }

    public void setlTaglia(String lTaglia) {
        this.lTaglia = lTaglia;
    }

    public List<Colore> getColoreList() {
        return coloreList;
    }

    public void setColoreList(List<Colore> coloreList) {
        this.coloreList = coloreList;
    }

    public List<Merce> getMerceList() {
        return MerceList;
    }

    public void setMerceList(List<Merce> merceList) {
        MerceList = merceList;
    }
}
