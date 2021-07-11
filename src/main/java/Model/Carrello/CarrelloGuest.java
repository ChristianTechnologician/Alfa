package Model.Carrello;

import Model.Fornitura.Fornitura;

import java.util.List;

public class CarrelloGuest {
    private List<String> codiceMerce;
    private List<Fornitura> codiciFornitura;

    public CarrelloGuest(){
    }

    public void setCodice(String codice){
        this.codiceMerce.add(codice);
    }

    public void setCodiciFornitura(Fornitura fornitura){
        this.codiciFornitura.add(fornitura);
    }

    public List<String> getCodiceMerce() {
        return codiceMerce;
    }

    public void setCodiceMerce(List<String> codiceMerce) {
        this.codiceMerce = codiceMerce;
    }

    public List<Fornitura> getCodiciFornituraList() {
        return codiciFornitura;
    }

    public void setcodiciFornituraList(List<Fornitura> fornituraList) {
        this.codiciFornitura = fornituraList;
    }
}
