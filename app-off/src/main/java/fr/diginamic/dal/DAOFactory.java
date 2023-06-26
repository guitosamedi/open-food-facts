package fr.diginamic.dal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ResourceBundle;

public final class DAOFactory {
    private static final String MODE_JPA = "JPA";
    private static final String MODE_COURANT;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("app");
        MODE_COURANT = bundle.getString("mode.stockage");
    }

    private DAOFactory() {}

    public static IProduitDAO getProduitDAO() {
        IProduitDAO dao =null;
        switch (MODE_COURANT) {
            case MODE_JPA -> dao = new fr.diginamic.dal.jpa.ProduitDAO();
            default -> throw new RuntimeException("Mode non implémenté !!!");
        }
        return dao;
    }

}
