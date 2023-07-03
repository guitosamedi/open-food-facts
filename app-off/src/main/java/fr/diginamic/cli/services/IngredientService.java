package fr.diginamic.cli.services;
import fr.diginamic.dal.DAOFactory;
import fr.diginamic.dal.IIngredientDAO;
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

    private final IIngredientDAO ingredientDao;

    {
        ingredientDao = DAOFactory.getIngredientDAO();
    }

    private IngredientService() {}

    /**
     * Obtient les ingrédients les plus courants.
     *
     * @param limit limite du nombre d'ingrédients à retourner
     * @return une liste d'objets Ingredient représentant les ingrédients les plus courants
     */
    public List<Ingredient> getIngredientsLesPlusCourants(int limit) {
        return ingredientDao.findAllIngredientsCountProduitGroupByProduit(limit);
    }
}
