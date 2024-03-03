##Activité Pratique N°2 : ORM, Jpa, Hibernate Spring Data##

1/Créer l'entité JPA Patient 

                public class Patient {
                        @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
                        private Long id;
                        private String nom;
                        private Date date;
                        private boolean malade;
                        private int score;
                        }

2/Configuration de  l'unité de persistance dans le ficher application.properties 

          server.port=8085
          spring.datasource.url=jdbc:h2:mem:patients-db
          spring.h2.console.enabled=true



![image](https://github.com/imaneYahya/TP2-Spring/assets/117214055/c37da3bd-18f6-4e06-a4e5-92e0e67f2ca4)

 6/ Tester quelques opérations de gestion de patients :
       - Ajouter des patients:
              
  la méthode 1/
                 
                 patientRepository.save(new Patient(null, "ahmed", new Date(), false, 10));
                        patientRepository.save(new Patient(null, "karim", new Date(), true, 50));
                        patientRepository.save(new Patient(null, "fatima", new Date(), false, 02));
                        List<Patient> patients = patientRepository.findAll();
                        patients.forEach(P -> {
                        System.out.println(P.toString());
                  });

  la methode 2/

                      return args -> {
                              Stream.of("ahmed","karim","fatima").forEach(name->{
                              Patient patient = new Patient();
                              patient.setNom(name);
                              patient.setDate(new Date());
                              patient.setMalade(false);
                              hospitalService.savePatient(patient);
                        });

- Chercher touts les patients:

         Patient patient = patientRepository.findById(Long.valueOf(1)).get();
            System.out.println("****************--- find patient with id ---**********************");
            System.out.println(patient.getId());
            System.out.println(patient.getNom());
            System.out.println(patient.getScore());
            System.out.println("*******************************************************************");

voila le resultat de chercher un patient par son id et de chercher touts les patients exist:
![image](https://github.com/imaneYahya/TP2-Spring/assets/117214055/d6a91088-b95a-48b1-8863-bffc24da60d2)

  - supprimer un patient:

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

      ![image](https://github.com/imaneYahya/TP2-Spring/assets/117214055/bc5dca27-5a1a-4243-8c85-fb53220b441f)

  - Mettre à jour un patient:

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


![image](https://github.com/imaneYahya/TP2-Spring/assets/117214055/4f7bc4ce-9a25-4b0f-b09b-e98134173e0b)

4/ Migrer de H2 Database vers MySQL:

              spring.datasource.url=jdbc:mysql://localhost/patients-db?createDataBaseIfNotExist=false
              spring.datasource.username = root
              spring.datasource.password =
              spring.datasource.driverClassName = com.mysql.jdbc.Driver
              spring.jpa.show-sql = true
              spring.jpa.hibernate.ddl-auto = create
              spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MariaDBDialect
 

  ![image](https://github.com/imaneYahya/TP2-Spring/assets/117214055/80496b5a-7b85-4f74-9a80-166ef7822ca5)

5/ la création des l'entité JPA Medecin, Consultation, RendezVous,Status:
    - Medecin:

              public class Medecin {
                      @Id
                      @GeneratedValue(strategy =  GenerationType.IDENTITY)
                      private Long id ;
                      private String nom;
                      private String email;
                      private  String specialisation;
                      @OneToMany(mappedBy = "medecin",fetch = FetchType.LAZY)
                      private Collection<RendezVous> rendezVous;

          }

  - MedecinRepository:
  
            public interface MedecinRepository extends JpaRepository<Medecin, Long> {
              Medecin findByNom(String nom);
          }
 - Consultation:

                     public class Consultation {
                                @Id
                                @GeneratedValue(strategy =  GenerationType.IDENTITY)
                            
                                private  Long id;
                                private Date dateConsultation;
                                private String rapport;
                                @OneToOne
                                private  RendezVous rendezVous;
                            
                            }
   - RendezVous:
     
                 public class RendezVous {
                       @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
                      private Long id;
                      private Date date;
                      private boolean annule;
                      @Enumerated(EnumType.STRING)
                      private StatusRDV status;
                      @ManyToOne
                      private Patient patient;
                      @ManyToOne
                      private Medecin medecin;
                      @OneToOne(mappedBy = "rendezVous",fetch = FetchType.LAZY)
                      private Consultation consultation;
                  
                  
                  }
- status:
  
            public enum StatusRDV {
                PENDING,
                CANCELED,
                DONE,
            }




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
                         

          };
        
            }
   
