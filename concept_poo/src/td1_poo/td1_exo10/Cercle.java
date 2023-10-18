package td1_poo.td1_exo10;

public class Cercle extends Figure2D {
    private double rayon;

    public Cercle(double rayon) {
        this.rayon = rayon;
    }

    @Override
    public double perimetre() {
        return 2 * Math.PI * rayon;
    }

    @Override
    public double aire() {
        return Math.PI * rayon * rayon;
    }
}
