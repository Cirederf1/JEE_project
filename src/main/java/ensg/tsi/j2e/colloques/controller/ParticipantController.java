package ensg.tsi.j2e.colloques.controller;

import ensg.tsi.j2e.colloques.metier.Evenement;
import ensg.tsi.j2e.colloques.metier.Participant;
import ensg.tsi.j2e.colloques.services.EvenementService;
import ensg.tsi.j2e.colloques.services.ParticipantService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParticipantController {

    private final EvenementService evenementService;
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService, EvenementService evenementService) {
        this.participantService = participantService;
        this.evenementService = evenementService;
    }


    @PostMapping("/goToEventParticipants")
    public String goToEventParticipants(@RequestParam("eventId") Long eventId, Model model) {
        Evenement e = evenementService.findById(eventId).get();
        model.addAttribute("participants", e.getParticipants());
        return "eventParticipants";
    }


    @PostMapping("/goToRegister")
    public String goToRegister(@RequestParam("eventId") Long id, Model model) {
        model.addAttribute("eventId", id);
        return "register";
    }

    @PostMapping("/inscription")
    public String test(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom,
            @RequestParam("email") String email, @RequestParam("date_naiss") String date_naiss,
            @RequestParam("organisation") String organisation,
            @RequestParam("observations") String observations, @RequestParam("eventId") Long id) {

        Evenement e = evenementService.findById(id).get();
        Participant p = new Participant(nom, prenom, email, date_naiss, organisation, observations);
        participantService.save(p);
        e.addParticipant(p);
        evenementService.save(e);
        return "redirect:/";
    }

}

