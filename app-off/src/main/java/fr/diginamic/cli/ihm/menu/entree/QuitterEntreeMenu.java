package fr.diginamic.cli.ihm.menu.entree;

public class QuitterEntreeMenu extends EntreeMenu {
    private static final String LIBELLE = "Quitter";

    public QuitterEntreeMenu() {
        super(LIBELLE, TypeEntreeMenu.QUIT);
    }

    @Override
    public void action() {

    }
}