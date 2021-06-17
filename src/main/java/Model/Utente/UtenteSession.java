package Model.Utente;

public class UtenteSession {
    private final String firstName, lastName;
    private final int id;
    private final boolean isAdmin;

    public UtenteSession(Utente utente){
        this.firstName = utente.getNome();
        this.lastName = utente.getCognome();
        this.id = utente.getId();
        this.isAdmin = utente.getIsAdministration();

    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public int getId() { return id; }

    public boolean isAdmin() { return isAdmin; }
}
