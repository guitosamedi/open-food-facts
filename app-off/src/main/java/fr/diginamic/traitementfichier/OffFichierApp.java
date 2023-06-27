package fr.diginamic.traitementfichier;

import fr.diginamic.entites.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class OffFichierApp {
    private static final String cheminFichier = "csv/open-food-facts.csv";
    public static void main(String[] args) {
        List<Produit> produits = new ArrayList<>();
        Map<String, Marque> marques = new HashMap<>();
        Map<String, Categorie> categories = new HashMap<>();
        Map<String, Additif> additifs = new HashMap<>();
        Map<String, Allergene> allergenes = new HashMap<>();
        Map<String, Ingredient> ingredients = new HashMap<>();

        try ( FileInputStream fileInputStream = new FileInputStream(cheminFichier);
              Scanner scanner = new Scanner(fileInputStream)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] column = line.split("\\|", 31);

                double graisse = Optional.of(column[6]).filter(OffFichierApp::isDouble).map(Double::parseDouble).orElse(0.0);
                double energie = Optional.of(column[5]).filter(OffFichierApp::isDouble).map(Double::parseDouble).orElse(0.0);

                Produit produit = new Produit(column[2], graisse, energie, ProduitScore.valueOf(column[3].toUpperCase()));
                Marque marque = marques.computeIfAbsent(column[1], Marque::new);
                produit.setMarque(marque);
                Categorie categorie = categories.computeIfAbsent(column[0], Categorie::new);
                produit.setCategorie(categorie);
                for (String additifName : column[29].split(",")) {
                    if (additifName.isBlank()) {
                        continue;
                    }
                    Additif additif = additifs.computeIfAbsent(additifName, Additif::new);
                    produit.addAdditif(additif);
                }
                for (String allergeneName : column[28].split(",")) {
                    if (allergeneName.isBlank()) {
                        continue;
                    }
                    Allergene allergene = allergenes.computeIfAbsent(allergeneName, Allergene::new);
                    produit.addAllergene(allergene);
                }
                for (String ingredientName : column[4].split(",")) {
                    if (ingredientName.isBlank()) {
                        continue;
                    }
                    Ingredient ingredient = ingredients.computeIfAbsent(ingredientName, Ingredient::new);
                    produit.addIngredient(ingredient);
                }

                produits.add(produit);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end");
    }

    private static boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
