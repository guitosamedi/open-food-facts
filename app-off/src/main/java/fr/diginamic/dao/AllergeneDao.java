package fr.diginamic.dao;

import fr.diginamic.entites.Allergene;

import java.util.List;

public interface AllergeneDao {
    List<Allergene> findAllAllergenesOrderByCountProduit(int limit);
}
