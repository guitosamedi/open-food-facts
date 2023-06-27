package fr.diginamic.dal.jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMFProvider {
    private static EntityManagerFactory emf;

    /**
     * @return
     */
    public static EntityManagerFactory getEmf(){
        if (emf == null){
            Persistence.createEntityManagerFactory("app-off");
        }
        return emf;
    }

    /**
     *
     */
    public static void close(){
        emf.close();
    }
}
