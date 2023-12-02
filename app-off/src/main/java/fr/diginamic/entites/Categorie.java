package fr.diginamic.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categorie")
@Cacheable
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    @OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER)
    private Set<Produit> produits = new HashSet<>();

    public Categorie() {}

    public Categorie(String nom) {
        this.nom = nom;
    }

    /**
     * @param id
     * @param nom
     */
    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return
     */
    public Set<Produit> getProduits() {
        return produits;
    }

    /**
     * @param produits
     */
    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    /**
     * @param produit
     */
    public void addProduit(Produit produit){
        produit.setCategorie(this);
    }


}
