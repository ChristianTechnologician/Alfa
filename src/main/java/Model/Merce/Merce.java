package Model.Merce;

import Model.Carrello.Carrello;
import Model.Colore.Colore;
import Model.Preferiti.Preferiti;
import Model.Taglia.Taglia;

import java.util.List;

public class Merce {
  private String codice,nome,descrizione,genere,tipocategoria;
  private double prezzo,sconto;
  private List<Preferiti> preferitiList;
  private List<Colore> coloreList;
  private List<Taglia> tagliaList;
  private List<Carrello> carrelloList;

  public Merce(){

  }

  public String getCodice() {
    return codice;
  }

  public void setCodice(String codice) {
    this.codice = codice;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public String getGenere() {
    return genere;
  }

  public void setGenere(String genere) {
    this.genere = genere;
  }

  public double getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(double prezzo) {
    this.prezzo = prezzo;
  }

  public String getTipocategoria() {
    return tipocategoria;
  }

  public void setTipocategoria(String tipocategoria) {
    this.tipocategoria = tipocategoria;
  }

  public double getSconto() {
    return sconto;
  }

  public void setSconto(double sconto) {
    this.sconto = sconto;
  }

  public List<Preferiti> getPreferitiList() {
    return preferitiList;
  }

  public void setPreferitiList(List<Preferiti> preferitiList) {
    this.preferitiList = preferitiList;
  }

  public List<Colore> getColoreList() {
    return coloreList;
  }

  public void setColoreList(List<Colore> coloreList) {
    this.coloreList = coloreList;
  }

  public List<Taglia> getTagliaList() {
    return tagliaList;
  }

  public void setTagliaList(List<Taglia> tagliaList) {
    this.tagliaList = tagliaList;
  }

  public List<Carrello> getCarrelloList() {
    return carrelloList;
  }

  public void setCarrelloList(List<Carrello> carrelloList) {
    this.carrelloList = carrelloList;
  }
}
  
