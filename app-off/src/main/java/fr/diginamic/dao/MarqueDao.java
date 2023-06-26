package fr.diginamic.dao;

import fr.diginamic.entites.Marque;

public interface MarqueDao {
    Marque findByName(String marqueName);
}
