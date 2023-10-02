package atelier_1_java.exercice1;

public class DeEffetMemoire extends De {
    private int derniereValeur = -1; // Initialisé à -1 pour indiquer qu'aucune valeur n'a été générée

    public DeEffetMemoire(String nom, int nbFaces) {
        super(nom, nbFaces); // Appel au constructeur de la classe De
    }

    // Redéfinition de la méthode lancer pour un dé à effet mémoire
    public int lancer() {
        int nouvelleValeur;
        do {
            nouvelleValeur = super.lancer(); // Générer une nouvelle valeur avec la méthode lancer de la classe De
        } while (nouvelleValeur == derniereValeur); // Continuer jusqu'à obtenir une nouvelle valeur différente

        // Mettre à jour la dernière valeur générée
        this.derniereValeur = nouvelleValeur;

        return nouvelleValeur;
    }

}

