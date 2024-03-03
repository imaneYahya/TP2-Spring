                                                Activité Pratique N°2 : ORM, Jpa, Hibernate Spring Data
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



https://github.com/imaneYahya/TP2-Spring/issues/2
