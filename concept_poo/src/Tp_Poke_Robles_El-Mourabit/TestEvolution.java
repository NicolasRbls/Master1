import java.util.List;
import java.util.ArrayList;

public class TestEvolution {
    public static void main(String[] args) {
        Pokedex pokedex = new Pokedex();
        // Créer un dresseur
        Dresseur dresseur = new Dresseur("Ash");

        // Ajouter un Herbizarre au dresseur
        Pokemon herbizarre = new Pokemon("Herbizarre", 100, 50, 55, 40, 90, new ArrayList<>(), List.of(Type.PLANTE));
        dresseur.ajouterPokemon(herbizarre);

        // Afficher les informations de Herbizarre
        //afficherPokemonInfos(pokedex,dresseur.getPokemonsDresseurs().get(0).getNom());
        System.out.println(listerAttaques(dresseur.getPokemonsDresseurs().get(0)));

        // Ajouter 5 bonbons de type PLANTE
        dresseur.ajouterBonbons(Type.PLANTE, 5);

        // Faire évoluer Herbizarre
        dresseur.evoluerPokemon(herbizarre);

        // Afficher les informations après l'évolution
        System.out.println("Pokémon après évolution: " );
        //afficherPokemonInfos(pokedex , dresseur.getPokemonsDresseurs().get(0).getNom());
        System.out.println(listerAttaques(dresseur.getPokemonsDresseurs().get(0)));
    }

    private static void afficherPokemonInfos(Pokedex pokedex, String nomPokemon) {
        Pokedex.PokemonInfo pokemonInfo = pokedex.getPokemons().stream()
            .filter(p -> p.getNom().equals(nomPokemon))
            .findFirst()
            .orElse(null);

        if (pokemonInfo != null) {
            System.out.println("Nom: " + pokemonInfo.getNom());
            System.out.println("Type: " + pokemonInfo.getType());
            System.out.println("Étape d'évolution: " + pokemonInfo.getEvolutionStage());
            System.out.println("Evolutions: " + pokedex.getEvolutions(nomPokemon));
            System.out.println("Toutes les evolutions: " + pokedex.getAllEvolutions(nomPokemon));
            

        } else {
            System.out.println("Pokemon non trouvé: " + nomPokemon);
        }
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

