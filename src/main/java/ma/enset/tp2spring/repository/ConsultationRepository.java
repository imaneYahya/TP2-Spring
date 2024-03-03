package ma.enset.tp2spring.repository;

import ma.enset.tp2spring.entities.Consultation;
import ma.enset.tp2spring.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

}
