package fr.diginamic.cli.ihm.menu.entree;

import fr.diginamic.cli.ScannerProvider;
import fr.diginamic.cli.services.AdditifService;
import fr.diginamic.entites.Additif;

import java.util.List;
import java.util.Scanner;

/**
 * La classe NAdditifsLesPlusCourantsEntreeMenu représente une entrée de menu pour afficher les additifs les plus courants.
 */
public class NAdditifsLesPlusCourantsEntreeMenu extends EntreeMenu {

    /**
     * Libellé de l'entrée de menu.
     */
    private static final String LIBELLE = "Les additifs les plus courants";

    /**
     * Constructeur de la classe NAdditifsLesPlusCourantsEntreeMenu.
     */
    public NAdditifsLesPlusCourantsEntreeMenu() {
        super(LIBELLE, TypeEntreeMenu.ACTION);
    }

    /**
     * Exécute l'action associée à l'entrée du menu, qui consiste à afficher les additifs les plus courants dans la limite saisie par l'utilisateur.
     */
    @Override
    public void action() {
        Scanner scanner = ScannerProvider.getScanner();

        System.out.println("Combien d'additifs souhaitez-vous afficher ?");
        int limit = scanner.nextInt();

        AdditifService additifService = AdditifService.getInstance();
        List<Additif> additifs = additifService.getAdditifsLesPlusCourants(limit);

        if (null != additifs) {
            System.out.println("Les " + limit + " additifs les plus courants :");
            for (Additif additif : additifs) {
                System.out.println(additif);
            }
        }
    }
}
