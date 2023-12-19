# Création du fichier README.md avec les instructions fournies par l'utilisateur

readme_content = """
### Guide d'Utilisation: Jeu Pokémon (Version Serveur-Client)

#### Présentation
Ce programme est une implémentation serveur-client d'un jeu Pokémon. Il permet à des joueurs de se connecter à un serveur, d'engager des combats Pokémon, et de gérer leurs dresseurs et leurs Pokémon.

#### Configuration Requise
- Java Runtime Environment (JRE) et Java Development Kit (JDK).
- IDE Java ou éditeur de texte pour la modification du code.

#### Composants du Programme
- `ServeurCombat.java`: Gère les connexions clients et orchestre les combats Pokémon.
- `ClientPokemon.java`: Interface client pour interagir avec le serveur, gérer les Pokémon, et combattre.
- `ClientHandler.java`: Gère la communication entre le serveur et chaque client.
- `GestionCombat.java`: Logique du combat Pokémon.
- `Save.java`, `Type.java`, `Attaque.java`, `Attaques.java`, `Pokemon.java`, `Pokedex.java`, `Dresseur.java`,
 `Bonbon.java` : Classes utilitaires pour la gestion des données du jeu.

#### Démarrage du Serveur
1. Ouvrez le fichier `ServeurCombat.java`.
2. Si nécessaire, modifiez le numéro de port dans le constructeur `ServeurCombat(int port)`.
3. Compilez et exécutez `ServeurCombat.java` pour démarrer le serveur.

#### Connexion d'un Client
1. Ouvrez le fichier `ClientPokemon.java`.
2. Modifiez l'adresse IP et le port pour correspondre à ceux du serveur dans le constructeur `ClientPokemon(String adresse, int port, String nomDresseur)`.
3. Compilez et exécutez `ClientPokemon.java` pour chaque client souhaitant se connecter.
4. Suivez les instructions à l'écran pour interagir avec le jeu.

#### Déroulement d'une Session de Jeu
1. **Connexion au Serveur**: Les clients se connectent au serveur.
2. **Menu Principal**: Les joueurs peuvent choisir de chasser des Pokémon, gérer leur équipe, engager des combats, etc.
3. **Combat**: Lorsqu'un combat est initié, le serveur gère automatiquement les échanges d'attaques entre les Pokémon des joueurs jusqu'à ce qu'un vainqueur soit déterminé.
4. **Fin du Combat**: Après un combat, les joueurs sont renvoyés au menu principal où ils peuvent choisir de continuer à jouer ou de sauvegarder et quitter.

#### Notes Importantes
- Assurez-vous que le serveur est en cours d'exécution avant de tenter de connecter un client.
- Les chemins de fichier pour la sauvegarde et le chargement des données (comme les dresseurs) peuvent nécessiter une modification selon l'environnement et le système d'exploitation utilisés.
- Toutes les interactions et la logique du jeu sont gérées par le serveur et reflétées sur les clients.

#### Dépannage
Si vous rencontrez des problèmes de connexion ou des bugs dans le jeu, vérifiez les points suivants :
- Le serveur et les clients utilisent-ils le même numéro de port ?
- L'adresse IP est-elle correctement configurée sur les clients ?
- Les exceptions ou erreurs sont-elles signalées dans la console ?

Ce guide fournit une vue d'ensemble de la configuration et de l'utilisation du jeu Pokémon version serveur-client. Pour une aide plus détaillée ou des modifications spécifiques, consultez la documentation Java ou un développeur Java expérimenté.
