package fr.diginamic.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "marque")
public class Marque {
    @Id
    @GeneratedValue
    private int id;

    @Column(length=100)
    private String nom;

    @OneToMany(mappedBy = "marque")
    private Set<Produit> produits = new HashSet<>();

    public Marque() {}

    /**
     * @param id
     * @param nom
     */
    public Marque(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

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
        produit.setMarque(this);
    }
}
