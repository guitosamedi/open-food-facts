package fr.diginamic.dal;
import fr.diginamic.entites.Additif;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface IAdditifDAO {
    /**
     * @return
     */
    List<Additif> findAllAdditif();

    /**
     * @param id
     * @return
     */
    Additif findAdditifById(int id);

    /**
     * @param additif
     */
    void createAdditif(Additif additif);

    /**
     * @param additif
     * @return
     */
    int updateAdditif(Additif additif);

    /**
     * @param additif
     * @return
     */
    boolean deleteAdditif(Additif additif);

    Additif findByNom(String nom);

    Additif findByNom(String nom, EntityManager em);

    List<Additif> findAllAdditifsCountProduitGroupByProduit(int limit);
}
