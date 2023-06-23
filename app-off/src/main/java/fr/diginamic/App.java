package fr.diginamic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("app-off");
        EntityManager em = emf.createEntityManager();
        System.out.println(em);
        em.close();
        emf.close();
    }
}
