package fr.diginamic.dal.jpa;
import fr.diginamic.dal.IProduitDAO;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;
import jakarta.persistence.*;
import java.util.List;


public class ProduitDAO implements IProduitDAO {

    private static final String GET_ALL_REQ = "SELECT p FROM Produit p";
    //private static final String GET_BY_ID_REQ = "SELECT p FROM produit p WHERE p.id =:id";
   // private static final String CREATE_REQ = "INSERT INTO produit (nom) VALUES (?)";
    //private static final String UPDATE_REQ = "UPDATE produit SET (nom) WHERE id =:id ";
   // private static final String DELETE_REQ = "DELETE FROM produit WHERE id =:id";
    private static final String GET_ALL_BY_MARQUE_ORDERBY_SCORE = "SELECT p FROM Produit p WHERE p.marque = :marque ORDER BY score";
    private static final String GET_ALL_BY_CATEGORIE_ORDERBY_SCORE = "SELECT p FROM Produit p WHERE p.categorie = :categorie ORDER BY score";

    public ProduitDAO() {}

    /**
     * @return
     */
    @Override
    public List<Produit> findAllProduit() {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Produit> p = em.createQuery(GET_ALL_REQ, Produit.class);
            return p.getResultList();
        } catch (Exception e) {
            // Gestion des exceptions
            // trouvé sur le net la méthode e.printStackTrace(); ???
            throw new RuntimeException("Erreur lors de la récupération des produits", e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Produit findProduitById(int id) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            return em.find(Produit.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du produit", e);
        }
    }

    /**
     * @param produit
     */
    @Override
    public void createProduit(Produit produit) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(produit);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création du produit", e);
        }
    }

    /**
     * @param produit
     * @return
     */
    @Override
    public int updateProduit(Produit produit) { // doit-on mettre int id dans les paramètres de la méthode ?
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Produit p = em.find(Produit.class, produit.getId());
            if (p != null) {
                em.merge(p);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la modification du produit", e);
        }
        return 0;
    }

    /**
     * @param produit
     * @return
     */
    @Override
    public boolean deleteProduit(Produit produit) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Produit p = em.find(Produit.class, produit.getId());
            if (p != null) {
                em.remove(p);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression du produit", e);
        }
        return false;
    }

    @Override
    public List<Produit> findAllProduitByMarqueOrderByScore(Marque marque, int limit) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Produit> p = em.createQuery(GET_ALL_BY_MARQUE_ORDERBY_SCORE, Produit.class);
            p.setParameter("marque", marque);
            return p.setMaxResults(limit).getResultList();
        } catch (Exception e) {
            // Gestion des exceptions
            // trouvé sur le net la méthode e.printStackTrace(); ???
            throw new RuntimeException("Erreur lors de la récupération des produits", e);
        }
    }

    @Override
    public List<Produit> findAllProduitByCategorieOrderByScore(Categorie categorie, int limit) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Produit> p = em.createQuery(GET_ALL_BY_CATEGORIE_ORDERBY_SCORE, Produit.class);
            p.setParameter("categorie", categorie);
            return p.setMaxResults(limit).getResultList();
        } catch (Exception e) {
            // Gestion des exceptions
            // trouvé sur le net la méthode e.printStackTrace(); ???
            throw new RuntimeException("Erreur lors de la récupération des produits", e);
        }
    }

    @Override
    public List<Produit> findAllProduitByMarqueAndCategorieOrderByScore(Marque marque, Categorie categorie, int limit) {
        return null;
    }

}
