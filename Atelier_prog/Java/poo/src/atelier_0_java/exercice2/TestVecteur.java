package atelier_0_java.exercice2;

public class TestVecteur {
    public static void main(String[] args) {
        // Création de deux vecteurs
        Vecteur3D v1 = new Vecteur3D(3.0, 2.0, 5.0);
        Vecteur3D v2 = new Vecteur3D(1.0, 2.0, 3.0);

        // Affichage des coordonnées des vecteurs
        System.out.println("v1 = ");
        v1.afficher();
        System.out.println("Norme de v1 = " + v1.norme());

        System.out.println("v2 = ");
        v2.afficher();
        System.out.println("Norme de v2 = " + v2.norme());

        // Calcul de la somme des vecteurs
        Vecteur3D somme = v1.somme(v2);
        System.out.println("v1 + v2 = ");
        somme.afficher();

        // Calcul du produit scalaire
        double produitScalaire = v1.produitScalaire(v2);
        System.out.println("v1.v2 = " + produitScalaire);
    }
}
