import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attaques {
    // Attaques de Type FEU
    public static Attaque flammeche = new Attaque("Flammèche", 40, Type.FEU);
    public static Attaque lanceFlammes = new Attaque("Lance-Flammes", 90, Type.FEU);
    public static Attaque roueDeFeu = new Attaque("Roue de Feu", 60, Type.FEU);
    public static Attaque deflagration = new Attaque("Déflagration", 110, Type.FEU);

    // Attaques de Type EAU
    public static Attaque pistoletAEau = new Attaque("Pistolet à Eau", 40, Type.EAU);
    public static Attaque hydrocanon = new Attaque("Hydrocanon", 110, Type.EAU);
    public static Attaque surf = new Attaque("Surf", 90, Type.EAU);
    public static Attaque cascade = new Attaque("Cascade", 80, Type.EAU);

    // Attaques de Type PLANTE
    public static Attaque tranchHerbe = new Attaque("Tranch'Herbe", 40, Type.PLANTE);
    public static Attaque lanceSoleil = new Attaque("Lance-Soleil", 120, Type.PLANTE);
    public static Attaque fouetLianes = new Attaque("Fouet Lianes", 45, Type.PLANTE);
    public static Attaque tempeteFlorale = new Attaque("Tempête Florale", 90, Type.PLANTE);

    // Attaques de Type ELECTRIK
    public static Attaque tonnerre = new Attaque("Tonnerre", 90, Type.ELECTRIK);
    public static Attaque etincelle = new Attaque("Étincelle", 65, Type.ELECTRIK);
    public static Attaque fatalFoudre = new Attaque("Fatal-Foudre", 110, Type.ELECTRIK);
    public static Attaque eclair = new Attaque("Éclair", 40, Type.ELECTRIK);


    private static Map<Type, List<Attaque>> attaquesParType = new HashMap<>();

    static {
        // Initialiser le Map avec tous les types
        for (Type type : Type.values()) {
            attaquesParType.put(type, new ArrayList<>());
        }

        // Parcourir les champs de cette classe pour ajouter les attaques
        for (Field field : Attaques.class.getDeclaredFields()) {
            try {
                Object value = field.get(null);
                if (value instanceof Attaque) {
                    Attaque attaque = (Attaque) value;
                    attaquesParType.get(attaque.getType()).add(attaque);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Attaque> getAttaquesParType(Type type) {
        return attaquesParType.getOrDefault(type, new ArrayList<>());
    }
}

