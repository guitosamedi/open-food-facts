package fr.diginamic.dao;

import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

import java.util.List;

public class ProduitDaoFactory {
    private static ProduitDao produitDao;

    public static ProduitDao getProduitDao() {
        if (null == produitDao) {
            produitDao = new ProduitDao() {
                @Override
                public List<Produit> findAllProduitByMarqueOrderByScore(Marque marque, int limit) {
                    System.out.println("Not implemented yet !");
                    return null;
                }

                @Override
                public List<Produit> findAllProduitByCategorieOrderByScore(Categorie categorie, int limit) {
                    System.out.println("Not implemented yet !");
                    return null;
                }

                @Override
                public List<Produit> findAllProduitByMarqueAndCategorieOrderByScore(Marque marque, Categorie categorie, int limit) {
                    System.out.println("Not implemented yet !");
                    return null;
                }
            };
        }
        return produitDao;
    }

    private ProduitDaoFactory() {}
}
