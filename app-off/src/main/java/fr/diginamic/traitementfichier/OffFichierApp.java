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
                String[] columns = scanner.nextLine().split("\\|", 31);

                double graisse = Optional.of(columns[6]).filter(OffFichierApp::isDouble).map(Double::parseDouble).orElse(0.0);
                double energie = Optional.of(columns[5]).filter(OffFichierApp::isDouble).map(Double::parseDouble).orElse(0.0);
                Produit produit = new Produit(columns[2], graisse, energie, ProduitScore.valueOf(columns[3].toUpperCase()));

                Marque marque = marques.computeIfAbsent(columns[1], Marque::new);
                produit.setMarque(marque);
                produit.setCategorie(categories.computeIfAbsent(columns[0], Categorie::new));
                Arrays.stream(columns[29].split(","))
                        .filter(additifName -> !additifName.isBlank())
                        .map(additifName -> additifs.computeIfAbsent(additifName, Additif::new))
                        .forEach(produit::addAdditif);
                Arrays.stream(columns[28].split(","))
                        .filter(allergeneName -> !allergeneName.isBlank())
                        .map(String::trim)
                        .map(String::toUpperCase)
                        .map(allergeneName -> allergenes.computeIfAbsent(allergeneName, Allergene::new))
                        .forEach(produit::addAllergene);
                Arrays.stream(columns[4].split(","))
                        .filter(ingredientName -> !ingredientName.isBlank())
                        .map(ingredientName -> ingredients.computeIfAbsent(ingredientName, Ingredient::new))
                        .forEach(produit::addIngredient);

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
