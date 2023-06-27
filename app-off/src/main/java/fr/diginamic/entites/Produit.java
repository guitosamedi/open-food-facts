package fr.diginamic.entites;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String nom;

    private double graisse;

    private double energie;

    @Enumerated(value= EnumType.STRING)
    private ProduitScore score;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_marque")
    private Marque marque;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_categorie")
    private Categorie categorie;

    /*** Association Contient **/
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name= "produit_ingredient",
           joinColumns = @JoinColumn(name="id_produit", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name="id_ingredient", referencedColumnName = "id")
    )
    private Set<Ingredient> ingredients;

    /*** Association A **/
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name= "produit_allergene",
            joinColumns = @JoinColumn(name="id_produit", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="id_allergene", referencedColumnName = "id")
    )
    private Set<Allergene> allergenes;

    /*** Association Possede **/
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name= "produit_additif",
            joinColumns = @JoinColumn(name="id_produit", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="id_additif", referencedColumnName = "id")
    )
    private Set<Additif> additifs;

    {
        ingredients = new HashSet<>();
        allergenes = new HashSet<>();
        additifs = new HashSet<>();
    }

    public Produit(){};

    public Produit(String nom, double graisse, double energie, ProduitScore score) {
        this.nom = nom;
        this.graisse = graisse;
        this.energie = energie;
        this.score = score;
    }

    public Produit(int id, String nom, double graisse, double energie, ProduitScore score) {
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

    public double getGraisse() {
        return graisse;
    }

    public void setGraisse(double graisse) {
        this.graisse = graisse;
    }

    public double getEnergie() {
        return energie;
    }

    public void setEnergie(double energie) {
        this.energie = energie;
    }

    public ProduitScore getScore() {
        return score;
    }

    public void setScore(ProduitScore score) {
        this.score = score;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        if(null != this.marque){
            this.marque.getProduits().remove(this);
        }
        if(null != marque){
            marque.getProduits().add(this);
        }

        this.marque = marque;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        if(null != this.categorie){
            this.categorie.getProduits().remove(this);
        }
        if(null != categorie){
            categorie.getProduits().add(this);
        }
        this.categorie = categorie;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient){
        if (null != ingredient) {
            this.ingredients.add(ingredient);
            ingredient.getProduits().add(this);
        }

    }

    public void removeIngredient(Ingredient ingredient){
        if (null != ingredient) {
            this.ingredients.remove(ingredient);
            ingredient.getProduits().remove(this);
        }
    }

    public Set<Allergene> getAllergenes() {
        return allergenes;
    }

    public void setAllergenes(Set<Allergene> allergenes) {
        this.allergenes = allergenes;
    }

    public void addAllergene(Allergene allergene){
        if (null != allergene) {
            this.allergenes.add(allergene);
            allergene.getProduits().add(this);
        }
    }

    public void removeAllergene(Allergene allergene){
        if (null != allergene) {
            this.allergenes.remove(allergene);
            allergene.getProduits().remove(this);
        }
    }

    public Set<Additif> getAdditifs() {
        return additifs;
    }

    public void setAdditifs(Set<Additif> additifs) {
        this.additifs = additifs;
    }

    public void addAdditif(Additif additif){
        if (null != additif) {
            this.additifs.add(additif);
            additif.getProduits().add(this);
        }
    }

    public void removeAdditif(Additif additif){
        if (null != additif) {
            this.additifs.remove(additif);
            additif.getProduits().remove(this);
        }
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
