package ensg.tsi.j2e.colloques.metier;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Evenement {
    @Id
    @GeneratedValue
    private long num_even;
    private String intitule;
    private String theme;
    private String date_debut;
    private int duree;
    private int nb_part_max;
    private String description;
    private String organisateur;
    private String type_even;

    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL)
    private List<Participant> participants = new ArrayList<>();

    public Evenement() {
    }

    // Constructeur avec paramètres
    public Evenement(String intitule, String theme, String date_debut, int duree, int nb_part_max,
                     String description, String organisateur, String type_even) {
        this.intitule = intitule;
        this.theme = theme;
        this.date_debut = date_debut;
        this.duree = duree;
        this.nb_part_max = nb_part_max;
        this.description = description;
        this.organisateur = organisateur;
        this.type_even = type_even;
    }
    
    public long getNum_even() {
        return num_even;
    }

    public void setNum_even(int num_even) {
        this.num_even = num_even;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getNb_part_max() {
        return nb_part_max;
    }

    public void setNb_part_max(int nb_part_max) {
        this.nb_part_max = nb_part_max;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public String getType_even() {
        return type_even;
    }

    public void setType_even(String type_even) {
        this.type_even = type_even;
    }

    public void addParticipant(Participant participant) {
        if (participants == null) {
            participants = new ArrayList<>();
        }
        participants.add(participant);
        participant.setEvenement(this);
    }

    public void setEvenementDetails(Evenement evenement) {
        this.intitule = evenement.getIntitule();
        this.theme = evenement.getTheme();
        this.date_debut = evenement.getDate_debut();
        this.duree = evenement.getDuree();
        this.nb_part_max = evenement.getNb_part_max();
        this.description = evenement.getDescription();
        this.organisateur = evenement.getOrganisateur();
        this.type_even = evenement.getType_even();
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}

    
    


