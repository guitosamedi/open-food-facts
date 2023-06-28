package fr.diginamic.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue
    private int id;

    @Column(length=100)
    private String nom;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Produit> produits = new HashSet<>();

    public Ingredient() {}

    /**
     * @param id
     * @param nom
     */
    public Ingredient(int id, String nom) {
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Ingredient{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
