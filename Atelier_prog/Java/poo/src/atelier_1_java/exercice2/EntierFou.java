package atelier_1_java.exercice2;
import java.util.Random;

public class EntierFou extends Entier {
    private int niveauDeFolie;
    private static Random random = new Random();

    public EntierFou(int valeurMinimale, int valeurMaximale, int niveauDeFolie) {
        super(valeurMinimale, valeurMaximale);
        this.niveauDeFolie = niveauDeFolie;
    }

    public EntierFou(int valeurMinimale, int valeurMaximale, int valeurInitiale ,int niveauDeFolie) {
        super(valeurMinimale, valeurMaximale , valeurInitiale);
        this.niveauDeFolie = niveauDeFolie;
    }

    public int getNiveauDeFolie() {
        return niveauDeFolie;
    }

    public void incrementerFou() {
        int increment = random.nextInt(niveauDeFolie + 1);
        incremente(increment);
    }

    public String toString() {
        return super.toString() + " (Folie: " + getNiveauDeFolie() + ")";
    }

    public boolean equals(Object autreObjet) {
        if (this == autreObjet) {
            return true;
        }
        if (autreObjet == null || getClass() != autreObjet.getClass()) {
            return false;
        }
        if (!super.equals(autreObjet)) {
            return false;
        }
        EntierFou autreEntierFou = (EntierFou) autreObjet;
        return niveauDeFolie == autreEntierFou.niveauDeFolie;
    }
}
