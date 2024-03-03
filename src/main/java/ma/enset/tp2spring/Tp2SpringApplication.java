package ma.enset.tp2spring;

import ma.enset.tp2spring.entities.*;
import ma.enset.tp2spring.repository.ConsultationRepository;
import ma.enset.tp2spring.repository.MedecinRepository;
import ma.enset.tp2spring.repository.PatientRepository;
import ma.enset.tp2spring.repository.RendezVousRepository;
import ma.enset.tp2spring.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
public class Tp2SpringApplication {



    public static void main(String[] args) {

        SpringApplication.run(Tp2SpringApplication.class, args);
    }

    @Bean
    CommandLineRunner start(HospitalService hospitalService ,MedecinRepository medecinRepository,PatientRepository patientRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        return args -> {
            Stream.of("ahmed","karim","fatima").forEach(name->{
                Patient patient = new Patient();
                patient.setNom(name);
                patient.setDate(new Date());
                patient.setMalade(false);
                hospitalService.savePatient(patient);
            });
            Stream.of("ayman","hanane","yasmine").forEach(name->{
                Medecin medecin = new Medecin();
                medecin.setNom(name);
                medecin.setEmail(name+"@gmail.com");
                medecin.setSpecialisation(Math.random()>0.5?"Cardio": "Dentiste");
                hospitalService.saveMedecin(medecin);
            });

            Patient patient=patientRepository.findById(1L).orElse(null);
            Patient patient1 = patientRepository.findByNom("karim");

            Medecin medecin = medecinRepository.findByNom("yasmine");

            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);
            hospitalService.saveRDV(rendezVous);


            RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);
            Consultation consultation = new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("rapport de consultation....");
            hospitalService.saveConsultation(consultation);


            /*patientRepository.save(new Patient(null, "ahmed", new Date(), false, 10));
            patientRepository.save(new Patient(null, "karim", new Date(), true, 50));
            patientRepository.save(new Patient(null, "fatima", new Date(), false, 02));
            List<Patient> patients = patientRepository.findAll();
            patients.forEach(P -> {
                System.out.println(P.toString());
            });
            Patient patient = patientRepository.findById(Long.valueOf(1)).get();
            System.out.println("****************--- find patient with id ---**********************");
            System.out.println(patient.getId());
            System.out.println(patient.getNom());
            System.out.println(patient.getScore());
            System.out.println("*******************************************************************");
            //-----------------------delete patient----------------------
            Long idToDelete = 1L;
            Optional<Patient> patient1 = patientRepository.findById(Long.valueOf(idToDelete));
            if (patient1.isPresent()) {
                patientRepository.deleteById(idToDelete);

            } else {
                System.out.println("patient doesn't existe in database");
            }
            List<Patient> patientsRestants = patientRepository.findAll();
            System.out.println("les patients restant!!!!!");
            patientsRestants.forEach(System.out::println);


            // -------------------update patient--------------------
            Long idToUpdate = 2L;
            Optional<Patient> patient2 = patientRepository.findById(Long.valueOf(idToUpdate));
            if (patient2.isPresent()) {
                Patient patientAMettreAJour = patient2.get();

                patientAMettreAJour.setNom("YAHYA-IMANE");
                patientAMettreAJour.setMalade(false);
                patientAMettreAJour.setScore(01);

                Patient patientMaj = patientRepository.save(patientAMettreAJour);
                System.out.println("Patient mis à jour : " + patientMaj);
            } else {
                System.out.println("there is no patient with this id");}
*/
        };

    }
}
