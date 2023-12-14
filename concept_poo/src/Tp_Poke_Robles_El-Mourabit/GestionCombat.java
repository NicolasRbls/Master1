import java.util.Scanner;

public class GestionCombat {
    private Dresseur dresseur1;
    private Dresseur dresseur2;
    private Scanner scanner;

    public GestionCombat(Dresseur dresseur1, Dresseur dresseur2) {
        this.dresseur1 = dresseur1;
        this.dresseur2 = dresseur2;
        this.scanner = new Scanner(System.in);
    }

    public String demarrerCombat() {
        Pokemon pokemon1 = dresseur1.choisirPokemon();
        if (pokemon1 == null) {
            return "Le dresseur 1 n'a pas de Pokémon valide.";
        }
    
        Pokemon pokemon2 = dresseur2.choisirPokemon();
        if (pokemon2 == null) {
            return "Le dresseur 2 n'a pas de Pokémon valide.";
        }
    
        while (dresseur1.aDesPokemonValides() && dresseur2.aDesPokemonValides()) {
            Attaque attaque1 = choisirAttaqueValide(pokemon1);
            effectuerAttaque(pokemon1, pokemon2, attaque1);
    
            if (pokemon2.getPV() <= 0) {
                System.out.println(pokemon2.getNom() + " est K.O. !");
                if (!dresseur2.aDesPokemonValides()) break;
                pokemon2 = dresseur2.choisirPokemon();
                while (pokemon2 == null) {
                    System.out.println("Le Pokémon choisi est K.O., choisissez-en un autre :");
                    pokemon2 = dresseur2.choisirPokemon();
                }
            } else {
                Attaque attaque2 = choisirAttaqueValide(pokemon2);
                effectuerAttaque(pokemon2, pokemon1, attaque2);
    
                if (pokemon1.getPV() <= 0) {
                    System.out.println(pokemon1.getNom() + " est K.O. !");
                    if (!dresseur1.aDesPokemonValides()) break;
                    pokemon1 = dresseur1.choisirPokemon();
                    while (pokemon1 == null) {
                        System.out.println("Le Pokémon choisi est K.O., choisissez-en un autre :");
                        pokemon1 = dresseur1.choisirPokemon();
                    }
                }
            }
        }
    
        return determinerVainqueur();
    }

    private Attaque choisirAttaqueValide(Pokemon pokemon) {
        if (pokemon == null) {
            return null;
        }

        System.out.println("Choisissez une attaque pour " + pokemon.getNom() + ":");
        int i = 1;
        for (Attaque attaque : pokemon.getAttaques()) {
            System.out.println(i + ". " + attaque.getNom());
            i++;
        }

        int choix = scanner.nextInt();
        if (choix > 0 && choix <= pokemon.getAttaques().size()) {
            return pokemon.getAttaques().get(choix - 1);
        } else {
            System.out.println("Choix invalide.");
            return choisirAttaqueValide(pokemon);
        }
    }

    private void effectuerAttaque(Pokemon attaquant, Pokemon cible, Attaque attaque) {
        double degatsDeBase = calculerDegats(attaque, cible);
        double bonusAttaque = attaquant.getAttaque() * 0.2; // 20% de la stat attaque du Pokémon attaquant
        double reductionDefense = cible.getDefense() * 0.2; // 20% de la stat défense du Pokémon cible
    
        double degatsFinaux = degatsDeBase + bonusAttaque - reductionDefense;
        degatsFinaux = Math.max(0, degatsFinaux); // Assurez-vous que les dégâts ne sont pas négatifs
    
        cible.setPV(cible.getPV() - (int) degatsFinaux);
        System.out.println(attaquant.getNom() + " attaque " + cible.getNom() + " avec " + attaque.getNom() + 
                           ", infligeant " + degatsFinaux + " dégâts.");
    }
    

    private double calculerDegats(Attaque attaque, Pokemon cible) {
        double multiplicateur = 1.0;
        for (Type typeCible : cible.getTypes()) {
            multiplicateur *= attaque.getType().efficaciteContre(typeCible);
        }
        return attaque.getPuissance() * multiplicateur;
    }

    private String determinerVainqueur() {
        if (!dresseur1.aDesPokemonValides()) {
            return dresseur2.getNom() + " gagne le combat!";
        } else if (!dresseur2.aDesPokemonValides()) {
            return dresseur1.getNom() + " gagne le combat!";
        } else {
            return "Égalité!";
        }
    }
}

