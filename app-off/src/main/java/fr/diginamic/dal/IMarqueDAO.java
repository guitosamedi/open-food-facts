package fr.diginamic.dal;
import fr.diginamic.entites.Marque;
import java.util.List;

public interface IMarqueDAO {
    /**
     * @return
     */
    List<Marque> findAllMarque();

    /**
     * @param id
     * @return
     */
    Marque findMarqueById(int id);

    /**
     * @param marque
     */
    void createMarque(Marque marque);

    /**
     * @param marque
     * @return
     */
    int updateMarque(Marque marque);

    /**
     * @param marque
     * @return
     */
    boolean deleteMarque(Marque marque);

    Marque findByNom(String nomMarque);
}
