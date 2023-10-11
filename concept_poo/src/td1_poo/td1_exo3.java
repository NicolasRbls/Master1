package td1_poo;

public class td1_exo3 {
    public static void main(String[] args) {
            
        
            double rayon = 5; // Exemple de rayon

            double perimetre = calculPerimetre(rayon);
            double aire = calculAire(rayon);

            System.out.println("Pour un cercle de rayon " + rayon + " :");
            System.out.println("Périmètre = " + perimetre);
            System.out.println("Aire = " + aire);
            }
        
            /**
             * Calcule le périmètre d'un cercle en fonction de son rayon.
             * @param R - Le rayon du cercle.
             * @return Le périmètre du cercle.
             */
            public static double calculPerimetre(double R) {
                return 2 * Math.PI * R;
            }
        
            /**
             * Calcule l'aire d'un cercle en fonction de son rayon.
             * @param R - Le rayon du cercle.
             * @return L'aire du cercle.
             */
            public static double calculAire(double R) {
                return Math.PI * Math.pow(R, 2);
            }
}
