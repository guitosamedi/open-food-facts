package fr.diginamic.dal;
import fr.diginamic.entites.Ingredient;
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
     * @param id
     * @return
     */
    int updateIngredient(Ingredient ingredient, int id);

    /**
     * @param ingredient
     * @param id
     * @return
     */
    boolean deleteIngredient(Ingredient ingredient, int id);
}
