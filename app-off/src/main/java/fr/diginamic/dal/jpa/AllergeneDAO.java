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
}
