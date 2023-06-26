package fr.diginamic.cli.menu;

import fr.diginamic.cli.menu.entree.EntreeMenu;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private final List<EntreeMenu> entreesMenu;

    private final String name;

    {
        entreesMenu = new ArrayList<>();
    }

    public Menu(String name) {
        this.name = name;
    }

    public void addEntreeMenu(EntreeMenu entreeMenu) {
        if (null != entreeMenu) {
            entreesMenu.add(entreeMenu);
            entreesMenu.sort(null);
        }
    }

    public void removeEntreeMenu(EntreeMenu entreeMenu) {
        if (null != entreeMenu) {
            entreesMenu.remove(entreeMenu);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name).append('\n');
        sb.append("=".repeat(name.length())).append('\n');

        for (EntreeMenu entree : entreesMenu) {
            sb.append(entree).append('\n');
        }

        return sb.toString();
    }
}
