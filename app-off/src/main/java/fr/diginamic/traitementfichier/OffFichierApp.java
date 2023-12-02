package fr.diginamic.traitementfichier;

import fr.diginamic.dal.*;
import fr.diginamic.dal.jpa.EMFProvider;
import fr.diginamic.entites.*;
import fr.diginamic.traitementfichier.services.ProductLineParser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import fr.diginamic.dal.jpa.CategorieDAO;
import jakarta.transaction.Transactional;

public class OffFichierApp {
    private static final String cheminFichier = "csv/open-food-facts.csv";

    public static void main(String[] args) {
        final IProduitDAO produitDAO = DAOFactory.getProduitDAO();
        final ProductLineParser productLineParser = new ProductLineParser();

        try ( FileInputStream fileInputStream = new FileInputStream(cheminFichier);
              Scanner scanner = new Scanner(fileInputStream);) {
            int i = 0;
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                i++;
                System.out.println("Line : " + i);

                productLineParser.handleLine(scanner.nextLine());

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end");
    }
}
