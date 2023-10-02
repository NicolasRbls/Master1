package atelier_1_java.exercice1;

public class TestDe {
    public static void main(String[] args) {
        // Créez une instance de Dé par défaut
        De deParDefaut = new De();
        System.out.println("Dé par défaut : " + deParDefaut.getNom() + " avec " + deParDefaut.getNbFaces() + " faces");

        // Créez une instance de Dé avec un nombre de faces spécifié
        De deAvecFaces = new De(12);
        System.out.println("Dé avec 12 faces : " + deAvecFaces.getNom() + " avec " + deAvecFaces.getNbFaces() + " faces");

        // Créez une instance de Dé avec un nom spécifié
        De deAvecNom = new De("MonDé");
        System.out.println("Dé avec nom 'MonDé' : " + deAvecNom.getNom() + " avec " + deAvecNom.getNbFaces() + " faces");
    }
}
