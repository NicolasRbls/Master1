import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientPokemon {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner; // Déclaration de la variable
    private Dresseur dresseur;

    public ClientPokemon(String adresse, int port, String nomDresseur) {
        try {
            socket = new Socket(adresse, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(System.in); // Initialisation de la variable
            System.out.println("Connecté au serveur sur " + adresse + ":" + port);

            // Créer un dresseur avec le nom donné
            dresseur = new Dresseur(nomDresseur);
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
            System.out.println("1. Chasser Pokémon (Non implémenté)");
            System.out.println("2. Combat");
            System.out.println("3. Quitter");
            System.out.print("Entrez votre choix : ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("Chasser Pokémon n'est pas encore implémenté.");
                    break;
                case 2:
                    List<Pokemon> pokemons = new ArrayList<>();
                    // Ajoutez ici les Pokémon créés à la main
                    pokemons.add(new Pokemon("Pikachu", 100, 50, 55, 40, 90, new ArrayList<>(), List.of(Type.ELECTRIK)));
                    envoyerDemandeCombat(pokemons);
                    ecouterResultat();
                    break;
                case 3:
                    System.out.println("Déconnexion...");
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return; // Quitter le menu
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du dresseur : ");
        String nomDresseur = scanner.nextLine();

        ClientPokemon client = new ClientPokemon("127.0.0.1", 12345, nomDresseur);
        client.afficherMenu();
    }
}

