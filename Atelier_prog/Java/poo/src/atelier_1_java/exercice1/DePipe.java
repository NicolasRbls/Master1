package atelier_1_java.exercice1;

public class DePipe extends De {
    private int valeurMinimale;

    public DePipe(String nom, int nbFaces, int valeurMinimale) {
        super(nom, nbFaces); // Appel au constructeur de la classe De
        if (validerValeurMinimale(valeurMinimale)) {
            this.valeurMinimale = valeurMinimale;
        } else {
            throw new IllegalArgumentException("La valeur minimale doit être supérieure ou égale à 1.");
        }
    }

    // Redéfinition de la méthode lancer pour un dé pipé
    public int lancer() {
        // Générer un nombre aléatoire entre valeurMinimale et nbFaces inclus
        return r.nextInt(nbFaces - valeurMinimale + 1) + valeurMinimale;
    }

    private boolean validerValeurMinimale(int valeurMinimale) {
        return valeurMinimale >= 1;
    }
}

