package ensg.tsi.j2e.colloques.metier;

import jakarta.persistence.*;

@Entity
public class Participant {
    @Id
    @GeneratedValue
    private long num_pers; // Identifiant unique du participant

    // Informations sur le participant
    private String nom; // Nom du participant
    private String prenom; // Prénom du participant
    private String email; // Adresse email du participant
    private String date_naiss; // Date de naissance du participant
    private String organisation; // Organisation du participant
    private String observations; // Observations sur le participant

    // Relation Many-to-One avec Evenement: Un participant appartient à un événement
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "num_even", referencedColumnName = "num_even")
    private Evenement evenement;

    public Participant() {
    }

    // Constructeur avec paramètres pour initialiser un participant
    public Participant(String nom, String prenom, String email, String date_naiss, String organisation,
            String observations) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.date_naiss = date_naiss;
        this.organisation = organisation;
        this.observations = observations;
    }

    // Méthode getter pour récupérer l'identifiant du participant
    public long getNum_pers() {
        return num_pers;
    }

    // Méthode setter pour définir l'identifiant du participant
    public void setNum_pers(int num_pers) {
        this.num_pers = num_pers;
    }

    // Méthodes getter et setter pour les autres attributs du participant

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(String date_naiss) {
        this.date_naiss = date_naiss;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    // Méthode getter pour récupérer l'événement auquel le participant appartient
    public Evenement getEvenement() {
        return evenement;
    }

    // Méthode setter pour définir l'événement auquel le participant appartient
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    // Méthode pour mettre à jour les informations du participant avec celles d'un
    // autre participant
    public void updateParticipant(Participant p) {
        this.nom = p.getNom();
        this.prenom = p.getPrenom();
        this.email = p.getEmail();
        this.date_naiss = p.getDate_naiss();
        this.organisation = p.getOrganisation();
        this.observations = p.getObservations();
    }

}
