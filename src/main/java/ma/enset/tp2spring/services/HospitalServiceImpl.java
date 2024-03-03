package ma.enset.tp2spring.services;

import jakarta.transaction.Transactional;
import ma.enset.tp2spring.entities.Consultation;
import ma.enset.tp2spring.entities.Medecin;
import ma.enset.tp2spring.entities.Patient;
import ma.enset.tp2spring.entities.RendezVous;
import ma.enset.tp2spring.repository.ConsultationRepository;
import ma.enset.tp2spring.repository.MedecinRepository;
import ma.enset.tp2spring.repository.PatientRepository;
import ma.enset.tp2spring.repository.RendezVousRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HospitalServiceImpl implements HospitalService {
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {

        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRDV(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {

        return consultationRepository.save(consultation);
    }
}
