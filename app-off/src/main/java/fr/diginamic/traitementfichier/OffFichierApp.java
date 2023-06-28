package fr.diginamic.traitementfichier;

import fr.diginamic.dal.*;
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

        final IProduitDAO produitDAO = DAOFactory.getProduitDAO();
        final IAdditifDAO additifDAO = DAOFactory.getAdditifDAO();
        final IAllergeneDAO allergeneDAO = DAOFactory.getAllergeneDAO();
        final IIngredientDAO ingredientDAO = DAOFactory.getIngredientDAO();


        try ( FileInputStream fileInputStream = new FileInputStream(cheminFichier);
              Scanner scanner = new Scanner(fileInputStream)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split("\\|", 31);

                double graisse = Optional.of(columns[6]).filter(OffFichierApp::isDouble).map(Double::parseDouble).orElse(0.0);
                double energie = Optional.of(columns[5]).filter(OffFichierApp::isDouble).map(Double::parseDouble).orElse(0.0);
                Produit produit = new Produit(columns[2], graisse, energie, ProduitScore.valueOf(columns[3].toUpperCase()));

                produit.setMarque(getMarque(columns[1]));
                produit.setCategorie(getCategorie(columns[0]));
//                Arrays.stream(columns[29].split(","))
//                        .filter(additifName -> !additifName.isBlank())
//                        .map(additifName -> additifs.computeIfAbsent(additifName, Additif::new))
//                        .forEach(produit::addAdditif);
//                Arrays.stream(columns[28].split(","))
//                        .filter(allergeneName -> !allergeneName.isBlank())
//                        .map(String::trim)
//                        .map(String::toUpperCase)
//                        .map(allergeneName -> allergenes.computeIfAbsent(allergeneName, Allergene::new))
//                        .forEach(produit::addAllergene);
//                Arrays.stream(columns[4].split(","))
//                        .filter(ingredientName -> !ingredientName.isBlank())
//                        .map(ingredientName -> ingredients.computeIfAbsent(ingredientName, Ingredient::new))
//                        .forEach(produit::addIngredient);

//                produits.add(produit);
                produitDAO.createProduit(produit);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end");
    }

    private static Categorie getCategorie(String nomCategorie) {
        final ICategorieDAO categorieDAO = DAOFactory.getCategorieDAO();

        Categorie categorie;
        try {
            categorie = categorieDAO.findByNom(nomCategorie);
        } catch (RuntimeException e) {
            categorie = new Categorie(nomCategorie);
        }
        return categorie;
    }

    private static Marque getMarque(String nomMarque) {
        final IMarqueDAO marqueDAO = DAOFactory.getMarqueDAO();

        Marque marque;
        try {
            marque = marqueDAO.findByNom(nomMarque);
        } catch (RuntimeException e) {
            marque = new Marque(nomMarque);
        }
        return marque;
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
