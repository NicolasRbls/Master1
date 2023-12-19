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

    // Attaques de Type GLACE
    public static Attaque gelee = new Attaque("Gelée", 50, Type.GLACE);
    public static Attaque blizzard = new Attaque("Blizzard", 110, Type.GLACE);
    public static Attaque ventGlacial = new Attaque("Vent Glacial", 55, Type.GLACE);
    public static Attaque eclatsGlace = new Attaque("Éclats Glace", 40, Type.GLACE);

    // Attaques de Type COMBAT
    public static Attaque poingKarate = new Attaque("Poing-Karaté", 50, Type.COMBAT);
    public static Attaque balayette = new Attaque("Balayette", 60, Type.COMBAT);
    public static Attaque coupCroix = new Attaque("Coup-Croix", 100, Type.COMBAT);
    public static Attaque dynamopoing = new Attaque("Dynamopoing", 100, Type.COMBAT);

    // Attaques de Type PSY
    public static Attaque chocMental = new Attaque("Choc Mental", 50, Type.PSY);
    public static Attaque rafalePsy = new Attaque("Rafale Psy", 65, Type.PSY);
    public static Attaque psyko = new Attaque("Psyko", 90, Type.PSY);
    public static Attaque psykoBoom = new Attaque("Soin", 120, Type.PSY);   
    // Attaques de Type SOL
    public static Attaque seisme = new Attaque("Séisme", 100, Type.SOL);
    public static Attaque pietisol = new Attaque("Piétisol", 60, Type.SOL);
    public static Attaque tunnel = new Attaque("Tunnel", 80, Type.SOL);
    public static Attaque tomberoche = new Attaque("Tomberoche", 60, Type.SOL);

    // Attaques de Type VOL
    public static Attaque pique = new Attaque("Piqué", 40, Type.VOL);
    public static Attaque cyclone = new Attaque("Cyclone", 40, Type.VOL);
    public static Attaque rapace = new Attaque("Rapace", 120, Type.VOL);
    public static Attaque ventArriere = new Attaque("Vent Arrière", 20, Type.VOL); 

    // Attaques de Type POISON
    public static Attaque dardVenin = new Attaque("Dard-Venin", 15, Type.POISON);
    public static Attaque bombBeurk = new Attaque("Bomb-Beurk", 90, Type.POISON);
    public static Attaque gazToxik = new Attaque("Gaz Toxik", 55, Type.POISON); 
    public static Attaque detritus = new Attaque("Détritus", 65, Type.POISON);

    // Attaques de Type INSECTE
    public static Attaque dardNuee = new Attaque("Dard-Nuée", 25, Type.INSECTE);
    public static Attaque trancheHerbeInsecte = new Attaque("Tranch'Herbe", 55, Type.INSECTE);
    public static Attaque essaim = new Attaque("Essaim", 90, Type.INSECTE);
    public static Attaque piqure = new Attaque("Piqûre", 60, Type.INSECTE);

    // Attaques de Type ROCHE
    public static Attaque jetPierres = new Attaque("Jet-Pierres", 50, Type.ROCHE);
    public static Attaque pouvoirAntique = new Attaque("Pouvoir Antique", 60, Type.ROCHE);
    public static Attaque lameDeRoc = new Attaque("Lame de Roc", 100, Type.ROCHE);
    public static Attaque tempeteDeSable = new Attaque("Tempête de Sable", 90, Type.ROCHE); 

    // Attaques de Type SPECTRE
    public static Attaque ballOmbre = new Attaque("Ball'Ombre", 80, Type.SPECTRE);
    public static Attaque griffeOmbre = new Attaque("Griffe Ombre", 70, Type.SPECTRE);
    public static Attaque chatiment = new Attaque("Chatiment", 65, Type.SPECTRE);
    public static Attaque ombreNocturne = new Attaque("Ombre Nocturne", 50, Type.SPECTRE); 

    // Attaques de Type NORMAL
    public static Attaque charge = new Attaque("Charge", 40, Type.NORMAL);
    public static Attaque coupDeBoule = new Attaque("Coup de Boule", 70, Type.NORMAL);
    public static Attaque griffe = new Attaque("Griffe", 40, Type.NORMAL);
    public static Attaque morsure = new Attaque("Morsure", 60, Type.NORMAL);

    // Attaques de Type DRAGON
    public static Attaque dracoGriffe = new Attaque("Draco-Griffe", 80, Type.DRAGON);
    public static Attaque colere = new Attaque("Colère", 120, Type.DRAGON);
    public static Attaque dracoSouffle = new Attaque("Draco-Souffle", 60, Type.DRAGON);
    public static Attaque queueDeDragon = new Attaque("Queue de Dragon", 60, Type.DRAGON);

    // Attaques de Type ACIER
    public static Attaque griffeAcier = new Attaque("Griffe Acier", 70, Type.ACIER);
    public static Attaque poingMétéore = new Attaque("Poing Météore", 90, Type.ACIER);
    public static Attaque lameDeFer = new Attaque("Lame de Fer", 80, Type.ACIER);
    public static Attaque teteDeFer = new Attaque("Tête de Fer", 100, Type.ACIER);

    // Attaques de Type TENEBRES
    public static Attaque morsureNoire = new Attaque("Morsure Noire", 60, Type.TENEBRES);
    public static Attaque griffObscure = new Attaque("Griff Obscure", 70, Type.TENEBRES);
    public static Attaque machouille = new Attaque("Mâchouille", 80, Type.TENEBRES);
    public static Attaque assurance = new Attaque("Assurance", 60, Type.TENEBRES);

    // Attaques de Type FEU
    public static Attaque incendie = new Attaque("Incendie", 75, Type.FEU);
    public static Attaque souffleFeu = new Attaque("Souffle Feu", 60, Type.FEU);

    // Attaques de Type EAU
    public static Attaque pluieVive = new Attaque("Pluie Vive", 70, Type.EAU);
    public static Attaque vagueOceane = new Attaque("Vague Océane", 85, Type.EAU);

    // Attaques de Type PLANTE
    public static Attaque feuilleMagik = new Attaque("Feuille Magik", 60, Type.PLANTE);
    public static Attaque gigaSangsue = new Attaque("Giga-Sangsue", 75, Type.PLANTE);

    // Attaques de Type ELECTRIK
    public static Attaque chocStatik = new Attaque("Choc Statik", 70, Type.ELECTRIK);
    public static Attaque rayonCharge = new Attaque("Rayon Chargé", 50, Type.ELECTRIK);

    // Attaques de Type GLACE
    public static Attaque avalancheFroide = new Attaque("Avalanche Froide", 65, Type.GLACE);
    public static Attaque griffeGel = new Attaque("Griffe Gel", 55, Type.GLACE);

    // Attaques de Type COMBAT
    public static Attaque coupPoing = new Attaque("Coup de Poing", 75, Type.COMBAT);
    public static Attaque uppercut = new Attaque("Uppercut", 70, Type.COMBAT);

    // Attaques de Type PSY
    public static Attaque rayonPsyche = new Attaque("Rayon Psyché", 65, Type.PSY);
    public static Attaque forcePsy = new Attaque("Force Psy", 70, Type.PSY);

    // Attaques de Type SOL
    public static Attaque coupDeBoue = new Attaque("Coup de Boue", 65, Type.SOL);
    public static Attaque tremblement = new Attaque("Tremblement", 75, Type.SOL);

    // Attaques de Type VOL
    public static Attaque ventViolent = new Attaque("Vent Violent", 70, Type.VOL);
    public static Attaque aileDacier = new Attaque("Aile d'Acier", 70, Type.VOL);

    // Attaques de Type POISON
    public static Attaque piqueToxique = new Attaque("Pique Toxique", 70, Type.POISON);
    public static Attaque vapeurToxique = new Attaque("Vapeur Toxique", 65, Type.POISON);

    // Attaques de Type INSECTE
    public static Attaque crochetVenin = new Attaque("Crochet Venin", 70, Type.INSECTE);
    public static Attaque poudreToxique = new Attaque("Poudre Toxique", 65, Type.INSECTE);

    // Attaques de Type ROCHE
    public static Attaque fracasRoc = new Attaque("Fracas Roc", 75, Type.ROCHE);
    public static Attaque coupDePierre = new Attaque("Coup de Pierre", 65, Type.ROCHE);

    // Attaques de Type SPECTRE
    public static Attaque criEffroyable = new Attaque("Cri Effroyable", 70, Type.SPECTRE);
    public static Attaque lameSpectrale = new Attaque("Lame Spectrale", 70, Type.SPECTRE);

    // Attaques de Type DRAGON
    public static Attaque souffleDragon = new Attaque("Souffle Dragon", 75, Type.DRAGON);
    public static Attaque queueDragon = new Attaque("Queue de Dragon", 60, Type.DRAGON);

    // Attaques de Type ACIER
    public static Attaque coupMetal = new Attaque("Coup de Métal", 70, Type.ACIER);
    public static Attaque griffeAceree = new Attaque("Griffe Acérée", 65, Type.ACIER);

    // Attaques de Type TENEBRES
    public static Attaque regardTenebreux = new Attaque("Regard Ténébreux", 65, Type.TENEBRES);
    public static Attaque crocsOmbre = new Attaque("Crocs Ombre", 70, Type.TENEBRES);

    // Attaques de Type FEE     
    public static Attaque CoupDeFleur = new Attaque("Coup de Fleur", 60, Type.FEE);
    public static Attaque RayonGemme = new Attaque("Rayon Gemme", 75, Type.FEE);
    public static Attaque BalletFleur = new Attaque("Ballet Fleur", 90, Type.FEE);
    public static Attaque AttaqueSolaire = new Attaque("Attaque Solaire", 105, Type.FEE);
    public static Attaque CoupDeCoeur = new Attaque("Coup de Coeur", 80, Type.FEE);
    public static Attaque PluieDeFleurs = new Attaque("Pluie de Fleurs", 95, Type.FEE);
    public static Attaque CoupDeGrace = new Attaque("Coup de Grâce", 110, Type.FEE);
    public static Attaque LueurCeleste = new Attaque("Lueur Céleste", 125, Type.FEE);
    public static Attaque CoupEnchanteur = new Attaque("Coup Enchanteur", 90, Type.FEE);
    public static Attaque CoupDeFee = new Attaque("Coup de Fée", 80, Type.FEE);


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

