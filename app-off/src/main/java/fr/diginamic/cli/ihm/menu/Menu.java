package fr.diginamic.cli.ihm.menu;

import fr.diginamic.cli.ScannerProvider;
import fr.diginamic.cli.ihm.menu.entree.EntreeMenu;
import fr.diginamic.cli.ihm.menu.entree.TypeEntreeMenu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * La classe Menu représente un menu interactif avec des entrées que l'utilisateur peut choisir.
 */
public class Menu {

    /**
     * Map qui stocke les entrées du menu par leur position.
     */
    private final Map<Integer, EntreeMenu> entreesMenuParPosition;

    /**
     * Nom du menu.
     */
    private final String name;

    {
        entreesMenuParPosition = new TreeMap<>();
    }

    /**
     * Constructeur de la classe Menu.
     *
     * @param name Nom du menu.
     */
    public Menu(String name) {
        this.name = name;
    }

    /**
     * Exécute le menu.
     */
    public void exec() {
        Scanner scanner = ScannerProvider.getScanner();
        int input;
        EntreeMenu choix = null;

        // Boucle jusqu'à ce que l'utilisateur choisisse de quitter
        while (null == choix || choix.getType() != TypeEntreeMenu.QUIT) {
            System.out.println(this);
            System.out.println("Que souhaitez-vous faire ? ");
            input = scanner.nextInt();
            choix = entreesMenuParPosition.get(input);

            if (null == choix) {
                System.out.println("Choix " + input + " invalide.");
                continue;
            }

            if (choix.getType() == TypeEntreeMenu.ACTION) {
                choix.action();
            }
        }
    }

    /**
     * Ajoute une entrée au menu à une position spécifique.
     *
     * @param position    Position de l'entrée dans le menu.
     * @param entreeMenu  Instance de EntreeMenu représentant l'entrée à ajouter.
     */
    public void addEntreeMenu(int position, EntreeMenu entreeMenu) {
        if (null != entreeMenu) {
            entreesMenuParPosition.put(position, entreeMenu);
        }
    }

    /**
     * Retourne une représentation sous forme de chaîne du menu.
     *
     * @return Représentation du menu sous forme de chaîne.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name).append('\n');
        sb.append("=".repeat(name.length())).append('\n');

        for (Map.Entry<Integer, EntreeMenu> entreeMenuEntry : entreesMenuParPosition.entrySet()) {
            sb.append(entreeMenuEntry.getKey())
                    .append(". ")
                    .append(entreeMenuEntry.getValue())
                    .append('\n');
        }

        return sb.toString();
    }
}
