package fr.diginamic.dao;

import fr.diginamic.entites.Categorie;

public interface CategorieDao {
    Categorie findByNom(String nom);
}
