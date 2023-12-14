package td6_poo.exo_1_2_td6;

public class Main2 {
    public static void main(String[] args) {
        Deserialiser deserialiser = new Deserialiser();
        Personne personne = deserialiser.deserializePersonneFromText("C:\\Users\\n" + //
                "icol\\OneDrive\\Bureau\\Python\\Licence3\\concept_poo\\src\\td6_poo\\exo_1_2_td6\\personne.txt");

        if (personne != null) {
            System.out.println("Objet récupéré : " + personne);
        } else {
            System.out.println("Erreur lors de la désérialisation");
        }
    }
}

