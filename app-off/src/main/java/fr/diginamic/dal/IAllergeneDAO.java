package fr.diginamic.dal;
import fr.diginamic.entites.Allergene;
import java.util.List;

public interface IAllergeneDAO {
    /**
     * @return
     */
    List<Allergene> findAllAllergene();

    /**
     * @param id
     * @return
     */
    Allergene findAllergeneById(int id);

    /**
     * @param allerge
     */
    void createAllergene(Allergene allerge);

    /**
     * @param allerge
     * @return
     */
    int updateAllergene(Allergene allerge);

    /**
     * @param allerge
     * @return
     */
    boolean deleteAllergene(Allergene allerge);

    List<Allergene> findAllAllergenesCountProduitGroupByProduit(int limit);
}
