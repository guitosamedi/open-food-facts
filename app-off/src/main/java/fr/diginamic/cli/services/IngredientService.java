package fr.diginamic.cli.services;

import fr.diginamic.dao.IngredientDao;
import fr.diginamic.dao.IngredientDaoFactory;
import fr.diginamic.entites.Ingredient;

import java.util.List;

/**
 * Le service IngredientService permet d'accéder aux fonctionnalités liées aux ingrédients.
 */
public class IngredientService {
    private static IngredientService instance;

    /**
     * Récupère l'instance unique de IngredientService.
     *
     * @return l'instance de IngredientService
     */
    public static IngredientService getInstance() {
        if (null == instance) {
            instance = new IngredientService();
        }
        return instance;
    }

    private final IngredientDao ingredientDao;

    {
        ingredientDao = IngredientDaoFactory.getIngredientDao();
    }

    private IngredientService() {}

    /**
     * Obtient les ingrédients les plus courants.
     *
     * @param limit limite du nombre d'ingrédients à retourner
     * @return une liste d'objets Ingredient représentant les ingrédients les plus courants
     */
    public List<Ingredient> getIngredientsLesPlusCourants(int limit) {
        return ingredientDao.findAllIngredientsOrderByCountProduit(limit);
    }
}
