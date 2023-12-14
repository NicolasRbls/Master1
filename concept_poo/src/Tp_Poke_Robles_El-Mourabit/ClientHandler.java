import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ServeurCombat serveur;
    private PrintWriter out;
    private BufferedReader in;
    private String nomDresseur;
    private List<Pokemon> pokemons = new ArrayList<>();
    private String dernierMessageRecu; // Attribut pour stocker le dernier message


    public ClientHandler(Socket socket, ServeurCombat serveur) {
        this.clientSocket = socket;
        this.serveur = serveur;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                dernierMessageRecu = inputLine; // Mettre à jour le dernier message reçu
                System.out.println("Données reçues du client: " + inputLine);
                traiterMessage(inputLine);
                // ... (Logique de traitement des messages)
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fermerConnexion();
        }
    }


    private void traiterMessage(String message) {
        // Extraction du nom du Dresseur (si disponible)
        if (nomDresseur == null) {
            nomDresseur = message;
            return;
        }

        // Traitement des informations de Pokémon
        Pokemon pokemon = serveur.creerPokemon(message);
        if (pokemon != null) {
            pokemons.add(pokemon);
        }
    }

    public Dresseur getDresseur() {
        Dresseur dresseur = new Dresseur(nomDresseur);
        for (Pokemon pokemon : pokemons) {
            dresseur.ajouterPokemon(pokemon);
        }
        return dresseur;
    }

    public void envoyerMessage(String message) {
        out.println(message);
    }

    // Méthode pour recevoir les informations du Pokémon envoyées par le client
    public String recevoirInfosPokemon() {
        try {
            String infosPokemon = in.readLine();
            return infosPokemon;
        } catch (IOException e) {
            System.out.println("Erreur lors de la réception des informations du Pokémon: " + e.getMessage());
            return null;
        }
    }

    public String getDernierMessageRecu() {
        return dernierMessageRecu; // Retourner le dernier message reçu
    }

    private void fermerConnexion() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
