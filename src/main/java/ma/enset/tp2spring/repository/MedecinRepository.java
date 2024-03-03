package ma.enset.tp2spring.repository;

import ma.enset.tp2spring.entities.Medecin;
import ma.enset.tp2spring.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findByNom(String nom);
}
