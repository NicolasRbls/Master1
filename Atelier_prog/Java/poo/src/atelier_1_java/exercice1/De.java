package atelier_1_java.exercice1;

import java.util.Random;

public class De {
    private String nom;
    private int nbFaces;
    private static Random r = new Random();
    

    public De(String nom, int nbFaces) {
        this.nom = nom;
        if (validerNbFaces(nbFaces)) {
            this.nbFaces = nbFaces;
        } else {
            System.out.println("Erreur : Le nombre de faces doit être compris entre 3 et 120.");
        }
    }

     // Constructeur par défaut avec 6 faces et nom par défaut "De"
     public De() {
        this("De", 6);
    }

    // Constructeur avec un nombre de faces spécifié
    public De(int nbFaces) {
        this("De", nbFaces);
    }

    // Constructeur avec un nom spécifié
    public De(String nom) {
        this(nom, 6);
    }

    public int getNbFaces() {
        return nbFaces;
    }

    public void setNbFaces(int nbFaces) {
        if (validerNbFaces(nbFaces)) {
            this.nbFaces = nbFaces;
        } else {
            System.out.println("Erreur : Le nombre de faces doit être compris entre 3 et 120.");
        }
    }

    public String getNom() {
        return nom;
    }

    private boolean validerNbFaces(int nbFaces) {
        return nbFaces >= 3 && nbFaces <= 120;
    }

    // Méthode pour lancer le dé et retourner la valeur du lancer
    public int lancer() {
        // Générer un nombre aléatoire entre 1 et nbFaces inclus
        int resultatLancer = r.nextInt(nbFaces) + 1;
        return resultatLancer;
    }
}

