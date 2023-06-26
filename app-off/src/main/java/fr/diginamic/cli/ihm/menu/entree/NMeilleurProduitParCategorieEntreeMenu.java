package fr.diginamic.cli.ihm.menu.entree;

import fr.diginamic.cli.ScannerProvider;
import fr.diginamic.cli.services.ProduitService;
import fr.diginamic.entites.Produit;

import java.util.List;
import java.util.Scanner;

public class NMeilleurProduitParCategorieEntreeMenu extends EntreeMenu {
    private static final String LIBELLE = "Les meilleurs produits pour une catégorie";

    public NMeilleurProduitParCategorieEntreeMenu() {
        super(LIBELLE, TypeEntreeMenu.ACTION);
    }

    @Override
    public void action() {
        Scanner scanner = ScannerProvider.getScanner();

        System.out.println("Choix de la catégorie :");
        String categorie = scanner.next();

        System.out.println("Combien de produits souhaitez-vous afficher ?");
        int limit = scanner.nextInt();

        ProduitService produitService = ProduitService.getInstance();
        List<Produit> produits = produitService.getMeilleursProduitsParCategorie(categorie, limit);

        if (null != produits) {
            System.out.println("Les " + limit + " meilleurs produits de la catégorie " + categorie + " :");
            for (Produit produit : produits) {
                System.out.println(produit);
            }
        }
    }
}
