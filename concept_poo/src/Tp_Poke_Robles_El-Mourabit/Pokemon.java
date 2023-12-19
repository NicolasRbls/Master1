import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.io.Serializable;


public class Pokemon  implements Serializable{
    private String nom;
    private int PC;
    private int PV;
    private long attaque;
    private long defense;
    private long vitesse;
    private List<Pokemon> evolutions;
    private List<Type> types;
    private List<Attaque> attaques;
    private static final long serialVersionUID = 1L; // Numéro de version pour la sérialisation

    // Constructeur
    public Pokemon(String nom, int PV, int PC, long attaque, long defense, long vitesse, List<Pokemon> evolutions, List<Type> types) {
        this.nom = nom;
        this.PV = PV;
        this.PC = PC;
        this.attaque = attaque;
        this.defense = defense;
        this.vitesse = vitesse;
        this.evolutions = evolutions;
        this.types = types;
        this.attaques = genererAttaquesAleatoires(types);
    }

    // Constructeur
    public Pokemon(String nom, int PV, int PC, long attaque, long defense, long vitesse, List<Pokemon> evolutions, List<Type> types , List<Attaque> attaques) {
        this.nom = nom;
        this.PV = PV;
        this.PC = PC;
        this.attaque = attaque;
        this.defense = defense;
        this.vitesse = vitesse;
        this.evolutions = evolutions;
        this.types = types;
        this.attaques = attaques;
    }

    public String getNom() {
        return nom;
    }

    public int getPC() {
        return PC;
    }

    public int getPV() {
        return PV;
    }

    public long getAttaque() {
        return attaque;
    }

    public long getDefense() {
        return defense;
    }

    public long getVitesse() {
        return vitesse;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPC(int pC) {
        PC = pC;
    }

    public void setPV(int pV) {
        PV = pV;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public void setEvolutions(List<Pokemon> evolutions) {
        this.evolutions = evolutions;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }


     // Générer aléatoirement des attaques en fonction des types du Pokémon
    public List<Attaque> genererAttaquesAleatoires(List<Type> types) {
        List<Attaque> attaquesGenerees = new ArrayList<>();
        Random random = new Random();

        for (Type type : types) {
            List<Attaque> attaquesParType = Attaques.getAttaquesParType(type);
            Set<Attaque> attaquesUniques = new HashSet<>();

            while (attaquesUniques.size() < 4 && attaquesParType.size() >= 4) {
                Attaque attaqueAleatoire = attaquesParType.get(random.nextInt(attaquesParType.size()));
                attaquesUniques.add(attaqueAleatoire);
            }

            attaquesGenerees.addAll(attaquesUniques);
        }

        // Réduire la liste à quatre attaques si plus ont été générées
        while (attaquesGenerees.size() > 4) {
            attaquesGenerees.remove(random.nextInt(attaquesGenerees.size()));
        }

        return attaquesGenerees;
    }

    

    public List<Attaque> getAttaques() {
        return attaques;
    }

   

}