package fr.diginamic.cli.services;

import fr.diginamic.dao.AdditifDao;
import fr.diginamic.dao.AdditifDaoFactory;
import fr.diginamic.entites.Additif;

import java.util.List;

/**
 * Le service AdditifService permet d'accéder aux fonctionnalités liées aux additifs.
 */
public class AdditifService {
    private static AdditifService instance;

    /**
     * Récupère l'instance unique de AdditifService.
     *
     * @return l'instance de AdditifService
     */
    public static AdditifService getInstance() {
        if (null == instance) {
            instance = new AdditifService();
        }
        return instance;
    }

    private final AdditifDao additifDao;

    {
        additifDao = AdditifDaoFactory.getAdditifDao();
    }

    private AdditifService() {}

    /**
     * Obtient les additifs les plus courants.
     *
     * @param limit limite du nombre d'additifs à retourner
     * @return une liste d'objets Additif représentant les additifs les plus courants
     */
    public List<Additif> getAdditifsLesPlusCourants(int limit) {
        return additifDao.findAllAdditifsOrderByCountProduit(limit);
    }
}
