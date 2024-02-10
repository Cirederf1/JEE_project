package ensg.tsi.j2e.colloques.controller;

import ensg.tsi.j2e.colloques.metier.Evenement;
import ensg.tsi.j2e.colloques.services.EvenementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParticipantController {

    private final EvenementService evenementService;

    public ParticipantController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @PostMapping("/goToRegister")
    public String goToRegister() {
        return "register";
    }

    @PostMapping("/goToEventParticipants")
    public String goToEventParticipants(@RequestParam("eventId") Long eventId, Model model) {
        Evenement e = evenementService.findById(eventId).get();
        model.addAttribute("participants", e.getParticipants());
        return "eventParticipants";
    }

}
