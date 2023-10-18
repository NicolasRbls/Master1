package td1_poo.td1_exo10;

public class Carre extends Figure2D{
    protected double cote;

    public Carre(double cote) {
        this.cote = cote;
    }

    @Override
    public double perimetre() {
        return 4 * cote;
    }

    @Override
    public double aire() {
        return cote * cote;
    }
}
