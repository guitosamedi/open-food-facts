package fr.diginamic.dal;
import fr.diginamic.entites.Categorie;
import jakarta.persistence.EntityManager;

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
     * @return
     */
    int updateCategorie(Categorie categorie);

    /**
     * @param categorie
     * @return
     */
    boolean deleteCategorie(Categorie categorie);

    Categorie findByNom(String nom);

    Categorie findByNom(String nom, EntityManager em);
}
