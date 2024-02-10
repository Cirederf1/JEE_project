package ensg.tsi.j2e.colloques.controller;

import ensg.tsi.j2e.colloques.metier.Administrateur;
import ensg.tsi.j2e.colloques.repositories.AdministrateurRepo;
import ensg.tsi.j2e.colloques.services.EvenementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final AdministrateurRepo administrateurRepo;
    private final EvenementService evenementService;

    // Constructeur prenant l'administrateur Repository et le service d'événements
    public LoginController(AdministrateurRepo administrateurRepo, EvenementService evenementService) {
        this.administrateurRepo = administrateurRepo;
        this.evenementService = evenementService;
    }

    // Affiche la page de connexion lorsque l'utilisateur accède à la racine de
    // l'application
    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    // Traitement pour la soumission du formulaire de connexion
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
            Model model) {
        // Vérifier si l'utilisateur est présent dans la table Administrateur
        if (this.isUserInAdministrateurTable(username, password)) {
            // Ajoute la liste des événements à l'attribut du modèle
            model.addAttribute("evenements", evenementService.findAll());
            return "home"; // Redirige vers la page d'accueil si l'utilisateur est authentifié
        } else {
            // Ajoute un message d'erreur à l'attribut du modèle et redirige vers la page de
            // connexion
            model.addAttribute("error", "Username does not exist or password is incorrect. Please try again.");
            return "login";
        }
    }

    // Vérifie si l'utilisateur existe dans la table Administrateur
    public boolean isUserInAdministrateurTable(String username, String password) {
        for (Administrateur admin : administrateurRepo.findAll()) {
            if (admin.getUsername().equals(username) && admin.getMotDePasse().equals(password)) {
                return true; // Retourne true si l'utilisateur est trouvé
            }
        }
        return false; // Retourne false si l'utilisateur n'est pas trouvé
    }

}
