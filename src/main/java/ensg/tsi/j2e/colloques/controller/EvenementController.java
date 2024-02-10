package ensg.tsi.j2e.colloques.controller;

import ensg.tsi.j2e.colloques.metier.Evenement;
import ensg.tsi.j2e.colloques.services.EvenementService;
import ensg.tsi.j2e.colloques.repositories.AdministrateurRepo;
import ensg.tsi.j2e.colloques.repositories.EvenementRepo;
import ensg.tsi.j2e.colloques.services.EvenementService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class EvenementController {
    private final EvenementService evenementService;

    // Constructeur prenant le service EvenementService
    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    // Affiche le formulaire pour ajouter un événement
    @PostMapping("/goToAddEvent")
    public String addEvent() {
        return "createEvent";
    }

    // Traitement pour soumettre un nouvel événement
    @PostMapping("/createEvent")
    public String submitEvent(@RequestParam("intitule") String intitule,
            @RequestParam("theme") String theme,
            @RequestParam("date_debut") String date_debut,
            @RequestParam("duree") int duree,
            @RequestParam("nb_part_max") int nb_part_max,
            @RequestParam("description") String description,
            @RequestParam("organisateur") String organisation,
            @RequestParam("type_even") String type_even,
            Model model) {

        // Création d'un nouvel événement
        Evenement e = new Evenement(intitule, theme, date_debut, duree, nb_part_max, description, organisation,
                type_even);
        // Sauvegarde de l'événement
        evenementService.save(e);
        // Ajout de la liste mise à jour des événements à l'attribut du modèle
        model.addAttribute("evenements", evenementService.findAll());
        return "home"; // Retourne à la page d'accueil
    }

    // Traitement pour supprimer un événement
    @PostMapping("/deleteEvent")
    public String deleteEvent(@RequestParam("eventId") Long eventId, Model model) {
        // Supprime l'événement spécifié
        evenementService.delete(eventId);
        // Ajout de la liste mise à jour des événements à l'attribut du modèle
        model.addAttribute("evenements", evenementService.findAll());
        return "home"; // Retourne à la page d'accueil
    }

    // Affiche le formulaire pour modifier un événement
    @PostMapping("/goToModifyEvent")
    public String modifyEvent(@RequestParam("eventId") Long eventId, Model model) {
        // Récupère l'événement spécifié et l'ajoute à l'attribut du modèle
        model.addAttribute("event", evenementService.findById(eventId).get());
        return "modifyEvent"; // Affiche le formulaire de modification d'événement
    }

    // Traitement pour soumettre la modification d'un événement
    @PostMapping("/modifyEvent")
    public String submitModifiedEvent(@RequestParam("eventId") Long eventId, @ModelAttribute Evenement updatedEvent,
            Model model) {
        // Récupère l'événement à modifier
        Evenement e = evenementService.findById(eventId).get();
        // Met à jour les détails de l'événement avec les données soumises
        e.setEvenementDetails(updatedEvent);

        // Sauvegarde de l'événement modifié
        evenementService.save(e);
        // Ajout de la liste mise à jour des événements à l'attribut du modèle
        model.addAttribute("evenements", evenementService.findAll());
        return "home"; // Retourne à la page d'accueil
    }

    // Affiche la liste des événements
    @PostMapping("/goToEvents")
    public String listEvents(Model model) {
        // Récupère tous les événements et les ajoute à l'attribut du modèle
        List<Evenement> events = evenementService.findAll();
        model.addAttribute("events", events);
        return "events"; // Affiche la page listant tous les événements
    }
}
