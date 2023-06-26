package fr.diginamic;

import fr.diginamic.dal.jpa.ProduitDAO;
import fr.diginamic.entites.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class App {
    public static void main(String[] args) {

        // Instance ProduitDAO
         ProduitDAO dao = new ProduitDAO();

        // Liste des produits
        List<Produit> produits = dao.findAll();

        for (Produit produit : produits) {
                System.out.println("****** Afficher la liste des produits ******");
                System.out.println(produit);

        }

    }
}
