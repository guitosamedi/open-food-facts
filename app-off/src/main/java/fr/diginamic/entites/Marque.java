package fr.diginamic.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "marque")
@Cacheable
public class Marque {
    @Id
    @GeneratedValue
    private int id;

    @Column(length=100)
    private String nom;

    @OneToMany(mappedBy = "marque", fetch = FetchType.EAGER)
    private Set<Produit> produits = new HashSet<>();

    public Marque() {}

    public Marque(String nom) {
        this.nom = nom;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marque marque = (Marque) o;
        return Objects.equals(nom, marque.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
