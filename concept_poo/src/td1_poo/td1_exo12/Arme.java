package td1_poo.td1_exo12;

abstract class Arme {
    protected String nom;
    protected int degats;

    public Arme(String nom, int degats) {
        this.nom = nom;
        this.degats = degats;
    }

    public String getNom() {
        return nom;
    }

    public int getDegats() {
        return degats;
    }
}
