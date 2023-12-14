import java.util.List;

public class TestCombat {
    public static void main(String[] args) {
        // Création des dresseurs et de leurs Pokémon
        Dresseur dresseur1 = new Dresseur("Ash");
        Pokemon pikachu = new Pokemon("Pikachu", 100, 50, 55, 40, 90, null, List.of(Type.ELECTRIK));
        Pokemon bulbizarre = new Pokemon("Bulbizarre", 95, 45, 49, 49, 45, null, List.of(Type.PLANTE));
        dresseur1.ajouterPokemon(pikachu);
        dresseur1.ajouterPokemon(bulbizarre);

        Dresseur dresseur2 = new Dresseur("Gary");
        Pokemon salameche = new Pokemon("Salamèche", 100, 50, 52, 43, 65, null, List.of(Type.FEU));
        Pokemon carapuce = new Pokemon("Carapuce", 90, 48, 48, 65, 43, null, List.of(Type.EAU));
        dresseur2.ajouterPokemon(salameche);
        dresseur2.ajouterPokemon(carapuce);

        // Afficher les attaques des Pokémon
        System.out.println("Attaques de Pikachu: " + listerAttaques(pikachu));
        System.out.println("Attaques de Bulbizarre: " + listerAttaques(bulbizarre));
        System.out.println("Attaques de Salamèche: " + listerAttaques(salameche));
        System.out.println("Attaques de Carapuce: " + listerAttaques(carapuce));

        // Démarrer le combat
        GestionCombat gestionCombat = new GestionCombat(dresseur1, dresseur2);
        String resultat = gestionCombat.demarrerCombat();

        // Afficher le résultat du combat
        System.out.println(resultat);
    }

    private static String listerAttaques(Pokemon pokemon) {
        StringBuilder sb = new StringBuilder();
        for (Attaque attaque : pokemon.getAttaques()) {
            sb.append(attaque.getNom()).append(" (").append(attaque.getPuissance()).append("), ");
        }
        // Supprimer la dernière virgule et l'espace
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}


