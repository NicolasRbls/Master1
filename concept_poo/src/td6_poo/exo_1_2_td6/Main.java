package td6_poo.exo_1_2_td6;

public class Main {
    public static void main(String[] args) {
        Personne personne = new Personne("Doe", "John", 30);
        Serialiser serialiser = new Serialiser();
        serialiser.serializePersonneToText(personne, "C:\\Users\\n" + //
                "icol\\OneDrive\\Bureau\\Python\\Licence3\\concept_poo\\src\\td6_poo\\exo_1_2_td6\\personne.txt");
    }
}
