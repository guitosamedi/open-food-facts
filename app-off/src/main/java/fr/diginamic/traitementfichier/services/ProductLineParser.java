package fr.diginamic.traitementfichier.services;

import fr.diginamic.dal.*;
import fr.diginamic.dal.jpa.EMFProvider;
import fr.diginamic.entites.*;
import jakarta.persistence.EntityManager;

import java.util.*;

public class ProductLineParser {

    public void handleLine(String line) {
        IProduitDAO produitDAO = DAOFactory.getProduitDAO();
        try (EntityManager em = EMFProvider.getEmf().createEntityManager()) {
            em.getTransaction().begin();
            String[] columns = line.split("\\|", 31);

            double graisse = Optional.of(columns[6]).filter(ProductLineParser::isDouble).map(Double::parseDouble).orElse(0.0);
            double energie = Optional.of(columns[5]).filter(ProductLineParser::isDouble).map(Double::parseDouble).orElse(0.0);
            Produit produit = new Produit(columns[2], graisse, energie, ProduitScore.valueOf(columns[3].toUpperCase()));

            produit.setMarque(getMarque(columns[1], em));
            produit.setCategorie(getCategorie(columns[0], em));
            Arrays.stream(columns[29].split(","))
                    .filter(additifName -> !additifName.isBlank())
                    .map(additifNom -> getAdditifs(additifNom, em))
                    .forEach(produit::addAdditif);
            Arrays.stream(columns[28].split(","))
                    .map(String::trim)
                    .map(allergeneNom -> allergeneNom.replaceAll("/", ""))
                    .map(allergeneNom -> {
                        if (allergeneNom.contains(":")) {
                            return allergeneNom.substring(allergeneNom.indexOf(':')+1);
                        }
                        return allergeneNom;
                    })
                    .filter(allergeneNom -> !allergeneNom.isBlank())
                    .map(String::toUpperCase)
                    .distinct()
                    .map(allergeneNom -> getAllergene(allergeneNom, em))
                    .forEach(produit::addAllergene);
            Arrays.stream(columns[4].split(","))
                    .map(ingredientNom -> {
                        return ingredientNom.trim()
                                .replaceAll("_", "")
                                .replaceAll("\\.", "")
                                .replaceAll("\\*", "");
                    })
                    .map(ingredientNom -> {
                        while (ingredientNom.contains("(")) {
                            int indexOfFirstParentesis = ingredientNom.indexOf("(");
                            if (ingredientNom.contains(")")) {
                                int indexOfSecondParentesis = ingredientNom.indexOf(")");
                                ingredientNom = ingredientNom.substring(0, indexOfFirstParentesis-1) +
                                        ingredientNom.substring(indexOfSecondParentesis+1, ingredientNom.length()-1);
                            } else {
                                ingredientNom = ingredientNom.substring(0, Math.max(indexOfFirstParentesis-1, 0));
                            }
                        }

                        while(ingredientNom.contains("%")){
                            List<String> split = new ArrayList<String>(List.of(ingredientNom.split(" ")));
                            Collections.reverse(split);
                            Iterator<String> iterator = split.iterator();
                            while(iterator.hasNext()) {
                                String sousChaine = iterator.next();
                                if (sousChaine.contains("%")) {
                                    if (sousChaine.length() > 1) {
                                        iterator.remove();
                                    } else {
                                        iterator.remove();
                                        iterator.next();
                                        iterator.remove();
                                    }
                                    break;
                                }
                            }
                            Collections.reverse(split);
                            ingredientNom = String.join(" ", split);
                        }
                        return ingredientNom;
                    })
                    .filter(ingredientName -> !ingredientName.isBlank())
                    .map(String::toUpperCase)
                    .distinct()
                    .map(ingredientNom -> getIngredient(ingredientNom, em))
                    .forEach(produit::addIngredient);

            produitDAO.createProduit(produit, em);
            em.getTransaction().commit();
        }
    }

    private static Ingredient getIngredient(String ingredientNom, EntityManager em) {
        final IIngredientDAO ingredientDAO = DAOFactory.getIngredientDAO();

        Ingredient ingredient;
        try {
            ingredient = ingredientDAO.findByNom(ingredientNom, em);
        } catch (RuntimeException e) {
            ingredient = new Ingredient(ingredientNom);
        }
        return ingredient;
    }

    private static Allergene getAllergene(String allergeneNom, EntityManager em) {
        final IAllergeneDAO allergeneDAO = DAOFactory.getAllergeneDAO();

        Allergene allergene;
        try {
            allergene = allergeneDAO.findByNom(allergeneNom, em);
        } catch (RuntimeException e) {
            allergene = new Allergene(allergeneNom);
        }
        return allergene;
    }

    private static Additif getAdditifs(String additifNom, EntityManager em) {
        final IAdditifDAO additifDAO = DAOFactory.getAdditifDAO();

        Additif additif;
        try {
            additif = additifDAO.findByNom(additifNom, em);
        } catch (RuntimeException e) {
            additif = new Additif(additifNom);
        }
        return additif;
    }

    private static Categorie getCategorie(String nomCategorie, EntityManager em) {
        final ICategorieDAO categorieDAO = DAOFactory.getCategorieDAO();

        Categorie categorie;
        try {
            categorie = categorieDAO.findByNom(nomCategorie, em);
        } catch (RuntimeException e) {
            categorie = new Categorie(nomCategorie);
        }
        return categorie;
    }

    private static Marque getMarque(String nomMarque, EntityManager em) {
        final IMarqueDAO marqueDAO = DAOFactory.getMarqueDAO();

        Marque marque;
        try {
            marque = marqueDAO.findByNom(nomMarque, em);
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
