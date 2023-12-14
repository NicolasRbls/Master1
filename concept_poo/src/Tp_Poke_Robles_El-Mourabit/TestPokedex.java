import java.util.List;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestPokedex {
    public static void main(String[] args) {
        Pokedex pokedex = new Pokedex();

        // Tester la génération aléatoire d'un Pokémon
        Dresseur dresseur = new Dresseur("Ash");
        dresseur.chasserPokemon(); // Supposant que cette méthode prend un Pokedex en paramètre
        dresseur.chasserPokemon();
        dresseur.chasserPokemon();

        // Afficher les Pokémon capturés
        System.out.println("Pokémons capturés par " + dresseur.getNom() + ":");
        for (Pokemon pokemon : dresseur.getPokemonsDresseurs()) {
            System.out.println(pokemon.getNom() + " - PV: " + pokemon.getPV() + ", Attaque: " + pokemon.getAttaque() +
                               ", Défense: " + pokemon.getDefense() + ", Vitesse: " + pokemon.getVitesse() +
                                     ", Prochaine evolutions " + dresseur.determinerProchaineEvolution(pokemon.getNom()));
        }
        for (Pokemon pokemon : dresseur.getPokemonsDresseurs()) {
            afficherPokemonInfos(pokedex,pokemon.getNom());
        }


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
}
    


