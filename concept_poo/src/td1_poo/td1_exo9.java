package td1_poo;

public class td1_exo9 {
    public static void main(String[] args) {
        String test1 = "radar";
        String test2 = "bonjour";
        
        System.out.println(test1 + " est un palindrome? " + estPalindrome(test1));
        System.out.println(test2 + " est un palindrome? " + estPalindrome(test2));
    }

    /**
     * Vérifie si une chaîne est un palindrome.
     * @param str - La chaîne à vérifier.
     * @return Vrai si la chaîne est un palindrome, sinon faux.
     */
    public static boolean estPalindrome(String str) {
        int i = 0, j = str.length() - 1;

        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false; // caractères ne sont pas les mêmes
            }
            i++;
            j--;
        }
        
        return true;
    }
}
