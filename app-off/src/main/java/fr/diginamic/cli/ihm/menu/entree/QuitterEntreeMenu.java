package fr.diginamic.cli.ihm.menu.entree;

/**
 * La classe QuitterEntreeMenu représente une entrée de menu pour quitter l'application.
 */
public class QuitterEntreeMenu extends EntreeMenu {

    /**
     * Libellé de l'entrée de menu.
     */
    private static final String LIBELLE = "Quitter";

    /**
     * Constructeur de la classe QuitterEntreeMenu.
     */
    public QuitterEntreeMenu() {
        super(LIBELLE, TypeEntreeMenu.QUIT);
    }

    /**
     * Exécute l'action associée à l'entrée du menu, qui est vide dans ce cas (ne fait rien).
     */
    @Override
    public void action() {

    }
}
