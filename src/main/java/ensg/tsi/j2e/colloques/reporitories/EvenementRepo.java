package ensg.tsi.j2e.colloques.reporitories;

import ensg.tsi.j2e.colloques.metier.Evenement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepo extends CrudRepository<Evenement, Long> {
}
