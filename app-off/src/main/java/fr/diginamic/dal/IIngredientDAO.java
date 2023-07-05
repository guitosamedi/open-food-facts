package fr.diginamic.dal;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Produit;

import java.util.List;

public interface IIngredientDAO {
    /**
     * @return
     */
    List<Ingredient> findAllIngredient();

    /**
     * @param id
     * @return
     */
    Ingredient findIngredientById(int id);

    /**
     * @param ingredient
     */
    void createIngredient(Ingredient ingredient);

    /**
     * @param ingredient
     * @return
     */
    int updateIngredient(Ingredient ingredient);

    /**
     * @param ingredient
     * @return
     */
    boolean deleteIngredient(Ingredient ingredient);

    Ingredient findByNom(String nomIngredient);

    List<Ingredient> findAllIngredientsCountProduitGroupByProduit(int limit);
}
