package td3_poo.exo_2_td3;

public class Racine {
    public static double calculer(double valeur) throws ValeurNegativeException {
        if (valeur < 0) {
            throw new ValeurNegativeException("La valeur ne peut pas être négative : " + valeur);
        }
        return Math.sqrt(valeur);
    }
}
