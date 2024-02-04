package ensg.tsi.j2e.colloques.services;

import ensg.tsi.j2e.colloques.metier.Evenement;
import ensg.tsi.j2e.colloques.repositories.EvenementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EvenementService {

    @Autowired
    private EvenementRepo repository;

    public List<Evenement> findAll() {
        return (List<Evenement>) repository.findAll();
    }

    public void save(Evenement evenement) {repository.save(evenement);}

    public Optional<Evenement> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Evenement evenement) {
        repository.delete(evenement);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

