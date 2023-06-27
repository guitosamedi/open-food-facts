package fr.diginamic.dal;
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
     * @param id
     * @return
     */
    int updateProduit(Produit produit, int id);

    /**
     * @param produit
     * @param id
     * @return
     */
    boolean deleteProduit(Produit produit, int id);
}
