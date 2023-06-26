package fr.diginamic.cli.menu.entree;

public abstract class EntreeMenu{
    private final String libelle;

    private final TypeEntreeMenu type;

    public EntreeMenu(String libelle, TypeEntreeMenu type) {
        this.libelle = libelle;
        this.type = type;
    }

    public abstract void action();

    @Override
    public String toString() {
        return libelle;
    }

    public TypeEntreeMenu getType() {
        return type;
    }
}
