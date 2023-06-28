package fr.diginamic.dal.jpa;
import fr.diginamic.dal.IMarqueDAO;
import fr.diginamic.entites.Marque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;


public class MarqueDAO implements IMarqueDAO {
    private static final String GET_ALL_REQ = "SELECT m FROM Marque m";
    private static final String FIND_BY_NOM_REQ = "SELECT m FROM Marque m WHERE m.nom = :nom";

    public MarqueDAO() {
    }

    /**
     * @return
     */
    @Override
    public List<Marque> findAllMarque() {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Marque> m = em.createQuery(GET_ALL_REQ, Marque.class);
            return m.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des marques", e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Marque findMarqueById(int id) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            return em.find(Marque.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la marque", e);
        }
    }

    /**
     * @param marque
     */
    @Override
    public void createMarque(Marque marque) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(marque);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de la marque", e);
        }
    }

    /**
     * @param marque
     * @return
     */
    @Override
    public int updateMarque(Marque marque) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Marque m = em.find(Marque.class, marque.getId());
            if (m != null) {
                em.merge(m);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la modification de la marque", e);
        }
        return 0;
    }

    /**
     * @param marque
     * @return
     */
    @Override
    public boolean deleteMarque(Marque marque) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Marque m = em.find(Marque.class, marque.getId());
            if (m != null) {
                em.remove(m);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de la marque", e);
        }
        return false;
    }

    @Override
    public Marque findByNom(String nom) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Marque> m = em.createQuery(FIND_BY_NOM_REQ, Marque.class);
            m.setParameter("nom", nom);
            return m.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des marques", e);
        }
    }
}
