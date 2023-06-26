package fr.diginamic.cli.services;

import fr.diginamic.dao.*;
import fr.diginamic.entites.Categorie;
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


    public List<Produit> getMeilleursProduitsParMarque(String nomMarque, int limit) {
        MarqueDao marqueDao = MarqueDaoFactory.getMarqueDao();
        Marque marque = marqueDao.findByNom(nomMarque);

        return produitDao.findAllProduitByMarqueOrderByScore(marque, limit);
    }

    public List<Produit> getMeilleursProduitsParCategorie(String nomCategorie, int limit) {
        CategorieDao categorieDao = CategorieDaoFactory.getCategorieDao();
        Categorie categorie = categorieDao.findByNom(nomCategorie);

        return produitDao.findAllProduitByCategorieOrderByScore(categorie, limit);
    }
}
