package ensg.tsi.j2e.colloques.repositories;

import ensg.tsi.j2e.colloques.metier.Administrateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepo extends CrudRepository<Administrateur, Long> {
}