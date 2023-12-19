import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionCombat {
    private Dresseur dresseur1;
    private Scanner scanner;

    public GestionCombat(Dresseur dresseur1) {
        this.dresseur1 = dresseur1;
        this.scanner = new Scanner(System.in);
    }


    public void effectuerAttaque(Pokemon attaquant, Pokemon cible, Attaque attaque) {
        double degatsDeBase = calculerDegats(attaque, cible);
        double bonusAttaque = attaquant.getAttaque() * 0.2; // 20% de la stat attaque du Pokémon attaquant
        double reductionDefense = cible.getDefense() * 0.2; // 20% de la stat défense du Pokémon cible
    
        double degatsFinaux = degatsDeBase + bonusAttaque - reductionDefense;
        degatsFinaux = Math.max(0, degatsFinaux); // Assurez-vous que les dégâts ne sont pas négatifs
    
        cible.setPV(cible.getPV() - (int) degatsFinaux);
        System.out.println(attaquant.getNom() + " attaque " + cible.getNom() + " avec " + attaque.getNom() + 
                           ", infligeant " + degatsFinaux + " dégâts.");
    }
    

    public double calculerDegats(Attaque attaque, Pokemon cible) {
        double multiplicateur = 1.0;
        for (Type typeCible : cible.getTypes()) {
            multiplicateur *= attaque.getType().efficaciteContre(typeCible);
        }
        return attaque.getPuissance() * multiplicateur;
    }


    public String demarrerCombat(Pokemon pokemon2) {
        Pokemon pokemon1 = choisirPokemon();
        if (pokemon1 == null) {
            return "Le dresseur 1 n'a pas de Pokémon valide.";
        }
    
        while (dresseur1.aDesPokemonValides() || pokemon2.getPV() > 0) {
            Attaque attaque1 = choisirAttaqueValide(pokemon1);
            effectuerAttaque(pokemon1, pokemon2, attaque1);
    
            if (pokemon2.getPV() <= 0) {
                System.out.println(pokemon2.getNom() + " est K.O. !");
                break;
            } else {
                int attaqueAleatoire = (int) (Math.random() * pokemon2.getAttaques().size());
                Attaque attaque2 = pokemon2.getAttaques().get(attaqueAleatoire);
                effectuerAttaque(pokemon2, pokemon1, attaque2);
    
                if (pokemon1.getPV() <= 0) {
                    System.out.println(pokemon1.getNom() + " est K.O. !");
                    if (dresseur1.aDesPokemonValides()){
                        pokemon1 = choisirPokemon();
                    }else{
                        break;
                    }
                }
            }
        }
    
        return determinerVainqueur();
    }

    public String determinerVainqueur() {
        if (!dresseur1.aDesPokemonValides()) {
            return "Le dresseur 1 a perdu !";
        } else {
            return "Le dresseur 1 a gagné !";
        }
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

    public Pokemon choisirPokemon() {
        if (this.dresseur1.getPokemonsDresseurs().isEmpty()) {
            return null;
        }

        System.out.println("Choisissez un Pokémon:");
        List<Integer> choixValides = new ArrayList<>();
        int i = 1;
        for (Pokemon pokemon : this.dresseur1.getPokemonsDresseurs()) {
            if (pokemon.getPV() > 0) {
                System.out.println(i + ". " + pokemon.getNom());
                choixValides.add(this.dresseur1.getPokemonsDresseurs().indexOf(pokemon));
                i++;
            }
        }

        int choix = scanner.nextInt() - 1;
        if (choix >= 0 && choix < choixValides.size()) {
            return this.dresseur1.getPokemonsDresseurs().get(choixValides.get(choix));
        } else {
            System.out.println("Choix invalide.");
            return choisirPokemon();
        }
    }

    public Pokemon choisirPokemon(int index) {
        if (index < 0 || index >= this.dresseur1.getPokemonsDresseurs().size()) {
            return null;
        }
    
        return this.dresseur1.getPokemonsDresseurs().get(index);
    }
}

