import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClientPokemon {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner; // Déclaration de la variable
    private Dresseur dresseur;
    private Pokedex pokedex;


    public ClientPokemon(String adresse, int port, String nomDresseur) {
        try {
            socket = new Socket(adresse, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(System.in); // Initialisation de la variable
            System.out.println("Connecté au serveur sur " + adresse + ":" + port);

            // Créer un dresseur avec le nom donné
            dresseur = new Dresseur(nomDresseur);            
            pokedex = new Pokedex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void envoyerDemandeCombat(List<Pokemon> pokemons) {
        for (Pokemon pokemon : pokemons) {
            String infosCombat = convertirEnChaine(pokemon);
            out.println(infosCombat);
        }
    }
    
    private String convertirEnChaine(Pokemon pokemon) {
        StringBuilder sb = new StringBuilder();
        
        // Ajouter les détails du Pokémon
        sb.append("nomPokemon:").append(pokemon.getNom()).append(",");
        sb.append("PC:").append(pokemon.getPC()).append(",");
        sb.append("PV:").append(pokemon.getPV()).append(",");
        sb.append("attaque:").append(pokemon.getAttaque()).append(",");
        sb.append("defense:").append(pokemon.getDefense()).append(",");
        sb.append("vitesse:").append(pokemon.getVitesse()).append(",");
        for (Type type : pokemon.getTypes()) {
            sb.append(type).append(",");
        }
        sb.setLength(sb.length() - 1); // Supprimer la dernière virgule
        
        return sb.toString();
    }
    
      

    /*public void ecouterResultat() {
        try {
            System.out.println("En attente du résultat du combat...");
            String reponse = in.readLine();
            System.out.println("Résultat du combat reçu: " + reponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void ecouterResultat() {
        try {
            String reponse;
            while ((reponse = in.readLine()) != null) {
                System.out.println("Résultat du combat reçu: " + reponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afficherMenu() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Chasser Pokémon");
            System.out.println("2. Voir mes Pokémon");
            System.out.println("3. Voir mes bonbons");
            System.out.println("4. Combat");
            System.out.println("5. Obtenir des bonbons");
            System.out.println("6. Quitter");
            System.out.print("Entrez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme le retour à la ligne

            switch (choix) {
                case 1:
                    chasserPokemon();
                    break;
                case 2:
                    afficherPokemons();
                    break;
                case 3:
                    afficherBonbons();
                    break;
                case 4:
                    // Logique pour le combat
                    break;
                case 5:
                    dresseur.obtenirBonbonsAleatoires();
                    break;
                case 6:
                    System.out.println("Déconnexion...");
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private void chasserPokemon() {
        System.out.println("Vous partez à la chasse aux Pokémon...");
        dresseur.chasserPokemon(); // Utilisation de la méthode chasserPokemon de la classe Dresseur
    }

    private void afficherPokemons() {
        List<Pokemon> pokemons = dresseur.getPokemonsDresseurs();
        if (pokemons.isEmpty()) {
            System.out.println("Vous n'avez pas encore de Pokémon.");
            return;
        }

        System.out.println("Vos Pokémon:");
        for (int i = 0; i < pokemons.size(); i++) {
            Pokemon pokemon = pokemons.get(i);
            System.out.printf("%d. %s\n", i + 1, pokemon.getNom());
        }

        System.out.println("Choisissez un Pokémon pour plus d'actions ou entrez 0 pour revenir:");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Consomme le retour à la ligne

        if (choix > 0 && choix <= pokemons.size()) {
            gererPokemon(pokemons.get(choix - 1));
        }
    }

    private void afficherBonbons() {
    System.out.println("Vos bonbons par type:");
    Map<Type, Integer> inventaireBonbons = dresseur.getInventaireBonbons();
    for (Map.Entry<Type, Integer> entry : inventaireBonbons.entrySet()) {
        System.out.printf("Type %s: %d bonbons\n", entry.getKey(), entry.getValue());
        }
    }


    private void gererPokemon(Pokemon pokemon) {
        while (true) {
            System.out.printf("Gérer %s:\n", pokemon.getNom());
            System.out.println("1. Voir les statistiques");
            System.out.println("2. Tenter d'évoluer");
            System.out.println("3. Retour");
            System.out.print("Entrez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme le retour à la ligne
    
            switch (choix) {
                case 1:
                    afficherStatistiquesPokemon(pokemon);
                    break;
                case 2:
                    tenterEvolution(pokemon);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }
    
    private void afficherStatistiquesPokemon(Pokemon pokemon) {
        System.out.println("Statistiques de " + pokemon.getNom() + ":");
        System.out.println("PV: " + pokemon.getPV());
        System.out.println("PC: " + pokemon.getPC());
        System.out.println("Attaque: " + pokemon.getAttaque());
        System.out.println("Défense: " + pokemon.getDefense());
        System.out.println("Vitesse: " + pokemon.getVitesse());
        System.out.println("Types: " + pokemon.getTypes());
        // Ajouter plus de détails si nécessaire
    }
    
    private void tenterEvolution(Pokemon pokemon) {
        dresseur.evoluerPokemon(pokemon);
        // La logique d'évolution est gérée dans la méthode evoluerPokemon de la classe Dresseur
    }
    


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du dresseur : ");
        String nomDresseur = scanner.nextLine();

        ClientPokemon client = new ClientPokemon("127.0.0.1", 12345, nomDresseur);
        client.afficherMenu();
    }
}

