package fr.diginamic.cli.ihm.menu;

import fr.diginamic.cli.ScannerProvider;
import fr.diginamic.cli.ihm.menu.entree.EntreeMenu;
import fr.diginamic.cli.ihm.menu.entree.TypeEntreeMenu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Menu {

    private final Map<Integer, EntreeMenu> entreesMenuParPosition;

    private final String name;

    {
        entreesMenuParPosition = new TreeMap<>();
    }

    public Menu(String name) {
        this.name = name;
    }

    public void exec() {
        Scanner scanner = ScannerProvider.getScanner();
        int input;
        EntreeMenu choix = null;
        while (null == choix || choix.getType() != TypeEntreeMenu.QUIT) {
            System.out.println(this);
            System.out.println("Que souhaitez-vous faire ? ");
            input = scanner.nextInt();
            choix = entreesMenuParPosition.get(input);
            if (null == choix) {
                System.out.println("Choix " + input + " invalid.");
                continue;
            }
            if (choix.getType() == TypeEntreeMenu.ACTION) {
                choix.action();
            }
        }
    }


    public void addEntreeMenu(int position, EntreeMenu entreeMenu) {
        if (null != entreeMenu) {
            entreesMenuParPosition.put(position, entreeMenu);
        }
    }

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
