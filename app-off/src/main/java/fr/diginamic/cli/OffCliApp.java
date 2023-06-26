package fr.diginamic.cli;

import fr.diginamic.cli.menu.Menu;
import fr.diginamic.cli.menu.entree.NMeilleurProduitParMarqueEntreeMenu;
import fr.diginamic.cli.menu.entree.QuitterEntreeMenu;

public class OffCliApp {
    public static void main(String[] args) {
        Menu menu = new Menu("Menu principal");
        menu.addEntreeMenu(new QuitterEntreeMenu());
        menu.addEntreeMenu(new NMeilleurProduitParMarqueEntreeMenu());

        System.out.println(menu);
    }
}
