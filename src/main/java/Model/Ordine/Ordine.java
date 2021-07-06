package Model.Ordine;

import Model.Carrello.Carrello;
import Model.Utente.Utente;
import java.time.LocalDate;

public class Ordine {
    private int numeroFattura,civico,stato,idUtente;
    private String via,citta,provincia,CodiceMerceAcquistata;
    private double prezzoTotale;
    private LocalDate date;
    private Carrello carrello;
    private Utente utente;

    public Ordine(){

    }

    public int getNumeroFattura() {
        return numeroFattura;
    }

    public void setNumeroFattura(int numeroFattura) {
        this.numeroFattura = numeroFattura;
    }

    public int getCivico() {
        return civico;
    }

    public void setCivico(int civico) {
        this.civico = civico;
    }

    public int getStato() {
        return stato;
    }

    public void setStato(int stato) {
        this.stato = stato;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getCodiceMerceAcquistata(){return this.CodiceMerceAcquistata;}

    public void setCodiceMerceAcquistata(String CodiceMerceAcquistata){this.CodiceMerceAcquistata = CodiceMerceAcquistata;}

}
