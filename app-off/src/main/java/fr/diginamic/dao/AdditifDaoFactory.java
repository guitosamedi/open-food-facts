package fr.diginamic.dao;

import fr.diginamic.entites.Additif;

import java.util.List;

public class AdditifDaoFactory {
    private static AdditifDao additifDao;


    public static AdditifDao getAdditifDao() {
        if (null == additifDao) {
            additifDao = new AdditifDao() {
                @Override
                public List<Additif> findAllAdditifsOrderByCountProduit(int limit) {
                    System.out.println("Not implemented yet !");
                    return null;
                }
            };
        }
        return additifDao;
    }

    private AdditifDaoFactory() {}
}
