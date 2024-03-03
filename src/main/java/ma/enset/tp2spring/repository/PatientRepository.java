package ma.enset.tp2spring.repository;

import ma.enset.tp2spring.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByNom(String name);
}
