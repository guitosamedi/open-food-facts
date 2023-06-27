package fr.diginamic.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue
    private int id;

    @Column(length=100)
    private String nom;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Produit> produits = new HashSet<>();

    public Ingredient() {}

    public Ingredient(String nom) {
        this.nom = nom;
    }

    public Ingredient(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
}
