package td1_poo.td1_exo10;

public class Main {
    public static void main(String[] args) {
        // Test pour Carré
        Carre carre = new Carre(4);
        System.out.println("Pour un carré de côté 4:");
        System.out.println("Périmètre: " + carre.perimetre());
        System.out.println("Aire: " + carre.aire());
        System.out.println("-----------------------------");

        // Test pour Rectangle
        Rectangle rectangle = new Rectangle(4, 7);
        System.out.println("Pour un rectangle de longueur 4 et de largeur 7:");
        System.out.println("Périmètre: " + rectangle.perimetre());
        System.out.println("Aire: " + rectangle.aire());
        System.out.println("-----------------------------");

        // Test pour Cercle
        Figure2D cercle = new Cercle(5);
        System.out.println("Pour un cercle de rayon 5:");
        System.out.println("Périmètre: " + cercle.perimetre());
        System.out.println("Aire: " + cercle.aire());
        System.out.println("-----------------------------");
    }
}

