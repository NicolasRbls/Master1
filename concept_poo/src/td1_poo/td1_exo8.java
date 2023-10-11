package td1_poo;

public class td1_exo8 {
    public static void main(String[] args) {
        System.out.println("Nombres premiers entre 1 et 100 :");
        for (int i = 1; i <= 100; i++) {
            if (estPremier(i)) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * Vérifie si un nombre est premier.
     * @param n - Le nombre à vérifier.
     * @return Vrai si le nombre est premier, sinon faux.
     */
    public static boolean estPremier(int n) {
        if (n <= 1) {
            return false; // 0 et 1 ne sont pas premiers
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false; // n est divisible par i, donc il n'est pas premier
            }
        }
        return true;
    }
}
