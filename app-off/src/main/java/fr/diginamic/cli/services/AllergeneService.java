package fr.diginamic.cli.services;
import fr.diginamic.dal.DAOFactory;
import fr.diginamic.dal.IAllergeneDAO;
import fr.diginamic.entites.Allergene;
import java.util.List;

/**
 * Le service AllergeneService permet d'accéder aux fonctionnalités liées aux allergènes.
 */
public class AllergeneService {
    private static AllergeneService instance;

    /**
     * Récupère l'instance unique de AllergeneService.
     *
     * @return l'instance de AllergeneService
     */
    public static AllergeneService getInstance() {
        if (null == instance) {
            instance = new AllergeneService();
        }
        return instance;
    }

    private final IAllergeneDAO allergeneDao;

    {
        allergeneDao = DAOFactory.getAllergeneDAO();
    }

    private AllergeneService() {}

    /**
     * Obtient les allergènes les plus courants.
     *
     * @param limit limite du nombre d'allergènes à retourner
     * @return une liste d'objets Allergene représentant les allergènes les plus courants
     */
    public List<Allergene> getAllergenesLesPlusCourants(int limit) {
        return allergeneDao.findAllAllergenesCountProduitGroupByProduit(limit);
    }
}
