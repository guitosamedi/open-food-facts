package fr.diginamic.dao;

import fr.diginamic.entites.Additif;

import java.util.List;
import java.util.ResourceBundle;

public class AdditifDaoFactory {
    private final static String DAO_MODE_JPA = "jpa";
    private final static  String DAO_MODE_XML = "xml";

    private static final String CURRENT_DAO_MODE;
    private static AdditifDao additifDao;

    static {
        ResourceBundle app = ResourceBundle.getBundle("app");
        CURRENT_DAO_MODE = app.getString("mode.dao");
    }


    public static AdditifDao getAdditifDao() {
        if (null == additifDao) {
            additifDao = new AdditifDao() {
                @Override
                public List<Additif> findAllAdditifsCountProduitGroupByProduit(int limit) {
                    System.out.println("Not implemented yet !");
                    return null;
                }
            };
        }
        return additifDao;
    }

    private AdditifDaoFactory() {}
}
