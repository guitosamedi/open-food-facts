package fr.diginamic.dao;

import fr.diginamic.entites.Categorie;

public class CategorieDaoFactory {
    private static CategorieDao categorieDao;

    public static CategorieDao getCategorieDao() {
        if (null == categorieDao) {
            categorieDao = new CategorieDao() {
                @Override
                public Categorie findByNom(String nom) {
                    System.out.println("Not implemented yet !");
                    return null;
                }
            };
        }
        return categorieDao;
    }

    private CategorieDaoFactory() {}
}
