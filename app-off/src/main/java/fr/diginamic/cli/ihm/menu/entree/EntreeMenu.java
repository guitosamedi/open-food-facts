package fr.diginamic.cli.ihm.menu.entree;

/**
 * La classe abstraite EntreeMenu représente une entrée de menu.
 */
public abstract class EntreeMenu {

    /**
     * Libellé de l'entrée du menu.
     */
    private final String libelle;

    /**
     * Type de l'entrée du menu.
     */
    private final TypeEntreeMenu type;

    /**
     * Constructeur de la classe EntreeMenu.
     *
     * @param libelle Libellé de l'entrée du menu.
     * @param type    Type de l'entrée du menu.
     */
    public EntreeMenu(String libelle, TypeEntreeMenu type) {
        this.libelle = libelle;
        this.type = type;
    }

    /**
     * Méthode abstraite qui représente l'action associée à l'entrée du menu.
     */
    public abstract void action();

    /**
     * Retourne une représentation sous forme de chaîne du libellé de l'entrée du menu.
     *
     * @return Libellé de l'entrée du menu.
     */
    @Override
    public String toString() {
        return libelle;
    }

    /**
     * Retourne le type de l'entrée du menu.
     *
     * @return Type de l'entrée du menu.
     */
    public TypeEntreeMenu getType() {
        return type;
    }
}
