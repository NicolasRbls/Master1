package atelier_1_java.exercice1;
public class TestDe {
    public static void main(String[] args) {
        //Dé qui sert au test de l'incrémentation
        De deTest1 = new De();
        De deTest2 = new De();
        De deTest3 = new De();


        // Créez une instance de Dé par défaut
        De deParDefaut = new De();
        System.out.println("Dé par défaut : " + deParDefaut.getNom() + " avec " + deParDefaut.getNbFaces() + " faces");

        // Créez une instance de Dé avec un nombre de faces spécifié
        De deAvecFaces = new De(12);
        System.out.println("Dé avec 12 faces : " + deAvecFaces.getNom() + " avec " + deAvecFaces.getNbFaces() + " faces");

        // Créez une instance de Dé avec un nom spécifié
        De deAvecNom = new De("MonDé");
        System.out.println("Dé avec nom 'MonDé' : " + deAvecNom.getNom() + " avec " + deAvecNom.getNbFaces() + " faces");

        System.out.println("Dé lancé : " + deParDefaut.lancer());
        System.out.println("Sur 10 essaie , sur un Dé a 6 face voici mon meilleur lancé : "+deParDefaut.lancer(10));

        De test1 = new De("DéRouge",10);
        System.out.println(test1);

        De test2 = new De("DéBleu",10);
        De test3 = new De("DéBleu",10);
        System.out.println("Dé avec même nom et nb faces, resultat : " + test2.equals(test3));
        System.out.println("Dé avec même nom mais pas même nb faces, resultat : " + test1.equals(test3));

        // Création d'un dé pipé avec 6 faces et une valeur minimale de 4
        DePipe dePipe = new DePipe("MonDéPipe", 6, 4);

        // Lancer le dé pipé plusieurs fois
        for (int i = 0; i < 10; i++) {
            int resultatLancer = dePipe.lancer();
            System.out.println("Résultat du lancer " + (i + 1) + " : " + resultatLancer);
        }
    }

}
