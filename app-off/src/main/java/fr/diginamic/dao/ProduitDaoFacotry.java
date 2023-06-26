package fr.diginamic.dao;

import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

import java.util.List;

public class ProduitDaoFacotry {
    private static ProduitDao produitDao;

    public static ProduitDao getProduitDao() {
        if (null == produitDao) {
            produitDao = new ProduitDao() {
                @Override
                public List<Produit> findAllProduitByMarqueOrderByScore(Marque marque, int limit) {
                    System.out.println("Not implemented yet !");
                    return null;
                }
            };
        }
        return produitDao;
    }
}
