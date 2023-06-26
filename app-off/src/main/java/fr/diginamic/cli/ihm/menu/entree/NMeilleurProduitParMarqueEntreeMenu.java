package fr.diginamic.cli.ihm.menu.entree;

import fr.diginamic.cli.ScannerProvider;
import fr.diginamic.cli.services.ProduitService;
import fr.diginamic.entites.Produit;

import java.util.List;
import java.util.Scanner;

/**
 * La classe NMeilleurProduitParMarqueEntreeMenu représente une entrée de menu pour afficher les meilleurs produits d'une marque donnée.
 */
public class NMeilleurProduitParMarqueEntreeMenu extends EntreeMenu {

    /**
     * Libellé de l'entrée de menu.
     */
    private static final String LIBELLE = "Les meilleurs produits d'une marque";

    /**
     * Constructeur de la classe NMeilleurProduitParMarqueEntreeMenu.
     */
    public NMeilleurProduitParMarqueEntreeMenu() {
        super(LIBELLE, TypeEntreeMenu.ACTION);
    }

    /**
     * Exécute l'action associée à l'entrée du menu, qui consiste à afficher les meilleurs produits d'une marque donnée dans la limite saisie par l'utilisateur.
     */
    @Override
    public void action() {
        Scanner scanner = ScannerProvider.getScanner();

        System.out.println("Choix de la marque :");
        String marque = scanner.next();

        System.out.println("Combien de produits souhaitez-vous afficher ?");
        int limit = scanner.nextInt();

        ProduitService produitService = ProduitService.getInstance();
        List<Produit> produits = produitService.getMeilleursProduitsParMarque(marque, limit);

        if (null != produits) {
            System.out.println("Les " + limit + " meilleurs produits de la marque " + marque + " :");
            for (Produit produit : produits) {
                System.out.println(produit);
            }
        }
    }
}
