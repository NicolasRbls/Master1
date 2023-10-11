package td1_poo;

public class td1_exo4 {
    public static void main(String[] args) {
        int[] tableau = {10, 20, 30, 40, 50}; // exemple de tableau

        double moyenne = calculMoyenne(tableau);

        System.out.println("La moyenne du tableau est: " + moyenne);
    }

    /**
     * Calcule la moyenne des éléments d'un tableau d'entiers.
     * @param arr - Le tableau d'entiers.
     * @return La moyenne des éléments du tableau.
     */
    public static double calculMoyenne(int[] arr) {
        int somme = 0;
        for (int i = 0; i < arr.length; i++) {
            somme += arr[i];
        }
        return (double) somme / arr.length;
    }
}
