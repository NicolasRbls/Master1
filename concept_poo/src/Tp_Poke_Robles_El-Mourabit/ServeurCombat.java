import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServeurCombat {
    private int port;
    private List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        ServeurCombat serveur = new ServeurCombat(12345); // Utilisez le port de votre choix
        serveur.start();
    }

    public ServeurCombat(int port) {
        this.port = port;
    }

    public synchronized void notifierMessageRecu(ClientHandler clientHandler) {
        // Vérifiez si les deux clients ont envoyé leurs informations
        if (tousLesClientsOntEnvoyeInfos()) {
            lancerCombat(clients.get(0), clients.get(1));
        }
    }
    
    private boolean tousLesClientsOntEnvoyeInfos() {
        for (ClientHandler client : clients) {
            if (client.getDernierMessageRecu() == null) {
                return false;
            }
        }
        return true;
    }
    

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré sur le port " + port);
    
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                synchronized (clients) {
                    clients.add(clientHandler);
                    /*if (clients.size() >= 2) {
                        lancerCombat(clients.get(0), clients.get(1)); // Si lancerCombat prend deux ClientHandler
                    }*/
                }
                new Thread(clientHandler).start();
                System.out.println("Client connecté: " + clientSocket.getInetAddress().getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void lancerCombat(ClientHandler client1, ClientHandler client2) {
        System.out.println("Combat en cours de lancement...");
    
        String infosDresseur1 = client1.recevoirInfosPokemon();
        String infosDresseur2 = client2.recevoirInfosPokemon();
    
        if (infosDresseur1 == null || infosDresseur2 == null) {
            System.out.println("Informations de combat incomplètes, combat non lancé.");
            return;
        }
    
        Dresseur dresseur1 = creerDresseur(infosDresseur1, "Dresseur 1");
        Dresseur dresseur2 = creerDresseur(infosDresseur2, "Dresseur 2");
    
        GestionCombat gestionCombat = new GestionCombat(dresseur1, dresseur2);
        String resultat = gestionCombat.demarrerCombat();
    
        client1.envoyerMessage(resultat);
        client2.envoyerMessage(resultat);
    }
    

    // Méthode pour envoyer le résultat du combat au client
    private void envoyerResultat(ClientHandler client, String resultat) {
        client.envoyerMessage(resultat);
    }

    public Pokemon creerPokemon(String infosPokemon) {
        try {
            String[] champs = infosPokemon.split(",");
            String nom = champs[0].split(":")[1];
            int PC = Integer.parseInt(champs[1].split(":")[1]);
            int PV = Integer.parseInt(champs[2].split(":")[1]);
            int attaque = Integer.parseInt(champs[3].split(":")[1]);
            int defense = Integer.parseInt(champs[4].split(":")[1]);
            int vitesse = Integer.parseInt(champs[5].split(":")[1]);
    
            List<Type> types = new ArrayList<>();
            for (int i = 6; i < champs.length; i++) {
                types.add(Type.valueOf(champs[i].toUpperCase()));
            }
    
            // Créez une liste d'attaques pour ce Pokémon
            List<Attaque> attaques = genererAttaquesPourPokemon(types);
    
            return new Pokemon(nom, PV, PC, attaque, defense, vitesse, new ArrayList<>(), types, attaques);
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du Pokémon: " + e.getMessage());
            return null;
        }
    }
    
    private Dresseur creerDresseur(String infosDresseur, String nomDresseur) {
        Dresseur dresseur = new Dresseur(nomDresseur);
        String[] infosPokemons = infosDresseur.split(";"); // Supposant que chaque Pokémon est séparé par ";"
        
        for (String infosPokemon : infosPokemons) {
            Pokemon pokemon = creerPokemon(infosPokemon);
            if (pokemon != null) {
                dresseur.ajouterPokemon(pokemon);
            }
        }
        
        return dresseur;
    }    

    // Méthode pour générer des attaques basées sur les types du Pokémon
    private List<Attaque> genererAttaquesPourPokemon(List<Type> types) {
        List<Attaque> attaques = new ArrayList<>();
        for (Type type : types) {
            attaques.addAll(Attaques.getAttaquesParType(type));
        }
        return attaques;
    }
    
    
    
}
