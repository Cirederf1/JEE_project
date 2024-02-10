package ensg.tsi.j2e.colloques.metier;

import jakarta.persistence.*;

@Entity
public class Administrateur {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String motDePasse;

    // Constructeur avec paramètres pour initialiser un Administrateur avec un nom
    // d'utilisateur et un mot de passe
    public Administrateur(String username, String motDePasse) {
        this.username = username;
        this.motDePasse = motDePasse;
    }

    // Constructeur par défaut nécessaire pour JPA
    public Administrateur() {

    }

    // Méthode getter pour récupérer l'ID de l'administrateur
    public long getId() {
        return id;
    }

    // Méthode getter pour récupérer le nom d'utilisateur de l'administrateur
    public String getUsername() {
        return username;
    }

    // Méthode setter pour définir le nom d'utilisateur de l'administrateur
    public void setUsername(String username) {
        this.username = username;
    }

    // Méthode getter pour récupérer le mot de passe de l'administrateur
    public String getMotDePasse() {
        return motDePasse;
    }

    // Méthode setter pour définir le mot de passe de l'administrateur
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
