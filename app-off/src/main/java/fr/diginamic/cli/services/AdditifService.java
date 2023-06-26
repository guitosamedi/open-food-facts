package fr.diginamic.cli.services;

import fr.diginamic.dao.AdditifDao;
import fr.diginamic.dao.AdditifDaoFactory;
import fr.diginamic.entites.Additif;

import java.util.List;

public class AdditifService {
    private static AdditifService instance;

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

    public List<Additif> getAdditifsLesPlusCourants(int limit) {
        return additifDao.findAllAdditifsOrderByCountProduit(limit);
    }
}
