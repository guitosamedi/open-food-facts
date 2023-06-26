package fr.diginamic.dao;

import fr.diginamic.entites.Ingredient;

import java.util.List;

public class IngredientDaoFactory {
    private static IngredientDao ingredientDao;

    public static IngredientDao getIngredientDao() {
        if (null == ingredientDao) {
            ingredientDao = new IngredientDao() {
                @Override
                public List<Ingredient> findAllOrderByCountProduit(int limit) {
                    System.out.println("Not implemented yet !");
                    return null;
                }
            };
        }
        return ingredientDao;
    }
}
