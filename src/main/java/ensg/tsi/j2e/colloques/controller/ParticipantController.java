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

    public ParticipantController(ParticipantService participantService, EvenementService evenementService) {
        this.participantService = participantService;
        this.evenementService = evenementService;
    }


    @PostMapping("/goToEventParticipants")
    public String goToEventParticipants(@RequestParam("eventId") Long eventId, Model model) {
        Evenement e = evenementService.findById(eventId).get();
        model.addAttribute("event", e);
        return "eventParticipants";
    }


    @PostMapping("/goToRegister")
    public String goToRegister(@RequestParam("eventId") Long id, Model model) {
        model.addAttribute("eventId", id);
        return "register";
    }

    @PostMapping("/goToRegisterAdmin")
    public String goToRegisterAdmin(@RequestParam("eventId") Long id, Model model) {
        model.addAttribute("eventId", id);
        return "registerAdmin";
    }

    @PostMapping("/inscription")
    public String inscription(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom,
            @RequestParam("email") String email, @RequestParam("date_naiss") String date_naiss,
            @RequestParam("organisation") String organisation,
            @RequestParam("observations") String observations, @RequestParam("eventId") Long id, Model model) {

        Evenement e = evenementService.findById(id).get();
        Participant p = new Participant(nom, prenom, email, date_naiss, organisation, observations);

        // Check if participant's email already exists in the event's participants list
        boolean emailExists = e.getParticipants().stream().anyMatch(participant -> participant.getEmail().equals(email));
        if (emailExists) {
            model.addAttribute("error", "Email already exists in the event's participants list. Please use another email.");
            model.addAttribute("eventId", e.getNum_even());
            return "register";
        }else{
            participantService.save(p);
            e.addParticipant(p);
            evenementService.save(e);
            return "redirect:/";
        }


    }


    @PostMapping("/inscriptionAdmin")
    public String inscriptionAdmin(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom,
            @RequestParam("email") String email, @RequestParam("date_naiss") String date_naiss,
            @RequestParam("organisation") String organisation,
            @RequestParam("observations") String observations, @RequestParam("eventId") Long id, Model model) {

        Evenement e = evenementService.findById(id).get();
        Participant p = new Participant(nom, prenom, email, date_naiss, organisation, observations);

        // Check if participant's email already exists in the event's participants list
        boolean emailExists = e.getParticipants().stream().anyMatch(participant -> participant.getEmail().equals(email));
        if (emailExists) {
            model.addAttribute("error", "Email already exists in the event's participants list. Please use another email.");
            model.addAttribute("eventId", e.getNum_even());
            return "registerAdmin";
        }else{
            participantService.save(p);
            e.addParticipant(p);
            evenementService.save(e);
            model.addAttribute("event", e);
            return "eventParticipants";
        }
    }

    @PostMapping("/deleteEventParticipant")
    public String deleteParticipant(@RequestParam("participantId") Long participantId, @RequestParam("eventId") Long id,
            Model model) {
        Participant p = participantService.findById(participantId).get();
        Evenement e = evenementService.findById(id).get();
        participantService.delete(p);
        model.addAttribute("event", e);
        return "eventParticipants";
    }

    @PostMapping("/goToModifyParticipant")
    public String goToModifyParticipant(@RequestParam("participantId") Long participantId, @RequestParam("eventId") Long id,
            Model model) {
        Participant p = participantService.findById(participantId).get();
        Evenement e = evenementService.findById(id).get();
        model.addAttribute("participant", p);
        model.addAttribute("event", e);
        return "modifyParticipant";
    }

    @PostMapping("/modifyParticipant")
    public String modifyParticipant(@RequestParam("participantId") Long participantId, @RequestParam("eventId") Long id, @ModelAttribute Participant updatedParticipant,
            Model model) {
        Participant p = participantService.findById(participantId).get();
        Evenement e = evenementService.findById(id).get();

        p.updateParticipant(updatedParticipant);
        participantService.save(p);
        evenementService.save(e);

        model.addAttribute("event", e);
        return "eventParticipants";
    }

}

