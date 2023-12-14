import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pokemon {
    private String nom;
    private int PC;
    private int PV;
    private int attaque;
    private int defense;
    private int vitesse;
    private List<Pokemon> evolutions;
    private List<Type> types;
    private List<Attaque> attaques;

    // Constructeur
    public Pokemon(String nom, int PV, int PC, int attaque, int defense, int vitesse, List<Pokemon> evolutions, List<Type> types) {
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
    public Pokemon(String nom, int PV, int PC, int attaque, int defense, int vitesse, List<Pokemon> evolutions, List<Type> types , List<Attaque> attaques) {
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

    public int getAttaque() {
        return attaque;
    }

    public int getDefense() {
        return defense;
    }

    public int getVitesse() {
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
     private List<Attaque> genererAttaquesAleatoires(List<Type> types) {
        List<Attaque> attaquesGenerees = new ArrayList<>();
        Random random = new Random();
        for (Type type : types) {
            List<Attaque> attaquesParType = Attaques.getAttaquesParType(type);
            if (!attaquesParType.isEmpty()) {
                attaquesGenerees.add(attaquesParType.get(random.nextInt(attaquesParType.size())));
            }
        }
        return attaquesGenerees;
    }
    

    public List<Attaque> getAttaques() {
        return attaques;
    }

}