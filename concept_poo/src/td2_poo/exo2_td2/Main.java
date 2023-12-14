package td2_poo.exo2_td2;

public class Main {
    public static void main(String[] args) {
        // Crée une fabrique de véhicules électriques
        FabriqueVéhicule fabriqueÉlectrique = new FabriqueVéhiculeElecticité();

        // Crée une automobile électrique
        Automobile électrique = fabriqueÉlectrique.créerAutomobile("Modèle électrique", 150, "Bleu", 60);
        électrique.afficherCaractéristiques();

        // Crée un scooter électrique
        Scooter scooterÉlectrique = fabriqueÉlectrique.créerScooter("Scooter électrique", "Noir", 200);
        scooterÉlectrique.afficherCaractéristiques();

        // Crée une fabrique de véhicules à essence
        FabriqueVéhicule fabriqueEssence = new FabriqueVéhiculeEssence();

        // Crée une automobile à essence
        Automobile essence = fabriqueEssence.créerAutomobile("Modèle à essence", 200, "Rouge", 80);
        essence.afficherCaractéristiques();

        // Crée un scooter à essence
        Scooter scooterEssence = fabriqueEssence.créerScooter("Scooter à essence", "Blanc", 150);
        scooterEssence.afficherCaractéristiques();
    }
}

