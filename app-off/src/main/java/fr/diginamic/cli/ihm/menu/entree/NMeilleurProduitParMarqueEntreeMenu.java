package fr.diginamic.cli.ihm.menu.entree;

import fr.diginamic.cli.ScannerProvider;
import fr.diginamic.cli.services.ProduitService;
import fr.diginamic.entites.Produit;

import java.util.List;
import java.util.Scanner;

public class NMeilleurProduitParMarqueEntreeMenu extends EntreeMenu {
    private static final String LIBELLE = "Les meilleurs produits d'une marque";

    public NMeilleurProduitParMarqueEntreeMenu() {
        super(LIBELLE, TypeEntreeMenu.ACTION);
    }

    @Override
    public void action() {
        Scanner scanner = ScannerProvider.getScanner();

        System.out.println("Choix de la maque :");
        String marque = scanner.next();

        System.out.println("Combien de produits souhaitez-vous afficher ?");
        int limit = scanner.nextInt();

        ProduitService produitService = ProduitService.getInstance();
        List<Produit> produits = produitService.getMeilleursProduitsParMarque(marque, limit);

        if (null != produits) {
            System.out.println("Les " + limit + " meilleurs produits de la marque " + marque + " :");
            for (Produit produit : produits) {
                System.out.println(produit);
            }
        }
    }
}
