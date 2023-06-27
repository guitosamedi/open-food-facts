package fr.diginamic.dal;
import fr.diginamic.entites.Categorie;
import java.util.List;

public interface ICategorieDAO {
    /**
     * @return
     */
    List<Categorie> findAllCategorie();

    /**
     * @param id
     * @return
     */
    Categorie findCategorieById(int id);

    /**
     * @param categorie
     */
    void createCategorie(Categorie categorie);

    /**
     * @param categorie
     * @param id
     * @return
     */
    int updateCategorie(Categorie categorie, int id);

    /**
     * @param categorie
     * @param id
     * @return
     */
    boolean deleteCategorie(Categorie categorie);
}
