package td1_poo;

public class td1_exo5 {
    public static void main(String[] args) {
        String original = "Ma chaine";
        String inverse = inverser(original);
        System.out.println("Chaîne originale: " + original);
        System.out.println("Chaîne inversée: " + inverse);
    }

    /**
     * Inverse une chaîne de caractères.
     * @param str - La chaîne à inverser.
     * @return La chaîne inversée.
     */
    public static String inverser(String str) {
        String inverse = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            inverse += str.charAt(i);
        }
            return inverse;
        }
}

