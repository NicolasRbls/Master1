package td1_poo.td1_exo11;

public class Main {
    public static void main(String[] args) {
        Rallye rallye = new Rallye();

        Personne pilote1 = new Personne("Jean", 32, "O+");
        Personne coPilote1 = new Personne("Pierre", 30, "A-");
        Voiture voiture1 = new Voiture("Renault Clio", "Groupe N", 130, pilote1, coPilote1);
        voiture1.ajouterTemps(120.5);
        voiture1.ajouterTemps(115.0);
        rallye.ajouterVoiture(voiture1);

        Personne pilote2 = new Personne("Jiles", 28, "O+");
        Personne coPilote2 = new Personne("Bernard", 29, "A-");
        Voiture voiture2 = new Voiture("Lancia Strada", "Groupe B", 140, pilote2, coPilote2);
        voiture2.ajouterTemps(115.0);
        voiture2.ajouterTemps(111.5);
        rallye.ajouterVoiture(voiture2);

        Personne pilote3 = new Personne("Luc", 31, "A+");
        Personne coPilote3 = new Personne("Hans", 28, "B+");
        Voiture voiture3 = new Voiture("Peugeot 208", "Groupe R", 150, pilote3, coPilote3);
        voiture3.ajouterTemps(119.0);
        voiture3.ajouterTemps(117.3);
        rallye.ajouterVoiture(voiture3);

        Personne pilote4 = new Personne("Robert", 35, "AB+");
        Personne coPilote4 = new Personne("Kevin", 27, "O-");
        Voiture voiture4 = new Voiture("Subaru Impreza", "Groupe A", 160, pilote4, coPilote4);
        voiture4.ajouterTemps(122.0);
        voiture4.ajouterTemps(118.7);
        rallye.ajouterVoiture(voiture4);

        Personne pilote5 = new Personne("Léo", 29, "B+");
        Personne coPilote5 = new Personne("Marc", 31, "AB-");
        Voiture voiture5 = new Voiture("Volkswagen Polo", "Groupe R", 150, pilote5, coPilote5);
        voiture5.ajouterTemps(123.5);
        voiture5.ajouterTemps(120.0);
        rallye.ajouterVoiture(voiture5);

        rallye.classementGeneral();
    }
}
