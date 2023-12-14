package td3_poo.exo_2_td3;

public class Main {
    public static void main(String[] args) {
        try {
            double resultat = Racine.calculer(9.0);
            System.out.println("La racine carrée est : " + resultat);
            resultat = Racine.calculer(-5.0); // Ceci devrait générer une exception
        } catch (ValeurNegativeException e) {
            System.out.println(e.getMessage());
        }
    }
}
