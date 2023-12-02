package fr.diginamic.dal.jpa;
import fr.diginamic.dal.IAllergeneDAO;
import fr.diginamic.entites.Allergene;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class AllergeneDAO implements IAllergeneDAO {

    /**
     *
     */
    private static final String GET_ALL_REQ = "SELECT al FROM Allergene al";
    private static final String FIND_BY_NOM_REQ = "SELECT a FROM Allergene a WHERE a.nom = :nom";

    private static final String FIND_BY_COMMON_ALLERGENE_REQ = "SELECT al FROM Allergene al JOIN al.produits p GROUP BY al ORDER BY COUNT(p) DESC";
    public AllergeneDAO() {
    }

    /**
     * @return
     */
    @Override
    public List<Allergene> findAllAllergene() {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Allergene> al = em.createQuery(GET_ALL_REQ, Allergene.class);
            return al.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des allergènes", e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Allergene findAllergeneById(int id) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            return em.find(Allergene.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de l'allergène'", e);
        }
    }

    /**
     * @param allerge
     */
    @Override
    public void createAllergene(Allergene allerge) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(allerge);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de l'allergène'", e);
        }
    }

    /**
     * @param allerge
     * @return
     */
    @Override
    public int updateAllergene(Allergene allerge) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Allergene al = em.find(Allergene.class, allerge.getId());
            if (al != null) {
                em.merge(al);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la modification de l'allergène'", e);
        }
        return 0;
    }

    /**
     * @param allerge
     * @return
     */
    @Override
    public boolean deleteAllergene(Allergene allerge) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Allergene al = em.find(Allergene.class, allerge.getId());
            if (al != null) {
                em.remove(al);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de l'allergène'", e);
        }
        return false;
    }

    @Override
    public Allergene findByNom(String nom) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Allergene> al = em.createQuery(FIND_BY_NOM_REQ, Allergene.class);
            al.setParameter("nom", nom);
            return al.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des allergènes", e);
        }
    }

    @Override
    public Allergene findByNom(String nom, EntityManager em) {
        try {
            TypedQuery<Allergene> al = em.createQuery(FIND_BY_NOM_REQ, Allergene.class);
            al.setParameter("nom", nom);
            return al.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des allergènes", e);
        }
    }

    @Override
    public List<Allergene> findAllAllergenesCountProduitGroupByProduit(int limit) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Allergene> al = em.createQuery(FIND_BY_COMMON_ALLERGENE_REQ, Allergene.class);
            return al.setMaxResults(limit).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des allergenes courants", e);
        }
    }
}
