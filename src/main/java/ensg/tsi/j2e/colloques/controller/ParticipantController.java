package ensg.tsi.j2e.colloques.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ParticipantController {

    @PostMapping("/goToRegister")
    public String goToRegister() {
        return "register";
    }
}
