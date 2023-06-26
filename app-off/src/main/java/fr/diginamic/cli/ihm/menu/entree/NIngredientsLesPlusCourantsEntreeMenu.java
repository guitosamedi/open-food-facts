package fr.diginamic.cli.ihm.menu.entree;

import fr.diginamic.cli.ScannerProvider;
import fr.diginamic.cli.services.IngredientService;
import fr.diginamic.entites.Ingredient;

import java.util.List;
import java.util.Scanner;

public class NIngredientsLesPlusCourantsEntreeMenu extends EntreeMenu {
    private static final String LIBELLE = "Les ingrédients les plus courants";

    public NIngredientsLesPlusCourantsEntreeMenu() {
        super(LIBELLE, TypeEntreeMenu.ACTION);
    }

    @Override
    public void action() {
        Scanner scanner = ScannerProvider.getScanner();

        System.out.println("Combien d'ingrédients souhaitez-vous afficher ?");
        int limit = scanner.nextInt();

        IngredientService ingredientService = IngredientService.getInstance();
        List<Ingredient> ingredients = ingredientService.getIngredientsLesPlusCourants(limit);

        if (null != ingredients) {
            for (Ingredient ingredient : ingredients) {
                System.out.println("Les " + limit + " ingrédients les plus courants :");
                System.out.println(ingredient);
            }
        }
    }
}
