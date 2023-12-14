import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Dresseur {
    private String nom;
    private List<Pokemon> pokemons;
    private Scanner scanner;
    private Pokedex pokedex; // Référence au Pokedex
    private Map<Type, Integer> inventaireBonbons;



    public Dresseur(String nom) {
        this.nom = nom;
        this.pokemons = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.pokedex=new Pokedex(); // Initialisation du Pokedex
        inventaireBonbons = new HashMap<>();
        for (Type type : Type.values()) {
            inventaireBonbons.put(type, 0); // Initialisation avec 0 bonbon de chaque type
        }

    }

     public Dresseur(String nom, List<Pokemon> pokemons) {
        this.nom = nom;
        this.pokemons = pokemons;
        this.scanner = new Scanner(System.in);
    }

    public void ajouterPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
    }

    public String getNom() {
        return nom;
    }

    public boolean aDesPokemonValides() {
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getPV() > 0) {
                return true;
            }
        }
        return false;
    }

    public Pokemon choisirPokemon() {
        if (this.pokemons.isEmpty()) {
            return null;
        }

        System.out.println("Choisissez un Pokémon:");
        List<Integer> choixValides = new ArrayList<>();
        int i = 1;
        for (Pokemon pokemon : this.pokemons) {
            if (pokemon.getPV() > 0) {
                System.out.println(i + ". " + pokemon.getNom());
                choixValides.add(this.pokemons.indexOf(pokemon));
                i++;
            }
        }

        int choix = scanner.nextInt() - 1;
        if (choix >= 0 && choix < choixValides.size()) {
            return this.pokemons.get(choixValides.get(choix));
        } else {
            System.out.println("Choix invalide.");
            return choisirPokemon();
        }
    }

    public Pokemon choisirPokemon(int index) {
        if (index < 0 || index >= this.pokemons.size()) {
            return null;
        }
    
        return this.pokemons.get(index);
    }

    public void chasserPokemon() {
        Pokemon pokemon = genererPokemonAleatoire();
        pokemons.add(pokemon);
        System.out.println("Vous avez capturé un nouveau Pokémon: " + pokemon.getNom());
    }

    private Pokemon genererPokemonAleatoire() {
        Random random = new Random();
        List<Pokedex.PokemonInfo> tousPokemons = pokedex.getPokemons();
        Pokedex.PokemonInfo pokemonInfo = tousPokemons.get(random.nextInt(tousPokemons.size()));

        String nom = pokemonInfo.getNom();
        int evolution = pokemonInfo.getEvolutionStage();
        int PC , PV, attaque, defense, vitesse;

        // Logique pour définir les statistiques basées sur le stade d'évolution
        if (evolution == 1) {
            PC = PV = random.nextInt(91) + 10; // Entre 10 et 100
            attaque = defense = vitesse = random.nextInt(21) + 10; // Entre 10 et 30
        } else if (evolution == 2) {
            PC = PV = random.nextInt(101) + 100; // Entre 100 et 200
            attaque = defense = vitesse = random.nextInt(31) + 30; // Entre 30 et 60
        } else {
            PC = PV = random.nextInt(151) + 200; // Entre 200 et 350
            attaque = defense = vitesse = random.nextInt(91) + 60; // Entre 60 et 150
        }

        List<Type> types = new ArrayList<>();
        types.add(pokemonInfo.getType()); // Définir le type à partir du Pokedex

        return new Pokemon(nom, PV, PC, attaque, defense, vitesse, new ArrayList<>(), types);
    }

    private int determinerEvolution(String nom) {
        return pokedex.getPokemons().stream()
                .filter(p -> p.getNom().equals(nom))
                .findFirst()
                .map(Pokedex.PokemonInfo::getEvolutionStage)
                .orElse(1); // Valeur par défaut si le Pokémon n'est pas trouvé
    }

    public String determinerProchaineEvolution(String nom) {
        List<String> evolutions = pokedex.getEvolutions(nom);
        if (evolutions != null && !evolutions.isEmpty()) {
            // Retourne le nom de la première évolution
            return evolutions.get(0);
        } else {
            // Retourne null ou un message indiquant qu'il n'y a pas d'évolution supplémentaire
            return "Pas d'évolution supplémentaire";
        }
    }
    

    public List<Pokemon> getPokemonsDresseurs() {
        return new ArrayList<>(pokemons);
    }

    // Méthode pour ajouter des bonbons au dresseur
    public void ajouterBonbons(Type type, int nombre) {
        int bonbonsActuels = inventaireBonbons.getOrDefault(type, 0);
        inventaireBonbons.put(type, bonbonsActuels + nombre);
    }

    public void evoluerPokemon(Pokemon pokemon) {
        String prochaineEvolution = determinerProchaineEvolution(pokemon.getNom());
        if (prochaineEvolution != null && !prochaineEvolution.equals("Pas d'évolution supplémentaire")) {
            Type typePokemon = pokemon.getTypes().get(0); // Supposons que le type principal soit le premier de la liste
            if (inventaireBonbons.get(typePokemon) >= 5) { // Vérifie si le dresseur a au moins 5 bonbons du bon type
                inventaireBonbons.put(typePokemon, inventaireBonbons.get(typePokemon) - 5);
    
                // Créer un nouveau Pokémon avec la prochaine évolution et les mêmes attaques
                List<Attaque> attaquesActuelles = pokemon.getAttaques();
                Pokemon nouveauPokemon = genererPokemonEvolue(prochaineEvolution, attaquesActuelles);
    
                // Remplacer l'ancien Pokémon par le nouveau dans la liste des Pokémon du dresseur
                pokemons.set(pokemons.indexOf(pokemon), nouveauPokemon);
    
                System.out.println(pokemon.getNom() + " a évolué en " + prochaineEvolution + " !");
            } else {
                System.out.println("Pas assez de bonbons pour faire évoluer " + pokemon.getNom());
            }
        } else {
            System.out.println("Aucune évolution supplémentaire pour " + pokemon.getNom());
        }
    }
    
    private Pokemon genererPokemonEvolue(String nomEvolue, List<Attaque> attaquesActuelles) {
        // Utilisez les informations du Pokedex pour générer le nouveau Pokémon évolué
        Pokedex.PokemonInfo infoEvolue = pokedex.getPokemons().stream()
                .filter(p -> p.getNom().equals(nomEvolue))
                .findFirst()
                .orElse(null);
    
        if (infoEvolue != null) {
            int PC , PV, attaque, defense, vitesse;
            Random random = new Random();
    
            // Générer des statistiques en fonction de l'évolution
            if (infoEvolue.getEvolutionStage() == 2) {
                PC = PV = random.nextInt(101) + 100; // Entre 100 et 200
                attaque = defense = vitesse = random.nextInt(31) + 30; // Entre 30 et 60
            } else {
                PC = PV = random.nextInt(151) + 200; // Entre 200 et 350
                attaque = defense = vitesse = random.nextInt(91) + 60; // Entre 60 et 150
            }
    
            return new Pokemon(nomEvolue, PV, PC , attaque, defense, vitesse, new ArrayList<>(), List.of(infoEvolue.getType()), attaquesActuelles);
        }
    
        return null; // Retourner null si l'évolution n'est pas trouvée
    }
    
    public Map<Type, Integer> getInventaireBonbons() {
        return inventaireBonbons;
    }

    public void obtenirBonbonsAleatoires() {
        Random random = new Random();
        Type typeAleatoire = Type.values()[random.nextInt(Type.values().length)];
        int nombreBonbons = random.nextInt(3) + 1; // Générer un nombre aléatoire entre 1 et 3

        // Ajouter les bonbons aléatoires à l'inventaire
        inventaireBonbons.put(typeAleatoire, inventaireBonbons.getOrDefault(typeAleatoire, 0) + nombreBonbons);

        // Afficher un message indiquant le nombre et le type de bonbons reçus
        System.out.println("Vous avez reçu " + nombreBonbons + " bonbon(s) de type " + typeAleatoire);
    }
    
}
