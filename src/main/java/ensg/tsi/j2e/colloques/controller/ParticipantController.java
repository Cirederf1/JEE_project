package ensg.tsi.j2e.colloques.controller;

import ensg.tsi.j2e.colloques.metier.Evenement;
import ensg.tsi.j2e.colloques.metier.Participant;
import ensg.tsi.j2e.colloques.services.EvenementService;
import ensg.tsi.j2e.colloques.services.ParticipantService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParticipantController {

    private final EvenementService evenementService;
    private final ParticipantService participantService;

    // Constructeur prenant le service de participants et le service d'événements
    public ParticipantController(ParticipantService participantService, EvenementService evenementService) {
        this.participantService = participantService;
        this.evenementService = evenementService;
    }

    // Affiche la liste des participants pour un événement spécifique
    @PostMapping("/goToEventParticipants")
    public String goToEventParticipants(@RequestParam("eventId") Long eventId, Model model) {
        Evenement e = evenementService.findById(eventId).get();
        model.addAttribute("event", e);
        return "eventParticipants"; // Retourne la vue d'affichage des participants à l'événement
    }

    // Redirige vers la page d'inscription pour un événement spécifique
    @PostMapping("/goToRegister")
    public String goToRegister(@RequestParam("eventId") Long id, Model model) {
        model.addAttribute("eventId", id);
        return "register"; // Retourne la vue d'inscription
    }

    // Redirige vers la page d'inscription pour un administrateur à un événement
    // spécifique
    @PostMapping("/goToRegisterAdmin")
    public String goToRegisterAdmin(@RequestParam("eventId") Long id, Model model) {
        model.addAttribute("eventId", id);
        return "registerAdmin"; // Retourne la vue d'inscription pour un administrateur
    }

    // Traitement de l'inscription d'un participant à un événement
    @PostMapping("/inscription")
    public String inscription(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom,
            @RequestParam("email") String email, @RequestParam("date_naiss") String date_naiss,
            @RequestParam("organisation") String organisation,
            @RequestParam("observations") String observations, @RequestParam("eventId") Long id, Model model) {

        Evenement e = evenementService.findById(id).get();
        Participant p = new Participant(nom, prenom, email, date_naiss, organisation, observations);

        // Vérifie si l'e-mail du participant existe déjà dans la liste des participants
        // de l'événement
        boolean emailExists = e.getParticipants().stream()
                .anyMatch(participant -> participant.getEmail().equals(email));
        if (emailExists) {
            // Ajoute un message d'erreur si l'e-mail existe déjà et redirige vers la page
            // d'inscription
            model.addAttribute("error",
                    "Email already exists in the event's participants list. Please use another email.");
            model.addAttribute("eventId", e.getNum_even());
            return "register";
        } else {
            // Enregistre le participant, l'ajoute à l'événement et redirige vers la page
            // d'accueil
            participantService.save(p);
            e.addParticipant(p);
            evenementService.save(e);
            return "redirect:/";
        }

    }

    // Traitement de l'inscription d'un administrateur à un événement
    @PostMapping("/inscriptionAdmin")
    public String inscriptionAdmin(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom,
            @RequestParam("email") String email, @RequestParam("date_naiss") String date_naiss,
            @RequestParam("organisation") String organisation,
            @RequestParam("observations") String observations, @RequestParam("eventId") Long id, Model model) {

        Evenement e = evenementService.findById(id).get();
        Participant p = new Participant(nom, prenom, email, date_naiss, organisation, observations);

        // Vérifie si l'e-mail du participant existe déjà dans la liste des participants
        // de l'événement
        boolean emailExists = e.getParticipants().stream()
                .anyMatch(participant -> participant.getEmail().equals(email));
        if (emailExists) {
            // Ajoute un message d'erreur si l'e-mail existe déjà et redirige vers la page
            // d'inscription pour l'administrateur
            model.addAttribute("error",
                    "Email already exists in the event's participants list. Please use another email.");
            model.addAttribute("eventId", e.getNum_even());
            return "registerAdmin";
        } else {
            // Enregistre le participant, l'ajoute à l'événement et redirige vers la page
            // des participants de l'événement
            participantService.save(p);
            e.addParticipant(p);
            evenementService.save(e);
            model.addAttribute("event", e);
            return "eventParticipants";
        }
    }

    // Supprime un participant d'un événement
    @PostMapping("/deleteEventParticipant")
    public String deleteParticipant(@RequestParam("participantId") Long participantId, @RequestParam("eventId") Long id,
            Model model) {
        Participant p = participantService.findById(participantId).get();
        Evenement e = evenementService.findById(id).get();
        participantService.delete(p);
        model.addAttribute("event", e);
        return "eventParticipants"; // Retourne la vue des participants de l'événement après la suppression
    }

    // Redirige vers la page de modification d'un participant
    @PostMapping("/goToModifyParticipant")
    public String goToModifyParticipant(@RequestParam("participantId") Long participantId,
            @RequestParam("eventId") Long id,
            Model model) {
        Participant p = participantService.findById(participantId).get();
        Evenement e = evenementService.findById(id).get();
        model.addAttribute("participant", p);
        model.addAttribute("event", e);
        return "modifyParticipant"; // Retourne la vue de modification d'un participant
    }

    // Traite la modification d'un participant
    @PostMapping("/modifyParticipant")
    public String modifyParticipant(@RequestParam("participantId") Long participantId, @RequestParam("eventId") Long id,
            @ModelAttribute Participant updatedParticipant,
            Model model) {
        Participant p = participantService.findById(participantId).get();
        Evenement e = evenementService.findById(id).get();

        p.updateParticipant(updatedParticipant);
        participantService.save(p);
        evenementService.save(e);

        model.addAttribute("event", e);
        return "eventParticipants"; // Retourne la vue des participants de l'événement après la modification
    }

}
