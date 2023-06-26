package fr.diginamic.cli.ihm.menu.entree;

import fr.diginamic.cli.ScannerProvider;
import fr.diginamic.cli.services.AllergeneService;
import fr.diginamic.entites.Allergene;

import java.util.List;
import java.util.Scanner;

public class NAllergenesLesPlusCourantsEntreeMenu extends EntreeMenu {
    private static final String LIBELLE = "Les allergènes le plus courants";

    public NAllergenesLesPlusCourantsEntreeMenu() {
        super(LIBELLE, TypeEntreeMenu.ACTION);
    }

    @Override
    public void action() {
        Scanner scanner = ScannerProvider.getScanner();

        System.out.println("Combien d'allergènes souhaitez-vous afficher ?");
        int limit = scanner.nextInt();

        AllergeneService allergeneService = AllergeneService.getInstance();
        List<Allergene> allergenes = allergeneService.getAllergenesLesPlusCourants(limit);

        if (null != allergenes) {
            for (Allergene allergene : allergenes) {
                System.out.println("Les " + limit + " allergènes les plus courants :");
                System.out.println(allergene);
            }
        }
    }
}
