package fr.diginamic.dal.jpa;
import fr.diginamic.dal.IIngredientDAO;
import fr.diginamic.entites.Ingredient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class IngredientDAO implements IIngredientDAO {

    private static final String GET_ALL_REQ = "SELECT i FROM Ingredient i";
    private static final String FIND_BY_NOM_REQ = "SELECT i FROM Ingredient i WHERE i.nom = :nom";
    private static final String FIND_BY_COMMON_INGREDIENT_REQ = "SELECT i FROM Ingredient i JOIN i.produits p GROUP BY i ORDER BY COUNT(p)";
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
     * @return
     */
    @Override
    public int updateIngredient(Ingredient ingredient) {
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
     * @return
     */
    @Override
    public boolean deleteIngredient(Ingredient ingredient) {
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

    @Override
    public Ingredient findByNom(String nomIngredient){
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Ingredient> i = em.createQuery(FIND_BY_NOM_REQ, Ingredient.class);
           i.setParameter("nom", nomIngredient);
            return i.getSingleResult();
        }catch(Exception e){
            throw new RuntimeException("Erreur lors de la récupération des ingrédients", e);
        }
    }

    @Override
    public List<Ingredient> findAllIngredientsCountProduitGroupByProduit(int limit) {
        EntityManagerFactory emf = EMFProvider.getEmf();
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Ingredient> i = em.createQuery(FIND_BY_COMMON_INGREDIENT_REQ, Ingredient.class);
            return i.setMaxResults(limit).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des ingrédients courants", e);
        }
    }
}
