package fr.diginamic.dao;

import fr.diginamic.entites.Allergene;

import java.util.List;

public class AllergenesDaoFactory {
    private static AllergeneDao allergeneDao;


    public static AllergeneDao getAllergeneDao() {
        if (null == allergeneDao) {
            allergeneDao = new AllergeneDao() {
                @Override
                public List<Allergene> findAllAllergenesCountProduitGroupByProduit(int limit) {
                    System.out.println("Not implemented yet !");
                    return null;
                }
            };
        }
        return allergeneDao;
    }
}
