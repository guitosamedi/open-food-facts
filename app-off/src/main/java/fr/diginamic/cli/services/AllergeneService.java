package fr.diginamic.cli.services;

import fr.diginamic.dao.AllergeneDao;
import fr.diginamic.dao.AllergenesDaoFactory;
import fr.diginamic.entites.Allergene;

import java.util.List;

public class AllergeneService {
    private static AllergeneService instance;

    public static AllergeneService getInstance() {
        if (null == instance) {
            instance = new AllergeneService();
        }
        return instance;
    }

    private final AllergeneDao allergeneDao;

    {
        allergeneDao = AllergenesDaoFactory.getAllergeneDao();
    }

    private AllergeneService() {}


    public List<Allergene> getAllergenesLesPlusCourants(int limit) {
        return allergeneDao.findAllAllergenesOrderByCountProduit(limit);
    }
}
