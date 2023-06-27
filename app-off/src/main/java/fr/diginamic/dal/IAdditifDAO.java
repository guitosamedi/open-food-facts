package fr.diginamic.dal;
import fr.diginamic.entites.Additif;
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
     * @param id
     * @return
     */
    int updateAdditif(Additif additif, int id);

    /**
     * @param additif
     * @param id
     * @return
     */
    boolean deleteAdditif(Additif additif, int id);
}
