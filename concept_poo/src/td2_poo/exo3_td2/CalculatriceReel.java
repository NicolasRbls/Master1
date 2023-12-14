package td2_poo.exo3_td2;

public class CalculatriceReel extends Affichage implements Operation {
    @Override
    public Object addition(Object a, Object b) {
        if (a instanceof Double && b instanceof Double) {
            return (Double) a + (Double) b;
        } else {
            throw new IllegalArgumentException("Les opérandes doivent être des nombres réels (Double).");
        }
    }

    @Override
    public Object soustraction(Object a, Object b) {
        if (a instanceof Double && b instanceof Double) {
            return (Double) a - (Double) b;
        } else {
            throw new IllegalArgumentException("Les opérandes doivent être des nombres réels (Double).");
        }
    }

    @Override
    public Object multiplication(Object a, Object b) {
        if (a instanceof Double && b instanceof Double) {
            return (Double) a * (Double) b;
        } else {
            throw new IllegalArgumentException("Les opérandes doivent être des nombres réels (Double).");
        }
    }

    @Override
    public Object division(Object a, Object b) {
        if (a instanceof Double && b instanceof Double) {
            if ((Double) b == 0.0) {
                throw new ArithmeticException("Division par zéro.");
            }
            return (Double) a / (Double) b;
        } else {
            throw new IllegalArgumentException("Les opérandes doivent être des nombres réels (Double).");
        }
    }
}

