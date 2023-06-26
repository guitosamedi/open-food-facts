package fr.diginamic.dal;

import fr.diginamic.entites.Produit;

import java.util.List;

public interface IProduitDAO {

    List<Produit> findAll();

    Produit findById(int id);

    void create(Produit produit);

    int update(Produit produit, int id);
    // pourquoi pas void update(Produit produit); ?

    boolean delete(Produit produit, int id);
}
