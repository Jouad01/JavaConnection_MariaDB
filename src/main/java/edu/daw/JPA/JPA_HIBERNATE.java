package edu.daw.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPA_HIBERNATE {
        private static final EntityManagerFactory emFactory;

        static {
            emFactory = Persistence.createEntityManagerFactory("LibraryPersistenceUnit");
        }

        public static EntityManager getEntityManager() {
            return emFactory.createEntityManager();
        }
    }

