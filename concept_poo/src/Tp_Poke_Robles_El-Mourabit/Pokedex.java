import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.Serializable;


public class Pokedex implements Serializable {
    private List<PokemonInfo> pokemons;
    private Map<String, List<String>> evolutions;
    private static final long serialVersionUID = 1L; // Numéro de version pour la sérialisation


    public Pokedex() {
        pokemons = new ArrayList<>();
        evolutions = new HashMap<>();

        // Ajouter les Pokémon avec leurs informations ici
        // Ajouter les Pokémon avec leurs informations ici
        ajouterPokemon("Bulbizarre", Type.PLANTE, 1, new ArrayList<>(List.of("Herbizarre")));
        ajouterPokemon("Herbizarre", Type.PLANTE, 2, new ArrayList<>(List.of("Florizarre")));
        ajouterPokemon("Florizarre", Type.PLANTE, 3, new ArrayList<>());

        ajouterPokemon("Salamèche", Type.FEU, 1, new ArrayList<>(List.of("Reptincel")));
        ajouterPokemon("Reptincel", Type.FEU, 2, new ArrayList<>(List.of("Dracaufeu")));
        ajouterPokemon("Dracaufeu", Type.FEU, 3, new ArrayList<>());

        ajouterPokemon("Carapuce", Type.EAU, 1, new ArrayList<>(List.of("Carabaffe")));
        ajouterPokemon("Carabaffe", Type.EAU, 2, new ArrayList<>(List.of("Tortank")));
        ajouterPokemon("Tortank", Type.EAU, 3, new ArrayList<>());

        ajouterPokemon("Chenipan", Type.INSECTE, 1, new ArrayList<>(List.of("Chrysacier")));
        ajouterPokemon("Chrysacier", Type.INSECTE, 2, new ArrayList<>(List.of("Papilusion")));
        ajouterPokemon("Papilusion", Type.INSECTE, 3, new ArrayList<>());

        ajouterPokemon("Aspicot", Type.INSECTE, 1, new ArrayList<>(List.of("Coconfort")));
        ajouterPokemon("Coconfort", Type.INSECTE, 2, new ArrayList<>(List.of("Dardargnan")));
        ajouterPokemon("Dardargnan", Type.INSECTE, 3, new ArrayList<>());

        ajouterPokemon("Roucool", Type.NORMAL, 1, new ArrayList<>(List.of("Roucoups")));
        ajouterPokemon("Roucoups", Type.NORMAL, 2, new ArrayList<>(List.of("Roucarnage")));
        ajouterPokemon("Roucarnage", Type.NORMAL, 3, new ArrayList<>());

        ajouterPokemon("Rattata", Type.NORMAL, 1, new ArrayList<>(List.of("Rattatac")));
        ajouterPokemon("Rattatac", Type.NORMAL, 2, new ArrayList<>());

        ajouterPokemon("Piafabec", Type.NORMAL, 1, new ArrayList<>(List.of("Rapasdepic")));
        ajouterPokemon("Rapasdepic", Type.NORMAL, 2, new ArrayList<>());

        ajouterPokemon("Abo", Type.POISON, 1, new ArrayList<>(List.of("Arbok")));
        ajouterPokemon("Arbok", Type.POISON, 2, new ArrayList<>());

        ajouterPokemon("Pikachu", Type.ELECTRIK, 1, new ArrayList<>(List.of("Raichu")));
        ajouterPokemon("Raichu", Type.ELECTRIK, 2, new ArrayList<>());

        ajouterPokemon("Sabelette", Type.SOL, 1, new ArrayList<>(List.of("Sablaireau")));
        ajouterPokemon("Sablaireau", Type.SOL, 2, new ArrayList<>());

        ajouterPokemon("Nidorand F", Type.POISON, 1, new ArrayList<>(List.of("Nidorina")));
        ajouterPokemon("Nidorina", Type.POISON, 2, new ArrayList<>(List.of("Nidoqueen")));
        ajouterPokemon("Nidoqueen", Type.POISON, 3, new ArrayList<>());

        ajouterPokemon("Nidorand M", Type.POISON, 1, new ArrayList<>(List.of("Nidorino")));
        ajouterPokemon("Nidorino", Type.POISON, 2, new ArrayList<>(List.of("Nidoking")));
        ajouterPokemon("Nidoking", Type.POISON, 3, new ArrayList<>());

        ajouterPokemon("Mélofée", Type.FEE, 1, new ArrayList<>(List.of("Mélodelfe")));
        ajouterPokemon("Mélodelfe", Type.FEE, 2, new ArrayList<>());

        ajouterPokemon("Goupix", Type.FEU, 1, new ArrayList<>(List.of("Feunard")));
        ajouterPokemon("Feunard", Type.FEU, 2, new ArrayList<>());

        ajouterPokemon("Rondoudou", Type.NORMAL, 1, new ArrayList<>(List.of("Grodoudou")));
        ajouterPokemon("Grodoudou", Type.NORMAL, 2, new ArrayList<>());

        ajouterPokemon("Nosferapti", Type.POISON, 1, new ArrayList<>(List.of("Nosferalto")));
        ajouterPokemon("Nosferalto", Type.POISON, 2, new ArrayList<>());

        ajouterPokemon("Mystherbe", Type.PLANTE, 1, new ArrayList<>(List.of("Ortide")));
        ajouterPokemon("Ortide", Type.PLANTE, 2, new ArrayList<>(List.of("Rafflesia")));
        ajouterPokemon("Rafflesia", Type.PLANTE, 3, new ArrayList<>());

        ajouterPokemon("Paras", Type.INSECTE, 1, new ArrayList<>(List.of("Parasect")));
        ajouterPokemon("Parasect", Type.INSECTE, 2, new ArrayList<>());

        ajouterPokemon("Mimitoss", Type.POISON, 1, new ArrayList<>(List.of("Aéromite")));
        ajouterPokemon("Aéromite", Type.POISON, 2, new ArrayList<>());

        ajouterPokemon("Taupiqueur", Type.SOL, 1, new ArrayList<>(List.of("Triopikeur")));
        ajouterPokemon("Triopikeur", Type.SOL, 2, new ArrayList<>());

        ajouterPokemon("Psykokwak", Type.EAU, 1, new ArrayList<>(List.of("Akwakwak")));
        ajouterPokemon("Akwakwak", Type.EAU, 2, new ArrayList<>());

        ajouterPokemon("Férosinge", Type.COMBAT, 1, new ArrayList<>(List.of("Colossinge")));
        ajouterPokemon("Colossinge", Type.COMBAT, 2, new ArrayList<>());

        ajouterPokemon("Caninos", Type.FEU, 1, new ArrayList<>(List.of("Arcanin")));
        ajouterPokemon("Arcanin", Type.FEU, 2, new ArrayList<>());

        ajouterPokemon("Ptitard", Type.EAU, 1, new ArrayList<>(List.of("Têtarte")));
        ajouterPokemon("Têtarte", Type.EAU, 2, new ArrayList<>(List.of("Tartard")));
        ajouterPokemon("Tartard", Type.EAU, 3, new ArrayList<>());

        ajouterPokemon("Abra", Type.PSY, 1, new ArrayList<>(List.of("Kadabra")));
        ajouterPokemon("Kadabra", Type.PSY, 2, new ArrayList<>(List.of("Alakazam")));
        ajouterPokemon("Alakazam", Type.PSY, 3, new ArrayList<>());

        ajouterPokemon("Machoc", Type.COMBAT, 1, new ArrayList<>(List.of("Machopeur")));
        ajouterPokemon("Machopeur", Type.COMBAT, 2, new ArrayList<>(List.of("Mackogneur")));
        ajouterPokemon("Mackogneur", Type.COMBAT, 3, new ArrayList<>());

        ajouterPokemon("Chétiflor", Type.PLANTE, 1, new ArrayList<>(List.of("Boustiflor")));
        ajouterPokemon("Boustiflor", Type.PLANTE, 2, new ArrayList<>(List.of("Empiflor")));
        ajouterPokemon("Empiflor", Type.PLANTE, 3, new ArrayList<>());

        ajouterPokemon("Tentacool", Type.EAU, 1, new ArrayList<>(List.of("Tentacruel")));
        ajouterPokemon("Tentacruel", Type.EAU, 2, new ArrayList<>());

        ajouterPokemon("Racaillou", Type.ROCHE, 1, new ArrayList<>(List.of("Gravalanch")));
        ajouterPokemon("Gravalanch", Type.ROCHE, 2, new ArrayList<>(List.of("Grolem")));
        ajouterPokemon("Grolem", Type.ROCHE, 3, new ArrayList<>());

        ajouterPokemon("Ponyta", Type.FEU, 1, new ArrayList<>(List.of("Galopa")));
        ajouterPokemon("Galopa", Type.FEU, 2, new ArrayList<>());

        ajouterPokemon("Ramoloss", Type.EAU, 1, new ArrayList<>(List.of("Flagadoss")));
        ajouterPokemon("Flagadoss", Type.EAU, 2, new ArrayList<>());

        ajouterPokemon("Magnéti", Type.ELECTRIK, 1, new ArrayList<>(List.of("Magnéton")));
        ajouterPokemon("Magnéton", Type.ELECTRIK, 2, new ArrayList<>());

        ajouterPokemon("Canarticho", Type.NORMAL, 1, new ArrayList<>());

        ajouterPokemon("Doduo", Type.NORMAL, 1, new ArrayList<>(List.of("Dodrio")));
        ajouterPokemon("Dodrio", Type.NORMAL, 2, new ArrayList<>());

        ajouterPokemon("Otaria", Type.EAU, 1, new ArrayList<>(List.of("Lamantine")));
        ajouterPokemon("Lamantine", Type.EAU, 2, new ArrayList<>());

        ajouterPokemon("Tadmorv", Type.POISON, 1, new ArrayList<>(List.of("Grotadmorv")));
        ajouterPokemon("Grotadmorv", Type.POISON, 2, new ArrayList<>());

        ajouterPokemon("Kokiyas", Type.EAU, 1, new ArrayList<>(List.of("Crustabri")));
        ajouterPokemon("Crustabri", Type.EAU, 2, new ArrayList<>());

        ajouterPokemon("Fantominus", Type.SPECTRE, 1, new ArrayList<>(List.of("Spectrum")));
        ajouterPokemon("Spectrum", Type.SPECTRE, 2, new ArrayList<>(List.of("Ectoplasma")));
        ajouterPokemon("Ectoplasma", Type.SPECTRE, 3, new ArrayList<>());

        ajouterPokemon("Onix", Type.ROCHE, 1, new ArrayList<>());

        ajouterPokemon("Soporifik", Type.PSY, 1, new ArrayList<>(List.of("Hypnomade")));
        ajouterPokemon("Hypnomade", Type.PSY, 2, new ArrayList<>());

        ajouterPokemon("Krabby", Type.EAU, 1, new ArrayList<>(List.of("Krabboss")));
        ajouterPokemon("Krabboss", Type.EAU, 2, new ArrayList<>());

        ajouterPokemon("Voltorbe", Type.ELECTRIK, 1, new ArrayList<>(List.of("Électrode")));
        ajouterPokemon("Électrode", Type.ELECTRIK, 2, new ArrayList<>());

        ajouterPokemon("Nœunœuf", Type.PLANTE, 1, new ArrayList<>(List.of("Noadkoko")));
        ajouterPokemon("Noadkoko", Type.PLANTE, 2, new ArrayList<>());

        ajouterPokemon("Osselait", Type.SOL, 1, new ArrayList<>(List.of("Ossatueur")));
        ajouterPokemon("Ossatueur", Type.SOL, 2, new ArrayList<>());

        ajouterPokemon("Kicklee", Type.COMBAT, 1, new ArrayList<>());
        ajouterPokemon("Tygnon", Type.COMBAT, 1, new ArrayList<>());

        ajouterPokemon("Excelangue", Type.NORMAL, 1, new ArrayList<>());

        ajouterPokemon("Smogo", Type.POISON, 1, new ArrayList<>(List.of("Smogogo")));
        ajouterPokemon("Smogogo", Type.POISON, 2, new ArrayList<>());

        ajouterPokemon("Rhinocorne", Type.SOL, 1, new ArrayList<>(List.of("Rhinoféros")));
        ajouterPokemon("Rhinoféros", Type.SOL, 2, new ArrayList<>());

        ajouterPokemon("Leveinard", Type.NORMAL, 1, new ArrayList<>());

        ajouterPokemon("Saquedeneu", Type.PLANTE, 1, new ArrayList<>());

        ajouterPokemon("Kangourex", Type.NORMAL, 1, new ArrayList<>());

        ajouterPokemon("Hypotrempe", Type.EAU, 1, new ArrayList<>(List.of("Hypocéan")));
        ajouterPokemon("Hypocéan", Type.EAU, 2, new ArrayList<>());

        ajouterPokemon("Poissirène", Type.EAU, 1, new ArrayList<>(List.of("Poissoroy")));
        ajouterPokemon("Poissoroy", Type.EAU, 2, new ArrayList<>());

        ajouterPokemon("Stari", Type.EAU, 1, new ArrayList<>(List.of("Staross")));
        ajouterPokemon("Staross", Type.EAU, 2, new ArrayList<>());

        ajouterPokemon("Arceus", Type.NORMAL, 4, new ArrayList<>());
        ajouterPokemon("Gyratina", Type.SPECTRE, 4, new ArrayList<>());





    }

    private void ajouterPokemon(String nom, Type type, int evolutionStage, List<String> evolutionsList) {
        pokemons.add(new PokemonInfo(nom, type, evolutionStage));
        evolutions.put(nom, evolutionsList);
    }

    public List<PokemonInfo> getPokemons() {
        return pokemons;
    }

    public List<PokemonInfo> getPokemonsByEvolution(int evolutionStage) {
        List<PokemonInfo> filteredPokemons = new ArrayList<>();
        for (PokemonInfo pokemon : pokemons) {
            if (pokemon.getEvolutionStage() == evolutionStage) {
                filteredPokemons.add(pokemon);
            }
        }
        return filteredPokemons;
    }

    public List<String> getEvolutions(String pokemonNom) {
        return evolutions.getOrDefault(pokemonNom, new ArrayList<>());
    }

    public List<String> getAllEvolutions(String nomPokemon) {
        // Trouver la forme de base du Pokémon
        String baseForm = trouverFormeDeBase(nomPokemon);
        
        List<String> allEvolutions = new ArrayList<>();
        allEvolutions.add(baseForm); // Ajouter le Pokémon de base
    
        // Construire la liste des évolutions à partir de la forme de base
        List<String> currentEvolutions = evolutions.getOrDefault(baseForm, new ArrayList<>());
        while (!currentEvolutions.isEmpty()) {
            String nextEvolution = currentEvolutions.get(0); // Prendre la première évolution
            allEvolutions.add(nextEvolution);
            currentEvolutions = evolutions.getOrDefault(nextEvolution, new ArrayList<>());
        }
    
        return allEvolutions;
    }
    
    private String trouverFormeDeBase(String nomPokemon) {
        for (PokemonInfo pokemon : pokemons) {
            if (pokemon.getNom().equals(nomPokemon)) {
                // Si le Pokémon est une forme de base
                if (pokemon.getEvolutionStage() == 1) {
                    return nomPokemon;
                }
            }
        }
        
        // Si le Pokémon n'est pas une forme de base, chercher sa forme précédente
        for (Map.Entry<String, List<String>> entry : evolutions.entrySet()) {
            if (entry.getValue().contains(nomPokemon)) {
                return trouverFormeDeBase(entry.getKey()); // Récursivement trouver la forme de base
            }
        }
        return nomPokemon; // Retourne le nom si aucune forme de base n'est trouvée
    }
    
    


    // Classe interne pour stocker les informations de base des Pokémon
    public static class PokemonInfo implements Serializable{
        private String nom;
        private Type type;
        private int evolutionStage;
        private static final long serialVersionUID = 1L; // Numéro de version pour la sérialisation


        public PokemonInfo(String nom, Type type, int evolutionStage) {
            this.nom = nom;
            this.type = type;
            this.evolutionStage = evolutionStage;
        }

        public String getNom() {
            return nom;
        }

        public Type getType() {
            return type;
        }

        public int getEvolutionStage() {
            return evolutionStage;
        }
    }
}


