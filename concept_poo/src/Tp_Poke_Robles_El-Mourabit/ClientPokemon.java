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
    private GestionCombat combat;



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
            combat = new GestionCombat(dresseur);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientPokemon(String adresse, int port, Dresseur dresseur) {
        try {
            socket = new Socket(adresse, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(System.in); // Initialisation de la variable
            System.out.println("Connecté au serveur sur " + adresse + ":" + port);
    
            // Utiliser le dresseur fourni
            this.dresseur = dresseur;            
            pokedex = new Pokedex();
            combat = new GestionCombat(dresseur);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

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
        boolean enCours = true; // Contrôle l'exécution de la boucle du menu
        while (enCours) {
            System.out.println("Menu:");
            System.out.println("1. Chasser Pokémon");
            System.out.println("2. Voir mes Pokémon");
            System.out.println("3. Voir mes bonbons");
            System.out.println("4. Combat");
            System.out.println("5. Sauvegarder et Quitter");
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
                    Save.serialiserDresseur(dresseur);
                    demanderCombat();
                    break;
                case 5:
                    sauvegarderEtQuitter();
                    enCours = false; // Met fin à la boucle du menu
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private void chasserPokemon() {
        Pokemon pokemon = dresseur.genererPokemonAleatoire();
        System.out.println("Vous avez rencontré un " + pokemon.getNom() + " !");

        // Le joueur a la possibilité de capturer le Pokémon
        System.out.println("Voulez-vous capturer ou combattre le Pokémon (Capture/Combat)");
        String choix = scanner.nextLine();

        if (choix.equalsIgnoreCase("Capture")) {
            dresseur.ajouterPokemon(pokemon);
        } else {
            System.out.println("Le combat commence...");
            String resultat = combat.demarrerCombat(pokemon);
            if (resultat.equalsIgnoreCase("Le dresseur 1 a gagné !")) {
                dresseur.obtenirBonbonsAleatoires();
            }
        }
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
            System.out.println("3. Relâcher le Pokémon");
            System.out.println("4. Retour");
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
                    relacherPokemon(pokemon);
                    return; // Retour au menu principal après avoir relâché le Pokémon
                case 4:
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }
    
    private void relacherPokemon(Pokemon pokemon) {
        dresseur.relacherPokemon(pokemon);
    }
    
    
    private void afficherStatistiquesPokemon(Pokemon pokemon) {
        System.out.println("Statistiques de " + pokemon.getNom() + ":");
        System.out.println("PV: " + pokemon.getPV());
        System.out.println("PC: " + pokemon.getPC());
        System.out.println("Attaque: " + pokemon.getAttaque());
        System.out.println("Défense: " + pokemon.getDefense());
        System.out.println("Vitesse: " + pokemon.getVitesse());
        System.out.println("Types: " + pokemon.getTypes());
    
        // Afficher les attaques
        System.out.println("Attaques:");
        for (Attaque attaque : pokemon.getAttaques()) {
            System.out.println("- " + attaque.getNom() + " (Dégâts: " + attaque.getPuissance() + ")");
        }
    
        // Afficher l'évolution possible
        String prochaineEvolution = dresseur.determinerProchaineEvolution(pokemon.getNom());
        if (!"Pas d'évolution supplémentaire".equals(prochaineEvolution)) {
            System.out.println("Prochaine évolution: " + prochaineEvolution);
        } else {
            System.out.println("Ce Pokémon n'a pas d'évolution supplémentaire.");
        }
    }
    
    
    private void tenterEvolution(Pokemon pokemon) {
        dresseur.evoluerPokemon(pokemon);
        // La logique d'évolution est gérée dans la méthode evoluerPokemon de la classe Dresseur
    }


    private void sauvegarderEtQuitter() {
        Save.serialiserDresseur(dresseur);
        try {
            socket.close();
            System.out.println("Déconnexion...");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Erreur lors de la déconnexion : " + e.getMessage());
        }
    }

    private void ecouterInstructionsCombat() {
        try {
            String ligne;    
            while ((ligne = in.readLine()) != null) {
                System.out.println(ligne); // Afficher les messages du serveur    
                if (ligne.contains("gagne le combat") || ligne.contains("Égalité")) {
                    return; // Sortir de la méthode après le combat
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la réception des instructions du combat: " + e.getMessage());
        }
    }
    
    private void demanderCombat() {
        System.out.println("Envoi du nom du dresseur au serveur: " + dresseur.getNom());
        out.println(dresseur.getNom()); // Envoie le nom du dresseur au serveur pour initier le combat
        out.flush();
        System.out.println("En attente d'un adversaire...");
        ecouterInstructionsCombat();
        afficherMenu(); // Retour au menu principal après la fin du combat
    }
    
    
        

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu Pokémon !");
        System.out.println("1. Nouvelle partie");
        System.out.println("2. Charger une partie");
        System.out.print("Choisissez une option : ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Consomme le retour à la ligne

        String nomDresseur;
        switch (choix) {
            case 1: // Nouvelle partie
                System.out.print("Entrez le nom du dresseur : ");
                nomDresseur = scanner.nextLine();
                ClientPokemon clientNouveau = new ClientPokemon("127.0.0.1", 12345, nomDresseur);
                clientNouveau.afficherMenu();
                break;
            case 2: // Charger une partie
                Dresseur dresseurCharge = null;
                while (dresseurCharge == null) {
                    System.out.print("Entrez le nom du dresseur pour charger la partie (ou 'annuler' pour revenir) : ");
                    nomDresseur = scanner.nextLine();
                    if (nomDresseur.equalsIgnoreCase("annuler")) {
                        System.out.println("Chargement annulé.");
                        return; // Retourne au menu principal
                    }
                    dresseurCharge = Save.deserialiserDresseur(nomDresseur);
                    if (dresseurCharge == null) {
                        System.out.println("Aucune sauvegarde trouvée pour " + nomDresseur + ", veuillez réessayer.");
                    }
                }
                ClientPokemon clientCharge = new ClientPokemon("127.0.0.1", 12345, dresseurCharge);
                clientCharge.afficherMenu();
                break;            
            default:
                System.out.println("Choix invalide, veuillez réessayer.");
                break;
        }
    }
}

