package Model.Utente;

import Model.Carrello.Carrello;
import Model.Ordine.Ordine;
import Model.Preferiti.Preferiti;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

public class Utente{

    private String nome,cognome,email,password;
    private int id;
    private boolean isAdministration;
    private Carrello carrello;
    private Preferiti preferiti;
    private List<Ordine> ordineList;

    public Utente(){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        SecureRandom ss = new SecureRandom();
        byte [] salt = new byte[16];
        ss.nextBytes(salt);
        digest.update(salt);
        byte [] hashedPad = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();
        for(byte bit : hashedPad){
            builder.append(String.format("%02x",bit));
        }
        this.password = builder.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsAdministration() {
        return isAdministration;
    }

    public void setIsAdministration(boolean isAdministration) {
        this.isAdministration = isAdministration;
    }

   public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public Preferiti getPreferiti() {
        return preferiti;
    }

    public void setPreferiti(Preferiti preferiti) {
        this.preferiti = preferiti;
    }

    public List<Ordine> getOrdineList() {
        return ordineList;
    }

    public void setOrdineList(List<Ordine> ordineList) {
        this.ordineList = ordineList;
    }

}
