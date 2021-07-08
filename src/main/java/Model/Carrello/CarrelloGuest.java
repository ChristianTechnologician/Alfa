package Model.Carrello;

import Model.Fornitura.Fornitura;

import java.util.List;

public class CarrelloGuest {
    private List<String> codiceMerce;
    private List<Fornitura> fornituraList;

    public CarrelloGuest(){
    }

    public void setCodice(String codice){
        this.codiceMerce.add(codice);
    }

    public void setFornitura(Fornitura fornitura){
        this.fornituraList.add(fornitura);
    }

    public List<String> getCodiceMerce() {
        return codiceMerce;
    }

    public void setCodiceMerce(List<String> codiceMerce) {
        this.codiceMerce = codiceMerce;
    }

    public List<Fornitura> getFornituraList() {
        return fornituraList;
    }

    public void setFornituraList(List<Fornitura> fornituraList) {
        this.fornituraList = fornituraList;
    }
}
