package fr.diginamic.dal.jpa;

import fr.diginamic.dal.IIngredientDAO;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Marque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class IngredientDAO implements IIngredientDAO {

    private static final String GET_ALL_REQ = "SELECT i FROM Ingredient i";

    public IngredientDAO() {
    }

    /**
     * @return
     */
    @Override
    public List<Ingredient> findAllIngredient() {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Ingredient> i = em.createQuery(GET_ALL_REQ, Ingredient.class);
            return i.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des ingrédients", e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Ingredient findIngredientById(int id) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            return em.find(Ingredient.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de l'ingrédient'", e);
        }
    }

    /**
     * @param ingredient
     */
    @Override
    public void createIngredient(Ingredient ingredient) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(ingredient);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de l'ingrédient'", e);
        }
    }

    /**
     * @param ingredient
     * @param id
     * @return
     */
    @Override
    public int updateIngredient(Ingredient ingredient, int id) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Ingredient i = em.find(Ingredient.class, ingredient.getId());
            if (i != null) {
                em.merge(i);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la modification de l'ingrédient'", e);
        }
        return 0;
    }

    /**
     * @param ingredient
     * @param id
     * @return
     */
    @Override
    public boolean deleteIngredient(Ingredient ingredient, int id) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Ingredient i = em.find(Ingredient.class, ingredient.getId());
            if (i != null) {
                em.remove(i);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de l'ingrédient'", e);
        }
        return false;
    }
}
