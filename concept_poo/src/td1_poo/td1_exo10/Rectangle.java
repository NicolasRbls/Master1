package td1_poo.td1_exo10;

public class Rectangle extends Carre {
    protected double largeur;

    public Rectangle(double longueur, double largeur) {
        super(longueur);  // utilise le cote de Carre pour stocker la longueur
        this.largeur = largeur;
    }

    @Override
    public double perimetre() {
        return 2 * (cote + largeur);
    }

    @Override
    public double aire() {
        return cote * largeur;
    }
}
