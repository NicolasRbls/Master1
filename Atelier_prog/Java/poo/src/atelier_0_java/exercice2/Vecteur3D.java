package atelier_0_java.exercice2;

public class Vecteur3D {
    private double x;
    private double y;
    private double z;

    // Constructeur à trois arguments
    public Vecteur3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Constructeur sans argument, crée un vecteur (0,0,0)
    public Vecteur3D() {
        this(0, 0, 0);
    }

    // Méthode pour afficher les coordonnées du vecteur
    public void afficher() {
        System.out.println("<" + x + ", " + y + ", " + z + ">");
    }

    // Méthode pour calculer la norme du vecteur
    public double norme() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    // Méthode pour calculer le produit scalaire avec un autre vecteur
    public double produitScalaire(Vecteur3D autre) {
        return x * autre.x + y * autre.y + z * autre.z;
    }

    // Méthode pour calculer la somme avec un autre vecteur (méthode d'instance)
    public Vecteur3D somme(Vecteur3D autre) {
        return new Vecteur3D(x + autre.x, y + autre.y, z + autre.z);
    }

    // Méthode statique (de classe) pour calculer la somme de deux vecteurs
    public static Vecteur3D somme(Vecteur3D vecteur1, Vecteur3D vecteur2) {
        return new Vecteur3D(vecteur1.x + vecteur2.x, vecteur1.y + vecteur2.y, vecteur1.z + vecteur2.z);
    }

    // Méthode statique (de classe) pour calculer le produit scalaire de deux vecteurs
    public static double produitScalaire(Vecteur3D vecteur1, Vecteur3D vecteur2) {
        return vecteur1.x * vecteur2.x + vecteur1.y * vecteur2.y + vecteur1.z * vecteur2.z;
    }
}
    

