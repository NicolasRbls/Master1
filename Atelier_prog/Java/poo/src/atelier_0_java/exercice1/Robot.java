package atelier_0_java.exercice1;

public class Robot {
     // Attributs
     private String reference;
     private String nom;
     private int x;
     private int y;
     private int orientation; // NORD=1, EST=2, SUD=3, OUEST=4
     private static int nombreRobotsCrees = 0;
 
     // Constantes pour les orientations
     public static final int NORD = 1;
     public static final int EST = 2;
     public static final int SUD = 3;
     public static final int OUEST = 4;

     // Constructeur avec référence générée automatiquement
    public Robot(String nom, int x, int y, int orientation) {
        this.nombreRobotsCrees++ ; 
        this.reference = "ROB" + (nombreRobotsCrees);
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    // Constructeur avec référence générée automatiquement et orientation par défaut (NORD)
    public Robot(String nom) {
        this(nom, 0, 0, NORD);
    }

    // Méthode pour modifier l'orientation du robot
    public void setOrientation(int nouvelleOrientation) {
        if (nouvelleOrientation >= 1 && nouvelleOrientation <= 4) {
            this.orientation = nouvelleOrientation;
        } else {
            System.out.println("Orientation invalide.");
        }
    }

    // Méthode pour déplacer le robot d'une unité dans la direction de son orientation
    public void deplacer() {
        if ((this.orientation == this.SUD && this.y - 1 < 0) || (this.orientation == this.OUEST && this.x - 1 < 0) ) {
            System.out.println("L'orientation devient négatives.");
        }else{
            switch (orientation) {
                case NORD:
                    y++;
                    break;
                case EST:
                    x++;
                    break;
                case SUD:
                    y--;
                    break;
                case OUEST:
                    x--;
                    break;
                default:
                    System.out.println("Orientation invalide.");
                }
        }
    }

    public void afficheToi() {
        System.out.println("Référence : " + reference);
        System.out.println("Nom : " + nom);
        System.out.println("Coordonnées : (" + x + ", " + y + ")");
        System.out.println("Orientation : " + this.getOrientation());
    }
    

    public String toString() {
        return "Référence : " + reference + "\n" +
                "Nom : " + nom + "\n" +
                "Coordonnées : (" + x + ", " + y + ")\n" +
                "Orientation : " + this.getOrientation();
    }

    // Méthode pour obtenir l'orientation du robot sous forme de chaîne de caractères
    public String getOrientation() {
        switch (orientation) {
            case NORD:
                return "NORD";
            case EST:
                return "EST";
            case SUD:
                return "SUD";
            case OUEST:
                return "OUEST";
            default:
                return "Orientation invalide";
        }
    }
}
