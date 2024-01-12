package ensg.tsi.j2e.colloques.reporitories;

import ensg.tsi.j2e.colloques.metier.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepo extends CrudRepository<Participant, Long> {
}
