package fr.diginamic.cli.ihm.menu.entree;

import fr.diginamic.cli.ScannerProvider;
import fr.diginamic.cli.services.IngredientService;
import fr.diginamic.entites.Ingredient;

import java.util.List;
import java.util.Scanner;

/**
 * La classe NIngredientsLesPlusCourantsEntreeMenu représente une entrée de menu pour afficher les ingrédients les plus courants.
 */
public class NIngredientsLesPlusCourantsEntreeMenu extends EntreeMenu {

    /**
     * Libellé de l'entrée de menu.
     */
    private static final String LIBELLE = "Les ingrédients les plus courants";

    /**
     * Constructeur de la classe NIngredientsLesPlusCourantsEntreeMenu.
     */
    public NIngredientsLesPlusCourantsEntreeMenu() {
        super(LIBELLE, TypeEntreeMenu.ACTION);
    }

    /**
     * Exécute l'action associée à l'entrée du menu, qui consiste à afficher les ingrédients les plus courants dans la limite saisie par l'utilisateur.
     */
    @Override
    public void action() {
        Scanner scanner = ScannerProvider.getScanner();

        System.out.println("Combien d'ingrédients souhaitez-vous afficher ?");
        int limit = scanner.nextInt();
        scanner.nextLine();

        IngredientService ingredientService = IngredientService.getInstance();
        List<Ingredient> ingredients = ingredientService.getIngredientsLesPlusCourants(limit);

        if (null != ingredients) {
            System.out.println("Les " + limit + " ingrédients les plus courants :");
            for (Ingredient ingredient : ingredients) {
                System.out.println(ingredient);
            }
        }
    }
}
