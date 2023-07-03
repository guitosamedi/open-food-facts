package fr.diginamic.dal.jpa;
import fr.diginamic.dal.ICategorieDAO;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Marque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CategorieDAO implements ICategorieDAO {
    private static final String GET_ALL_REQ = "SELECT c FROM Categorie c";
    private static final String FIND_BY_NOM_REQ = "SELECT c FROM Categorie c WHERE c.nom = :nom";

    /**
     * @return
     */
    @Override
    public List<Categorie> findAllCategorie() {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Categorie> c = em.createQuery(GET_ALL_REQ, Categorie.class);
            return c.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des catégories", e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Categorie findCategorieById(int id) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            return em.find(Categorie.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la catégorie", e);
        }
    }

    /**
     * @param categorie
     */
    @Override
    public void createCategorie(Categorie categorie) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(categorie);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de la catégorie", e);
        }
    }

    /**
     * @param categorie
     * @param id
     * @return
     */
    @Override
    public int updateCategorie(Categorie categorie) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Categorie c = em.find(Categorie.class, categorie.getId());
            if (c != null) {
                em.merge(c);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la modification de la catégorie", e);
        }
        return 0;
    }

    /**
     * @param categorie
     * @return
     */
    @Override
    public boolean deleteCategorie(Categorie categorie) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Categorie c = em.find(Categorie.class, categorie.getId());
            if (c != null) {
                em.remove(c);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de la marque", e);
        }
        return false;
    }

    @Override
    public Categorie findByNom(String nomCategorie) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Categorie> c = em.createQuery(FIND_BY_NOM_REQ, Categorie.class);
            c.setParameter("nom", nomCategorie);
            return c.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des marques", e);
        }
    }
}
