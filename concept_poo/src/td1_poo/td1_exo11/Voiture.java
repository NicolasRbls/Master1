package td1_poo.td1_exo11;
  
public class Voiture {
    private String modele;
    private String categorie; // Groupe N, Groupe A, Groupe R
    private int puissance;
    private Personne pilote;
    private Personne coPilote;
    private double tempsTotal;

    public Voiture(String modele, String categorie, int puissance, Personne pilote, Personne coPilote) {
        this.modele = modele;
        this.categorie = categorie;
        this.puissance = puissance;
        this.pilote = pilote;
        this.coPilote = coPilote;
        this.tempsTotal = 0;
    }

    public void ajouterTemps(double tempsCourse) {
        this.tempsTotal += tempsCourse;
    }

    // Getters
    public String getModele() {
        return modele;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getPuissance() {
        return puissance;
    }

    public Personne getPilote() {
        return pilote;
    }

    public Personne getCoPilote() {
        return coPilote;
    }

    public double getTempsTotal() {
        return tempsTotal;
    }
    
}

