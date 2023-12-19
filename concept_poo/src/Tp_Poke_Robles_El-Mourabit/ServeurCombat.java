import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServeurCombat {
    private int port;
    private List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());
    private ExecutorService executorService;
    private ExecutorService pool = Executors.newCachedThreadPool();


    public ServeurCombat(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        ServeurCombat serveur = new ServeurCombat(12345); // Utilisez le port de votre choix
        serveur.start();
    }

    public synchronized void notifierMessageRecu(ClientHandler clientHandler) {
        // Ajoutez le client à la liste s'il n'y est pas déjà
        if (!clients.contains(clientHandler)) {
            clients.add(clientHandler);
        }
    
        // Attendez que le deuxième client ait envoyé son message
        while (clients.size() < 2) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    
        // Vérifiez si les deux clients ont envoyé leurs informations
        if (clients.size() >= 2 && tousLesClientsOntEnvoyeInfos()) {
            System.out.println("Client connecté: " + clientHandler.getNomDresseur());
            lancerCombat(clients.get(0), clients.get(1));
        }
    }
    
    
    private boolean tousLesClientsOntEnvoyeInfos() {
        for (ClientHandler client : clients) {
            if (client.getNomDresseur() == null) {
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
                }
                pool.execute(clientHandler); // Utiliser le pool pour exécuter le clientHandler
                System.out.println("Client connecté: " + clientSocket.getInetAddress().getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void lancerCombat(ClientHandler client1, ClientHandler client2) {
        Dresseur dresseur1 = chargerDresseur(client1.getNomDresseur());
        Dresseur dresseur2 = chargerDresseur(client2.getNomDresseur());
    
        if (dresseur1 == null || dresseur2 == null) {
            client1.envoyerMessage("Erreur: Impossible de charger les dresseurs.");
            client2.envoyerMessage("Erreur: Impossible de charger les dresseurs.");
            return;
        }

        System.out.println("Préparation du combat entre " + client1.getNomDresseur() + " et " + client2.getNomDresseur());
    
        // Combat automatique
        while (dresseur1.aDesPokemonValides() && dresseur2.aDesPokemonValides()) {
            Pokemon pokemon1 = choisirPokemonAutomatique(dresseur1);
            Pokemon pokemon2 = choisirPokemonAutomatique(dresseur2);
    
            // Envoi de l'état du combat
            client1.envoyerMessage("Votre Pokémon: " + pokemon1.getNom());
            client2.envoyerMessage("Votre Pokémon: " + pokemon2.getNom());
    
            while (pokemon1.getPV() > 0 && pokemon2.getPV() > 0) {
                effectuerAttaqueAutomatique(pokemon1, pokemon2, client1, client2);
                effectuerAttaqueAutomatique(pokemon2, pokemon1, client2, client1);
            }
        }
    
        // Envoyer le résultat
        String resultat = determinerVainqueur(dresseur1, dresseur2);
        client1.envoyerMessage(resultat);
        client2.envoyerMessage(resultat);
    }


    private Dresseur chargerDresseur(String nomDresseur) {
        return Save.deserialiserDresseur(nomDresseur); 
    }

    

    // Méthode pour envoyer le résultat du combat au client
    private void envoyerResultat(ClientHandler client, String resultat) {
        client.envoyerMessage(resultat);
    }

    private Pokemon choisirPokemonAutomatique(Dresseur dresseur) {
        // Choix du premier Pokémon valide
        for (Pokemon pokemon : dresseur.getPokemonsDresseurs()) {
            if (pokemon.getPV() > 0) {
                return pokemon;
            }
        }
        return null; // Aucun Pokémon valide
    }

    private void effectuerAttaqueAutomatique(Pokemon attaquant, Pokemon cible, ClientHandler clientAttaquant, ClientHandler clientCible) {
    // Choisir une attaque aléatoirement
    int indexAttaque = new Random().nextInt(attaquant.getAttaques().size());
    Attaque attaque = attaquant.getAttaques().get(indexAttaque);

    // Calculer les dégâts en tenant compte des statistiques
    double degatsDeBase = calculerDegats(attaque, cible);
    double bonusAttaque = attaquant.getAttaque() * 0.2; // 20% de la stat attaque du Pokémon attaquant
    double reductionDefense = cible.getDefense() * 0.2; // 20% de la stat défense du Pokémon cible

    double degatsFinaux = degatsDeBase + bonusAttaque - reductionDefense;
    degatsFinaux = Math.max(0, degatsFinaux); // Assurez-vous que les dégâts ne sont pas négatifs

    // Appliquer les dégâts
    cible.setPV(cible.getPV() - (int) degatsFinaux);

    // Envoi des détails de l'attaque
    String message = attaquant.getNom() + " attaque " + cible.getNom() + " avec " + attaque.getNom() + 
                     ", infligeant " + degatsFinaux + " dégâts.";
    clientAttaquant.envoyerMessage(message);
    clientCible.envoyerMessage(message);
}

    
    public double calculerDegats(Attaque attaque, Pokemon cible) {
        double multiplicateur = 1.0;
        for (Type typeCible : cible.getTypes()) {
            multiplicateur *= attaque.getType().efficaciteContre(typeCible);
        }
        return attaque.getPuissance() * multiplicateur;
    }
    
    private String determinerVainqueur(Dresseur dresseur1, Dresseur dresseur2) {
        if (!dresseur1.aDesPokemonValides()) {
            return dresseur2.getNom() + " gagne le combat!";
        } else if (!dresseur2.aDesPokemonValides()) {
            return dresseur1.getNom() + " gagne le combat!";
        } else {
            return "Égalité!";
        }
    }

    
}
