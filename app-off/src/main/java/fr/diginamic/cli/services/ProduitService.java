package fr.diginamic.cli.services;

import fr.diginamic.dal.DAOFactory;
import fr.diginamic.dal.ICategorieDAO;
import fr.diginamic.dal.IMarqueDAO;
import fr.diginamic.dal.IProduitDAO;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

import java.util.List;

/**
 * Le service ProduitService permet d'accéder aux fonctionnalités liées aux produits.
 */
public class ProduitService {
    private static ProduitService instance;

    /**
     * Récupère l'instance unique de ProduitService.
     *
     * @return l'instance de ProduitService
     */
    public static ProduitService getInstance() {
        if (null == instance) {
            instance = new ProduitService();
        }
        return instance;
    }

    private final IProduitDAO produitDao;

    {
        produitDao = DAOFactory.getProduitDAO();
    }

    private ProduitService() {}

    /**
     * Obtient les meilleurs produits d'une marque.
     *
     * @param nomMarque nom de la marque
     * @param limit limite du nombre de produits à retourner
     * @return une liste d'objets Produit représentant les meilleurs produits de la marque
     */
    public List<Produit> getMeilleursProduitsParMarque(String nomMarque, int limit) {
        IMarqueDAO marqueDao = DAOFactory.getMarqueDAO();
        Marque marque = marqueDao.findByNom(nomMarque);

        return produitDao.findAllProduitByMarqueOrderByScore(marque, limit);
    }

    /**
     * Obtient les meilleurs produits d'une catégorie.
     *
     * @param nomCategorie nom de la catégorie
     * @param limit limite du nombre de produits à retourner
     * @return une liste d'objets Produit représentant les meilleurs produits de la catégorie
     */
    public List<Produit> getMeilleursProduitsParCategorie(String nomCategorie, int limit) {
        ICategorieDAO categorieDao = DAOFactory.getCategorieDAO();
        Categorie categorie = categorieDao.findByNom(nomCategorie);

        return produitDao.findAllProduitByCategorieOrderByScore(categorie, limit);
    }

    /**
     * Obtient les meilleurs produits d'une marque et d'une catégorie.
     *
     * @param nomMarque nom de la marque
     * @param nomCategorie nom de la catégorie
     * @param limit limite du nombre de produits à retourner
     * @return une liste d'objets Produit représentant les meilleurs produits de la marque et de la catégorie
     */
    public List<Produit> getMeilleursProduitsParMarqueEtParCategorie(String nomMarque, String nomCategorie, int limit) {
        IMarqueDAO marqueDao = DAOFactory.getMarqueDAO();
        Marque marque = marqueDao.findByNom(nomMarque);

        ICategorieDAO categorieDao = DAOFactory.getCategorieDAO();
        Categorie categorie = categorieDao.findByNom(nomCategorie);

        return produitDao.findAllProduitByMarqueAndCategorieOrderByScore(marque, categorie, limit);
    }
}
