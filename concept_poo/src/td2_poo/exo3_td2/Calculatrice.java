package td2_poo.exo3_td2;

public class Calculatrice extends Affichage implements Operation {
    @Override
    public Object addition(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            return (Integer) a + (Integer) b;
        } else {
            throw new IllegalArgumentException("Les opérandes doivent être des entiers.");
        }
    }

    @Override
    public Object soustraction(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            return (Integer) a - (Integer) b;
        } else {
            throw new IllegalArgumentException("Les opérandes doivent être des entiers.");
        }
    }

    @Override
    public Object multiplication(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            return (Integer) a * (Integer) b;
        } else {
            throw new IllegalArgumentException("Les opérandes doivent être des entiers.");
        }
    }

    @Override
    public Object division(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            if ((Integer) b == 0) {
                throw new ArithmeticException("Division par zéro.");
            }
            return (Integer) a / (Integer) b;
        } else {
            throw new IllegalArgumentException("Les opérandes doivent être des entiers.");
        }
    }
}

