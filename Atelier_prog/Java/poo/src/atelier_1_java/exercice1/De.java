package atelier_1_java.exercice1;

import java.util.Random;

import atelier_1_java.exercice2.Entier;

public class De {
    private String nom;
    protected int nbFaces;
    protected static Random r = new Random();
    private static int nombreDesCrees = 0; // Variable de classe pour compter le nombre de Dés créés

    

    public De(String nom, int nbFaces) {
        this.nombreDesCrees++;
        this.nom = nom;
        if (validerNbFaces(nbFaces)) {
            this.nbFaces = nbFaces;
        } else {
            System.out.println("Erreur : Le nombre de faces doit être compris entre 3 et 120.");
        }
    }

     // Constructeur par défaut avec 6 faces et nom par défaut "De"
     public De() {
        this("Dé n°" + nombreDesCrees, 6);
    }

    // Constructeur avec un nombre de faces spécifié
    public De(int nbFaces) {
        this("Dé n°" + nombreDesCrees, nbFaces);
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
    
    // Surcharge de la méthode pour lancer le dé nb fois et retourner le meilleur résultat
    public int lancer(int nb) {
        if (nb <= 0) {
            throw new IllegalArgumentException("Le nombre de lancers doit être supérieur à zéro.");
        }

        int meilleurLancer = lancer(); // Lancer initial pour initialiser meilleurLancer

        for (int i = 1; i < nb; i++) {
            int lancerActuel = lancer(); // Lancer actuel
            if (lancerActuel > meilleurLancer) {
                meilleurLancer = lancerActuel; // Mettre à jour le meilleur résultat
            }
        }

        return meilleurLancer;
    }

    public String toString() {
        return "Dé '" + nom + "' avec " + nbFaces + " faces";
    }

   public boolean equals(Object autreObjet) {
        if (this == autreObjet) {
            return true;
        }
        if (autreObjet == null || getClass() != autreObjet.getClass()) {
            return false;
        }
        De autreDe = (De) autreObjet;
        return nbFaces == autreDe.getNbFaces() && nom.equals(autreDe.getNom());
    }

}

