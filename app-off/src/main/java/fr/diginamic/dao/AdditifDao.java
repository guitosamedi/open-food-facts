package fr.diginamic.dao;

import fr.diginamic.entites.Additif;

import java.util.List;

public interface AdditifDao {
    List<Additif> findAllAdditifsOrderByCountProduit(int limit);
}
