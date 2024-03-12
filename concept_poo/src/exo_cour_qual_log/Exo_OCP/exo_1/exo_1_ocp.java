package exo_cour_qual_log.Exo_OCP.exo_1;

// Définition de l'interface pour toutes les opérations de calcul
interface CalculatorOperation {
    void performOperation();
    double getResult();
}

// Implémentation de l'addition
class Addition implements CalculatorOperation {
    private double left;
    private double right;
    private double result = 0.0;

    public Addition(double left, double right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void performOperation() {
        this.result = this.left + this.right;
    }

    @Override
    public double getResult() {
        return this.result;
    }
}

// Implémentation de la soustraction
class Subtraction implements CalculatorOperation {
    private double left;
    private double right;
    private double result = 0.0;

    public Subtraction(double left, double right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void performOperation() {
        this.result = this.left - this.right;
    }

    @Override
    public double getResult() {
        return this.result;
    }
}

// Classe du calculateur
class Calculator {
    public double calculate(CalculatorOperation operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Cannot perform operation");
        }
        operation.performOperation();
        return operation.getResult();
    }
}

// Exemple d'utilisation
class exo_1_ocp {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        
        CalculatorOperation addition = new Addition(10, 20);
        double additionResult = calculator.calculate(addition);
        System.out.println("Addition Result: " + additionResult);
        
        CalculatorOperation subtraction = new Subtraction(20, 10);
        double subtractionResult = calculator.calculate(subtraction);
        System.out.println("Subtraction Result: " + subtractionResult);
    }
}

