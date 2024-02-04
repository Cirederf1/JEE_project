package ensg.tsi.j2e.colloques.session;

import ensg.tsi.j2e.colloques.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ensg.tsi.j2e.colloques.metier.*;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EvenementRepo evenementRepository;
    private final ParticipantRepo participantRepository;
    private final AdministrateurRepo administrateurRepository;

    @Autowired
    public DataInitializer(EvenementRepo evenementRepository, ParticipantRepo participantRepository, AdministrateurRepo administrateurRepository) {
        this.evenementRepository = evenementRepository;
        this.participantRepository = participantRepository;
        this.administrateurRepository = administrateurRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Participant participant1 = new Participant("Doe", "John", "john.doe@example.com", "01/01/1995", "ENSG", "");
        Participant participant2 = new Participant("Ye", "Fredo", "john.doe@example.com", "01/01/1995", "ENSG", "");
        Participant participant3 = new Participant("Doe", "John", "john.doe@example.com", "01/01/1995", "ENSG", "");

        Evenement evenement1 = new Evenement("Intitule1", "Theme1", "2024-01-01", 2, 100, "Description1", "Organisateur1", "Type1");
        Evenement evenement2 = new Evenement("Intitule2", "Theme2", "2024-02-01", 3, 150, "Description2", "Organisateur2", "Type2");

        evenement1.addParticipant(participant1);
        evenement2.addParticipant(participant2);
        evenement2.addParticipant(participant3);

        evenementRepository.save(evenement1);
        evenementRepository.save(evenement2);

        evenementRepository.delete(evenement2);

        Administrateur admin = new Administrateur("admin", "admin");
        administrateurRepository.save(admin);
        
        List<Evenement> events = (List<Evenement>) evenementRepository.findAll();

        System.out.println(events.get(0).getIntitule());


    }
}
