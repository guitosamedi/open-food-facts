package fr.diginamic.dao;

import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

import java.util.List;

public interface ProduitDao {
    List<Produit> findAllProduitByMarqueOrderByScore(Marque marque, int limit);
}
