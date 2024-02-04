package ensg.tsi.j2e.colloques.metier;

import jakarta.persistence.*;

@Entity
public class Administrateur {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String motDePasse;

    public Administrateur(String username, String motDePasse) {
        this.username = username;
        this.motDePasse = motDePasse;
    }

    public Administrateur(){

    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


}
