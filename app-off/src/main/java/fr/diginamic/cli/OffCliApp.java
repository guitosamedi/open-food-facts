package fr.diginamic.cli;

import fr.diginamic.cli.ihm.menu.Menu;
import fr.diginamic.cli.ihm.menu.entree.NMeilleurProduitParCategorieEntreeMenu;
import fr.diginamic.cli.ihm.menu.entree.NMeilleurProduitParMarqueEntreeMenu;
import fr.diginamic.cli.ihm.menu.entree.NMeilleurProduitParMarqueEtParCategorieEntreeMenu;
import fr.diginamic.cli.ihm.menu.entree.QuitterEntreeMenu;

public class OffCliApp {
    public static void main(String[] args) {
        Menu menu = new Menu("Menu principal");
        menu.addEntreeMenu(99, new QuitterEntreeMenu());
        menu.addEntreeMenu(1, new NMeilleurProduitParMarqueEntreeMenu());
        menu.addEntreeMenu(2, new NMeilleurProduitParCategorieEntreeMenu());
        menu.addEntreeMenu(3, new NMeilleurProduitParMarqueEtParCategorieEntreeMenu());

        menu.exec();
    }
}
