package fr.diginamic.dal.jpa;
import fr.diginamic.dal.IAdditifDAO;
import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Ingredient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AdditifDAO implements IAdditifDAO {
    private static final String GET_ALL_REQ = "SELECT ad FROM Additif ad";
    private static final String FIND_BY_NOM = "SELECT a FROM Additif a WHERE a.nom = :nom";

    private static final String FIND_BY_COMMON_ADDITIF_REQ = "SELECT ad FROM Additif ad JOIN ad.produits p GROUP BY ad ORDER BY COUNT(p) DESC";
    public AdditifDAO() {
    }

    /**
     * @return
     */
    @Override
    public List<Additif> findAllAdditif() {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Additif> ad = em.createQuery(GET_ALL_REQ, Additif.class);
            return ad.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des additifs", e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Additif findAdditifById(int id) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            return em.find(Additif.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de l'additif", e);
        }
    }

    /**
     * @param additif
     */
    @Override
    public void createAdditif(Additif additif) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(additif);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de l'additif'", e);
        }
    }

    /**
     * @param additif
     * @return
     */
    @Override
    public int updateAdditif(Additif additif) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Additif ad = em.find(Additif.class, additif.getId());
            if (ad != null) {
                em.merge(ad);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la modification de l'additif'", e);
        }
        return 0;
    }

    /**
     * @param additif
     * @return
     */
    @Override
    public boolean deleteAdditif(Additif additif) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Additif ad = em.find(Additif.class, additif.getId());
            if (ad != null) {
                em.remove(ad);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de l'additif'", e);
        }
        return false;
    }

    @Override
    public List<Additif> findAllAdditifsCountProduitGroupByProduit(int limit) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Additif> ad = em.createQuery(FIND_BY_COMMON_ADDITIF_REQ, Additif.class);
            return ad.setMaxResults(limit).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des additifs courants", e);
        }
    }

    @Override
    public Additif findByNom(String nom) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Additif> ad = em.createQuery(FIND_BY_NOM, Additif.class);
            ad.setParameter("nom", nom);
            return ad.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des additifs", e);
        }
    }

    @Override
    public Additif findByNom(String nom, EntityManager em) {
        try {
            TypedQuery<Additif> ad = em.createQuery(FIND_BY_NOM, Additif.class);
            ad.setParameter("nom", nom);
            return ad.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des additifs", e);
        }
    }
}
