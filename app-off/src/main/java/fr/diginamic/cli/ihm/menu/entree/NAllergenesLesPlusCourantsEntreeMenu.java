package fr.diginamic.cli.ihm.menu.entree;

import fr.diginamic.cli.ScannerProvider;
import fr.diginamic.cli.services.AllergeneService;
import fr.diginamic.entites.Allergene;

import java.util.List;
import java.util.Scanner;

/**
 * La classe NAllergenesLesPlusCourantsEntreeMenu représente une entrée de menu pour afficher les allergènes les plus courants.
 */
public class NAllergenesLesPlusCourantsEntreeMenu extends EntreeMenu {

    /**
     * Libellé de l'entrée de menu.
     */
    private static final String LIBELLE = "Les allergènes les plus courants";

    /**
     * Constructeur de la classe NAllergenesLesPlusCourantsEntreeMenu.
     */
    public NAllergenesLesPlusCourantsEntreeMenu() {
        super(LIBELLE, TypeEntreeMenu.ACTION);
    }

    /**
     * Exécute l'action associée à l'entrée du menu, qui consiste à afficher les allergènes les plus courants dans la limite saisie par l'utilisateur.
     */
    @Override
    public void action() {
        Scanner scanner = ScannerProvider.getScanner();

        System.out.println("Combien d'allergènes souhaitez-vous afficher ?");
        int limit = scanner.nextInt();

        AllergeneService allergeneService = AllergeneService.getInstance();
        List<Allergene> allergenes = allergeneService.getAllergenesLesPlusCourants(limit);

        if (null != allergenes) {
            System.out.println("Les " + limit + " allergènes les plus courants :");
            for (Allergene allergene : allergenes) {
                    System.out.println(allergene + " qui apparaît dans :" + allergene.getProduits().size() + " produit(s)");
            }

        }else{
            System.out.println("Aucun résultat dans la base.");
        }
    }
}
