package fr.diginamic.dal.jpa;

import fr.diginamic.dal.IProduitDAO;
import fr.diginamic.entites.Produit;
import jakarta.persistence.*;

import java.util.List;


public class ProduitDAO implements IProduitDAO {
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction transaction;

    private static final String GET_ALL_REQ = "SELECT p FROM Produit p";
    //private static final String GET_BY_ID_REQ = "SELECT p FROM produit p WHERE p.id =:id";
   // private static final String CREATE_REQ = "INSERT INTO produit (nom) VALUES (?)";
    //private static final String UPDATE_REQ = "UPDATE produit SET (nom) WHERE id =:id ";
   // private static final String DELETE_REQ = "DELETE FROM produit WHERE id =:id";

    public ProduitDAO() {
        try {
            emf = Persistence.createEntityManagerFactory("app-off"); // Nom de l'unité de persistance définie dans le fichier persistence.xml
            em = emf.createEntityManager();
            transaction = em.getTransaction();

        } catch (Exception e) {
            // Gestion de l'exception dans la création de l'EntityManagerFactory
            System.out.println("Erreur de Persistence ! " + e.getMessage());
        }/* ou mettre le emf.close() ???
        finally {
           // emf.close();
        }*/
    }
    @Override
    public List<Produit> findAll() {
        try {
            TypedQuery<Produit> q = em.createQuery(GET_ALL_REQ, Produit.class);
            return q.getResultList();
        } catch (Exception e) {
            // Gestion des exceptions
            // trouvé sur le net la méthode e.printStackTrace(); ???
            System.out.println("Erreur lors de l'affichage de la liste des produits : " + e.getMessage());
            return null;
        }finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public Produit findById(int id) {
        try {
            return em.find(Produit.class, id);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage d'un produit : " + e.getMessage());
        }finally {
            em.close();
            emf.close();
        }
        return null;
    }

    @Override
    public void create(Produit produit) {
        try {
            transaction.begin();
            em.persist(produit);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Erreur lors de la création d'un produit : " + e.getMessage());
        }finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public int update(Produit produit, int id) { // doit-on mettre int id dans les paramètres de la méthode ?
        try {
            transaction.begin();
            Produit p = em.find(Produit.class, produit.getId());
            if (p != null) {
                em.merge(p);
            }else{
                System.out.println("Produit non trouvé");
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Erreur lors de la modification d'un produit : " + e.getMessage());
        }finally {
            em.close();
            emf.close();
        }
        return 0;
    }

    @Override
    public boolean delete(Produit produit, int id) {
        try {
            transaction.begin();
            Produit p = em.find(Produit.class, produit.getId());
            if (p != null) {
                em.merge(p);
                em.remove(p);
            }else{
                System.out.println("Produit non trouvé");
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Erreur lors de la suppression d'un produit : " + e.getMessage());
        }finally {
            em.close();
            emf.close();
        }
        return false;
    }


}
