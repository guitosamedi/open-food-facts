package fr.diginamic.entites;
import jakarta.persistence.*;
import fr.diginamic.entites.Categorie;

import java.util.Set;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String nom;

    private int graisse;

    private int energie;

    @Enumerated(value= EnumType.STRING)
    private ProduitScore score;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id")
    private Categorie categorie;

    @ManyToMany(cascade = CascadeType.PERSIST)
   @JoinTable(name= "produit_ingredient",
           joinColumns = @JoinColumn(name="id_produit", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name="id_ingredient", referencedColumnName = "id")
    )
    private Set<Ingredient> ingredients;


    public Produit(){};

    public Produit(int id, String nom, int graisse, int energie, ProduitScore score) {
        this.id = id;
        this.nom = nom;
        this.graisse = graisse;
        this.energie = energie;
        this.score = score;
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

    public int getGraisse() {
        return graisse;
    }

    public void setGraisse(int graisse) {
        this.graisse = graisse;
    }

    public int getEnergie() {
        return energie;
    }

    public void setEnergie(int energie) {
        this.energie = energie;
    }

    public ProduitScore getScore() {
        return score;
    }

    public void setScore(ProduitScore score) {
        this.score = score;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        if(null != this.categorie){
            this.categorie.getProduits().remove(this);
        }
        categorie.getProduits().add(this);
        this.categorie = categorie;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
        ingredient.getProduits().add(this);
    }

    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
        ingredient.getProduits().remove(this);
    }
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Produit{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", graisse=").append(graisse);
        sb.append(", energie=").append(energie);
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
}
