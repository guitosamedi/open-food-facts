package fr.diginamic.cli.services;

import fr.diginamic.dao.IngredientDao;
import fr.diginamic.dao.IngredientDaoFactory;
import fr.diginamic.entites.Ingredient;

import java.util.List;

public class IngredientService {
    private static IngredientService instance;

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


    public List<Ingredient> getIngredientLesPlusCourants(int limit) {
        return ingredientDao.findAllOrderByCountProduit(limit);
    }
}
