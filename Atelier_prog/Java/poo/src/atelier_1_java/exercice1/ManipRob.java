package atelier_1_java.exercice1;

public class ManipRob {
    public static void main(String[] args) {
        // Création de deux robots
        Robot toto = new Robot("Toto", 10, 20, 3);
        Robot titi = new Robot("Titi", 0, 0, 1);

        // Déplacement et changement d'orientation
        toto.deplacer();  // Toto se déplace vers le Sud
        titi.setOrientation(2);  // Titi change d'orientation vers l'Est
        titi.deplacer();  // Titi se déplace vers l'Est

        System.out.println(toto);
        System.out.println(titi);
    }
}
