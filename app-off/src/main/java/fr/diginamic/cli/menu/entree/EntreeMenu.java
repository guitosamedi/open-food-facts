package fr.diginamic.cli.menu.entree;

public abstract class EntreeMenu implements Comparable<EntreeMenu>{
    private final Integer position;

    private final String libelle;

    public EntreeMenu(Integer position, String libelle) {
        this.position = position;
        this.libelle = libelle;
    }

    public abstract void action();

    @Override
    public int compareTo(EntreeMenu other) {
        return this.position.compareTo(other.position);
    }

    @Override
    public String toString() {
        return position + ". " + libelle;
    }
}
