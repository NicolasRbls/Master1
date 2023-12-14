package td3_poo.exo_4_td4;

public class Main {
    public static void main(String[] args) {
        // Triplet d'entiers
        Ducks<Integer> intDucks = new Ducks<>(1, 2, 3);
        intDucks.affiche();

        // Triplet de chaînes de caractères
        Ducks<String> stringDucks = new Ducks<>("Riri", "Fifi", "Loulou");
        stringDucks.affiche();

        // Triplet mixte 
        Ducks<?> mixedDucks = new Ducks<>(1, "Deux", 3.0);
        mixedDucks.affiche();
    }
}

