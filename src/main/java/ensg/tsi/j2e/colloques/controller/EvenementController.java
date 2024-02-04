package ensg.tsi.j2e.colloques.controller;

import ensg.tsi.j2e.colloques.metier.Evenement;
import ensg.tsi.j2e.colloques.services.EvenementService;
import ensg.tsi.j2e.colloques.repositories.AdministrateurRepo;
import ensg.tsi.j2e.colloques.repositories.EvenementRepo;
import ensg.tsi.j2e.colloques.services.EvenementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EvenementController {
    private final EvenementService evenementService;
    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        model.addAttribute("evenements", evenementService.findAll());
        return "home";
    }

    @GetMapping("/createEvent")
    public String createEvent() {
        return "createEvent";
    }

    @PostMapping("/createEvent")
    public String submitEvent(@RequestParam("intitule") String intitule,
                              @RequestParam("theme") String theme,
                              @RequestParam("date_debut") String date_debut,
                              @RequestParam("duree") int duree,
                              @RequestParam("nb_part_max") int nb_part_max,
                              @RequestParam("description") String description,
                              @RequestParam("organisateur") String organisation,
                              @RequestParam("type_even") String type_even,
                              Model model){

        Evenement e = new Evenement(intitule, theme, date_debut, duree, nb_part_max, description, organisation, type_even);
        evenementService.save(e);
        return "redirect:/home";
    }

    @PostMapping("/deleteEvent")
    public String deleteEvent(@RequestParam("eventId") Long eventId) {
        evenementService.delete(eventId);
        return "redirect:/home";
    }

}
