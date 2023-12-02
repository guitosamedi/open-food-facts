package fr.diginamic;

import fr.diginamic.dal.jpa.EMFProvider;
import fr.diginamic.entites.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        EntityManager em1 = emf.createEntityManager();

        Produit produit1 = em1.find(Produit.class, 1);
        System.out.println("Produit 1 get with em1 id: " + produit1.getNom());

        EntityManager em2 = emf.createEntityManager();

        Produit produit2 = em2.find(Produit.class, 1);
        System.out.println("Produit 2 get with em2 id: " + produit1.getNom());

        EMFProvider.close();
    }
}
