package fr.diginamic.cli.ihm.menu.entree;

import fr.diginamic.cli.ScannerProvider;
import fr.diginamic.cli.services.ProduitService;
import fr.diginamic.entites.Produit;

import java.util.List;
import java.util.Scanner;

/**
 * La classe NMeilleurProduitParCategorieEntreeMenu représente une entrée de menu pour afficher les meilleurs produits pour une catégorie donnée.
 */
public class NMeilleurProduitParCategorieEntreeMenu extends EntreeMenu {

    /**
     * Libellé de l'entrée de menu.
     */
    private static final String LIBELLE = "Les meilleurs produits pour une catégorie";

    /**
     * Constructeur de la classe NMeilleurProduitParCategorieEntreeMenu.
     */
    public NMeilleurProduitParCategorieEntreeMenu() {
        super(LIBELLE, TypeEntreeMenu.ACTION);
    }

    /**
     * Exécute l'action associée à l'entrée du menu, qui consiste à afficher les meilleurs produits pour une catégorie donnée dans la limite saisie par l'utilisateur.
     */
    @Override
    public void action() {
        Scanner scanner = ScannerProvider.getScanner();

        System.out.println("Choix de la catégorie :");
        String categorie = scanner.nextLine();

        System.out.println("Combien de produits souhaitez-vous afficher ?");
        int limit = scanner.nextInt();
        scanner.nextLine();

        ProduitService produitService = ProduitService.getInstance();
        List<Produit> produits = produitService.getMeilleursProduitsParCategorie(categorie, limit);

        if (null != produits) {
            System.out.println("Les " + limit + " meilleurs produits de la catégorie " + categorie + " :");
            for (Produit produit : produits) {
                System.out.println(produit);
            }
        }
    }
}
