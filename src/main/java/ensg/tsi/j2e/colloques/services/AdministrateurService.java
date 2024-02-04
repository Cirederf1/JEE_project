package ensg.tsi.j2e.colloques.services;

import ensg.tsi.j2e.colloques.metier.Administrateur;
import ensg.tsi.j2e.colloques.repositories.AdministrateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepo repository;

    public List<Administrateur> findAll() {
        return (List<Administrateur>) repository.findAll();
    }

    public void save(Administrateur administrateur) {repository.save(administrateur);}

    public Optional<Administrateur> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Administrateur administrateur) {
        repository.delete(administrateur);
    }
}

