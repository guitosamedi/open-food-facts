package fr.diginamic.dal;

import java.util.ResourceBundle;

public final class DAOFactory {
    private static final String MODE_JPA = "JPA";
    private static final String MODE_JDBC = "JDBC";
    private static final String MODE_XML = "XML";
    private static final String MODE_COURANT;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("app");
        MODE_COURANT = bundle.getString("mode.stockage");
    }

    private DAOFactory() {}

    /**
     * @return
     */
    public static IProduitDAO getProduitDAO() {
        IProduitDAO dao =null;
        switch (MODE_COURANT) {
            case MODE_JPA -> dao = new fr.diginamic.dal.jpa.ProduitDAO();
           // case MODE_JDBC -> dao = new fr.diginamic.dal.jdbc.ProduitDAO();
           // case MODE_XML -> dao = new fr.diginamic.dal.xml.ProduitDAO();
            default -> throw new RuntimeException("Mode non implémenté !!!");
        }
        return dao;
    }

    /**
     * @return
     */
    public static IMarqueDAO getMarqueDAO() {
        IMarqueDAO dao =null;
        switch (MODE_COURANT) {
            case MODE_JPA -> dao = new fr.diginamic.dal.jpa.MarqueDAO();
            // case MODE_JDBC -> dao = new fr.diginamic.dal.jdbc.MarqueDAO();
            // case MODE_XML -> dao = new fr.diginamic.dal.xml.MarqueDAO();
            default -> throw new RuntimeException("Mode non implémenté !!!");
        }
        return dao;
    }

    public static ICategorieDAO getCategorieDAO() {
        ICategorieDAO dao =null;
        switch (MODE_COURANT) {
            case MODE_JPA -> dao = new fr.diginamic.dal.jpa.CategorieDAO();
            // case MODE_JDBC -> dao = new fr.diginamic.dal.jdbc.ProduitDAO();
            // case MODE_XML -> dao = new fr.diginamic.dal.xml.ProduitDAO();
            default -> throw new RuntimeException("Mode non implémenté !!!");
        }
        return dao;
    }

    public static IAdditifDAO getAdditifDAO() {
        IAdditifDAO dao =null;
        switch (MODE_COURANT) {
            case MODE_JPA -> dao = new fr.diginamic.dal.jpa.AdditifDAO();
            // case MODE_JDBC -> dao = new fr.diginamic.dal.jdbc.ProduitDAO();
            // case MODE_XML -> dao = new fr.diginamic.dal.xml.ProduitDAO();
            default -> throw new RuntimeException("Mode non implémenté !!!");
        }
        return dao;
    }

    public static IAllergeneDAO getAllergeneDAO() {
        IAllergeneDAO dao =null;
        switch (MODE_COURANT) {
            case MODE_JPA -> dao = new fr.diginamic.dal.jpa.AllergeneDAO();
            // case MODE_JDBC -> dao = new fr.diginamic.dal.jdbc.ProduitDAO();
            // case MODE_XML -> dao = new fr.diginamic.dal.xml.ProduitDAO();
            default -> throw new RuntimeException("Mode non implémenté !!!");
        }
        return dao;
    }

    public static IIngredientDAO getIngredientDAO() {
        IIngredientDAO dao =null;
        switch (MODE_COURANT) {
            case MODE_JPA -> dao = new fr.diginamic.dal.jpa.IngredientDAO();
            // case MODE_JDBC -> dao = new fr.diginamic.dal.jdbc.ProduitDAO();
            // case MODE_XML -> dao = new fr.diginamic.dal.xml.ProduitDAO();
            default -> throw new RuntimeException("Mode non implémenté !!!");
        }
        return dao;
    }
}
