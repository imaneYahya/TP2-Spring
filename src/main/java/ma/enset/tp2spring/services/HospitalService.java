package ma.enset.tp2spring.services;

import ma.enset.tp2spring.entities.Consultation;
import ma.enset.tp2spring.entities.Medecin;
import ma.enset.tp2spring.entities.Patient;
import ma.enset.tp2spring.entities.RendezVous;

public interface HospitalService {
     Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);

}
