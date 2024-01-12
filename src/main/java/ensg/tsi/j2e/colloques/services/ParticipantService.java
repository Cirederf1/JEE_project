package ensg.tsi.j2e.colloques.services;

import ensg.tsi.j2e.colloques.metier.Participant;
import ensg.tsi.j2e.colloques.reporitories.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepo repository;

    public List<Participant> findAll() {
        return (List<Participant>) repository.findAll();
    }

    public void save(Participant participant) {repository.save(participant);}

    public Optional<Participant> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Participant participant) {
        repository.delete(participant);
    }
}

