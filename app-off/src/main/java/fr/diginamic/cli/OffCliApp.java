package fr.diginamic.cli;

import fr.diginamic.cli.ihm.menu.Menu;
import fr.diginamic.cli.ihm.menu.entree.*;

public class OffCliApp {
    public static void main(String[] args) {
        Menu menu = new Menu("Menu principal");
        menu.addEntreeMenu(99, new QuitterEntreeMenu());
        menu.addEntreeMenu(1, new NMeilleurProduitParMarqueEntreeMenu());
        menu.addEntreeMenu(2, new NMeilleurProduitParCategorieEntreeMenu());
        menu.addEntreeMenu(3, new NMeilleurProduitParMarqueEtParCategorieEntreeMenu());
        menu.addEntreeMenu(4, new NIngredientsLesPlusCourantsEntreeMenu());
        menu.addEntreeMenu(5, new NAllergenesLesPlusCourantsEntreeMenu());
        menu.addEntreeMenu(6, new NAdditifsLesPlusCourantsEntreeMenu());

        menu.exec();
    }
}
