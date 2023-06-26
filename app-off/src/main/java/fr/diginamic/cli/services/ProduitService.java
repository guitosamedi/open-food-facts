package fr.diginamic.cli.services;

import fr.diginamic.dao.MarqueDao;
import fr.diginamic.dao.MarqueDaoFactory;
import fr.diginamic.dao.ProduitDao;
import fr.diginamic.dao.ProduitDaoFacotry;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

import java.util.List;

public class ProduitService {
    private static ProduitService instance;

    public static ProduitService getInstance() {
        if (null == instance) {
            instance = new ProduitService();
        }
        return instance;
    }

    private final ProduitDao produitDao;

    {
        produitDao = ProduitDaoFacotry.getProduitDao();
    }

    private ProduitService() {}


    public List<Produit> getMeilleursProduitsParMarque(String marqueName, int limit) {
        MarqueDao marqueDao = MarqueDaoFactory.getMarqueDao();
        Marque marque = marqueDao.findByName(marqueName);

        return produitDao.findAllProduitByMarqueOrderByScore(marque, limit);
    }
}
