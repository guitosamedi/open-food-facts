package fr.diginamic;

import fr.diginamic.dal.jpa.ProduitDAO;
import fr.diginamic.entites.Produit;

import java.util.List;

public class App {
    public static void main(String[] args) {

        // Instance ProduitDAO
         ProduitDAO dao = new ProduitDAO();

        // Liste des produits
        List<Produit> produits = dao.findAllProduit();

        for (Produit produit : produits) {
                System.out.println("****** Afficher la liste des produits ******");
                System.out.println(produit);

        }
    }
}
