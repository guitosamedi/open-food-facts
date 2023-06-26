package fr.diginamic.cli.ihm.menu.entree;

import fr.diginamic.cli.ScannerProvider;
import fr.diginamic.cli.services.AdditifService;
import fr.diginamic.entites.Additif;

import java.util.List;
import java.util.Scanner;

public class NAdditifsLesPlusCourantsEntreeMenu extends EntreeMenu {
    private static final String LIBELLE = "Les additifs les plus courants";

    public NAdditifsLesPlusCourantsEntreeMenu() {
        super(LIBELLE, TypeEntreeMenu.ACTION);
    }

    @Override
    public void action() {
        Scanner scanner = ScannerProvider.getScanner();

        System.out.println("Combien d'additifs souhaitez-vous afficher ?");
        int limit = scanner.nextInt();

        AdditifService additifService = AdditifService.getInstance();
        List<Additif> additifs = additifService.getAdditifsLesPlusCourants(limit);

        if (null != additifs) {
            for (Additif additif : additifs) {
                System.out.println("Les " + limit + " additifs les plus courants :");
                System.out.println(additif);
            }
        }
    }
}
