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
     * @param id
     * @return
     */
    int updateMarque(Marque marque, int id);

    /**
     * @param marque
     * @param id
     * @return
     */
    boolean deleteMarque(Marque marque, int id);
}
