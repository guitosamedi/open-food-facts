package fr.diginamic.dao;

import fr.diginamic.entites.Ingredient;

import java.util.List;

public interface IngredientDao {
    List<Ingredient> findAllIngredientsOrderByCountProduit(int limit);
}
