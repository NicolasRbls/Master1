package td2_poo.exo3_td2;

public class Main {
    public static void main(String[] args) {
        // Utilisation de la calculatrice pour les entiers
        Calculatrice calculatrice = new Calculatrice();

        // Addition d'entiers
        Object additionResult = calculatrice.addition(5, 3);
        calculatrice.afficher(additionResult);

        // Soustraction d'entiers
        Object soustractionResult = calculatrice.soustraction(10, 3);
        calculatrice.afficher(soustractionResult);

        // Multiplication d'entiers
        Object multiplicationResult = calculatrice.multiplication(4, 5);
        calculatrice.afficher(multiplicationResult);

        // Division d'entiers
        Object divisionResult = calculatrice.division(8, 2);
        calculatrice.afficher(divisionResult);

        // Utilisation de la calculatrice pour les nombres réels (Double)
        CalculatriceReel calculatriceReel = new CalculatriceReel();

        // Addition de nombres réels
        Object additionResultReel = calculatriceReel.addition(3.5, 2.5);
        calculatriceReel.afficher(additionResultReel);

        // Soustraction de nombres réels
        Object soustractionResultReel = calculatriceReel.soustraction(8.5, 2.0);
        calculatriceReel.afficher(soustractionResultReel);

        // Multiplication de nombres réels
        Object multiplicationResultReel = calculatriceReel.multiplication(3.0, 2.5);
        calculatriceReel.afficher(multiplicationResultReel);

        // Division de nombres réels
        Object divisionResultReel = calculatriceReel.division(5.0, 2.0);
        calculatriceReel.afficher(divisionResultReel);
    }
}

