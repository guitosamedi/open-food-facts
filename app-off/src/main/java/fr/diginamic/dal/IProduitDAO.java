package fr.diginamic.dal;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;
import java.util.List;

public interface IProduitDAO {

    /**
     * @return
     */
    List<Produit> findAllProduit();

    /**
     * @param id
     * @return
     */
    Produit findProduitById(int id);

    /**
     * @param produit
     */
    void createProduit(Produit produit);

    /**
     * @param produit
     * @return
     */
    int updateProduit(Produit produit);

    /**
     * @param produit
     * @return
     */
    boolean deleteProduit(Produit produit);

    List<Produit> findAllProduitByMarqueOrderByScore(Marque marque, int limit);

    List<Produit> findAllProduitByCategorieOrderByScore(Categorie categorie, int limit);

    List<Produit> findAllProduitByMarqueAndCategorieOrderByScore(Marque marque, Categorie categorie, int limit);

}
