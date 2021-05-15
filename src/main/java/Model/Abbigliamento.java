package Model;


public class Abbigliamento {
  private String codice;
  private String taglia;
  private String genere;
  private double prezzo;
  private String categoria;
  
  public String getCodice() {
    return codice;
  }

  public String getTaglia() {
    return taglia;
  }

  public String getGenere() {
    return genere;
  }

  public double getPrezzo() {
    return prezzo;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCodice(String codice) {
    this.codice = codice;
  }

  public void setTaglia(String taglia) {
    this.taglia = taglia;
  }

  public void setGenere(String genere) {
    this.genere = genere;
  }

  public void setPrezzo(double prezzo) {
    this.prezzo = prezzo;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }
}
  
