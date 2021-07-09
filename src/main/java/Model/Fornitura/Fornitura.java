package Model.Fornitura;

public class Fornitura {
    private String codMerce;
    private String lTaglia;
    private int codColore;
    private int quantita;
    private int identificatore;

public Fornitura(){
}
    public String getCodMerce() {
        return codMerce;
    }

    public void setCodMerce(String codMerce) { this.codMerce = codMerce;}

    public String getlTaglia() {
        return lTaglia;
    }

    public void setlTaglia(String lTaglia) {
        this.lTaglia = lTaglia;
    }

    public int getCodColore() {
        return codColore;
    }

    public void setCodColore(int codColore) {
        this.codColore = codColore;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getIdentificatore() {
        return identificatore;
    }

    public void setIdentificatore(int identificatore) {
        this.identificatore = identificatore;
    }
}
