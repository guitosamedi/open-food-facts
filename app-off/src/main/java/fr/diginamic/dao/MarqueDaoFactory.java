package fr.diginamic.dao;

import fr.diginamic.entites.Marque;

public class MarqueDaoFactory {
    private static MarqueDao marqueDao;

    public static MarqueDao getMarqueDao() {
        if (null == marqueDao) {
            marqueDao = new MarqueDao() {
                @Override
                public Marque findByName(String marqueName) {
                    System.out.println("Not implemented yet !");
                    return null;
                }
            };
        }
        return marqueDao;
    }
}
