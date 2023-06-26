package fr.diginamic.dao;

import fr.diginamic.entites.Marque;

public interface MarqueDao {
    Marque findByNom(String nom);
}
