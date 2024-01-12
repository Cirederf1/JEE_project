package ensg.tsi.j2e.colloques.metier;

import jakarta.persistence.*;

@Entity
public class Participant {
    @Id
    @GeneratedValue
    private long num_pers;
    private String nom;
    private String prenom;
    private String email;
    private String date_naiss;
    private String organisation;
    private String observations;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "num_even", referencedColumnName = "num_even")
    private Evenement evenement;

    public Participant() {
    }

    public Participant(String nom, String prenom, String email, String date_naiss, String organisation, String observations) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.date_naiss = date_naiss;
        this.organisation = organisation;
        this.observations = observations;
    }

    public long getNum_pers() {
        return num_pers;
    }

    public void setNum_pers(int num_pers) {
        this.num_pers = num_pers;
    }

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

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
}

