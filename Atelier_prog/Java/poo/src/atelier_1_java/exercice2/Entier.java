package atelier_1_java.exercice2;

public class Entier {
    private final int valeurMinimale;
    private final int valeurMaximale;
    private int valeur;

    public Entier(int valeurMinimale, int valeurMaximale) {
        this.valeurMinimale = valeurMinimale;
        this.valeurMaximale = valeurMaximale;
        this.valeur = 0; // Valeur par défaut à 0 si rien n'est spécifié à la construction
    }

    public Entier(int valeurMinimale, int valeurMaximale, int valeurInitiale) {
        this(valeurMinimale,valeurMaximale);
        if (estDansBornes(valeurInitiale)) {
            this.valeur = valeurInitiale;
        } else {
            throw new IllegalArgumentException("La valeur initiale n'est pas dans les bornes spécifiées.");
        }
    }

    public int getMin(){
        return this.valeurMinimale;
    }

    public int getMax(){
        return this.valeurMaximale;
    }

    private boolean estDansBornes(int valeur) {
        return valeur >= valeurMinimale && valeur <= valeurMaximale;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int nouvelleValeur) {
        if (estDansBornes(nouvelleValeur)) {
            valeur = nouvelleValeur;
        } else {
            throw new IllegalArgumentException("Erreur : La nouvelle valeur n'est pas dans les bornes spécifiées.");
        }
    }

    public void incremente(int pas) {
        int nouvelleValeur = valeur + pas;
        if (estDansBornes(nouvelleValeur)) {
            this.valeur = nouvelleValeur;
        } else {
            throw new IllegalArgumentException("Erreur : Incrémentation impossible, dépassement des bornes.");
        }
    }

    public void incremente() {
        incremente(1);
    }

    public String toString() {
        return String.valueOf(this.valeur);
    }

    public boolean equals(Object autreObjet) {
        if (this == autreObjet) {
            return true;
        }
        if (autreObjet == null || getClass() != autreObjet.getClass()) {
            return false;
        }
        Entier autreEntier = (Entier) autreObjet;
        return valeur == autreEntier.valeur &&
                valeurMinimale == autreEntier.valeurMinimale &&
                valeurMaximale == autreEntier.valeurMaximale;
    }

}

