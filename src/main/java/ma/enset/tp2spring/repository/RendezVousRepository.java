package ma.enset.tp2spring.repository;

import ma.enset.tp2spring.entities.Patient;
import ma.enset.tp2spring.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

}
