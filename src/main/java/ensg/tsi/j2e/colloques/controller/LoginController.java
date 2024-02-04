package ensg.tsi.j2e.colloques.controller;

import ensg.tsi.j2e.colloques.metier.Administrateur;
import ensg.tsi.j2e.colloques.repositories.AdministrateurRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final AdministrateurRepo administrateurRepo;

    public LoginController(AdministrateurRepo administrateurRepo) {
        this.administrateurRepo = administrateurRepo;
    }

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        // Vérifier si l'utilisateur est présent dans la table Administrateur
        if (this.isUserInAdministrateurTable(username, password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username does not exist or password is incorrect. Please try again.");
            return "login";
        }
    }

    public boolean isUserInAdministrateurTable(String username, String password) {
        for (Administrateur admin : administrateurRepo.findAll()) {
            if (admin.getUsername().equals(username) && admin.getMotDePasse().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
