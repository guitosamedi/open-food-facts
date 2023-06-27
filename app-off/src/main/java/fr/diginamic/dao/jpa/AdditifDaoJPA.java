package fr.diginamic.dao.jpa;

import fr.diginamic.dao.AdditifDao;
import fr.diginamic.entites.Additif;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class AdditifDaoJPA implements AdditifDao {
    EntityManagerFactory emf;

    @Override
    public List<Additif> findAllAdditifsCountProduitGroupByProduit(int limit) {
        return null;
    }

    public Additif findByReference(int id) {
        EntityManager em = emf.createEntityManager();
        return em.getReference(Additif.class, id);
    }
}
