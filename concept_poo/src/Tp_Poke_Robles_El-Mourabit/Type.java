import java.util.ArrayList;
import java.util.List;

public enum Type {
    NORMAL("Normal"),
    COMBAT("Combat"),
    VOL("Vol"),
    POISON("Poison"),
    SOL("Sol"),
    ROCHE("Roche"),
    INSECTE("Insecte"),
    SPECTRE("Spectre"),
    ACIER("Acier"),
    FEU("Feu"),
    EAU("Eau"),
    PLANTE("Plante"),
    ELECTRIK("Électrik"),
    GLACE("Glace"),
    PSY("Psy"),
    DRAGON("Dragon"),
    TENEBRES("Ténèbres"),
    FEE("Fée"),
    ;

    private String nom;

    Type(String nom) {
        this.nom = nom;
    }

    public double efficaciteContre(Type autreType) {
        switch (this) {
            case NORMAL:
                return (autreType == Type.SPECTRE) ? 0.0 :
                       (autreType == Type.COMBAT) ? 2.0 :
                       1.0;
            case COMBAT:
                return (autreType == Type.NORMAL || autreType == Type.ROCHE || autreType == Type.ACIER || autreType == Type.GLACE || autreType == Type.TENEBRES) ? 2.0 :
                       (autreType == Type.VOL || autreType == Type.POISON || autreType == Type.PSY || autreType == Type.INSECTE || autreType == Type.FEE) ? 0.5 :
                       (autreType == Type.SPECTRE) ? 0.0 :
                       1.0;
            case VOL:
                return (autreType == Type.COMBAT || autreType == Type.INSECTE || autreType == Type.PLANTE) ? 0.5 :
                       (autreType == Type.ELECTRIK || autreType == Type.ROCHE || autreType == Type.ACIER) ? 2.0 :
                       1.0;
            case POISON:
                return (autreType == Type.PLANTE || autreType == Type.FEE) ? 2.0 :
                       (autreType == Type.POISON || autreType == Type.SOL || autreType == Type.ROCHE || autreType == Type.SPECTRE) ? 0.5 :
                       (autreType == Type.ACIER) ? 0.0 :
                       1.0;
            case SOL:
                return (autreType == Type.FEU || autreType == Type.ELECTRIK || autreType == Type.POISON || autreType == Type.ROCHE || autreType == Type.ACIER) ? 2.0 :
                       (autreType == Type.PLANTE || autreType == Type.INSECTE) ? 0.5 :
                       (autreType == Type.VOL) ? 0.0 :
                       1.0;
            case ROCHE:
                return (autreType == Type.FEU || autreType == Type.GLACE || autreType == Type.VOL || autreType == Type.INSECTE) ? 2.0 :
                       (autreType == Type.COMBAT || autreType == Type.SOL || autreType == Type.ACIER) ? 0.5 :
                       1.0;
            case INSECTE:
                return (autreType == Type.PLANTE || autreType == Type.PSY || autreType == Type.TENEBRES) ? 2.0 :
                       (autreType == Type.FEU || autreType == Type.COMBAT || autreType == Type.POISON || autreType == Type.VOL || autreType == Type.SPECTRE || autreType == Type.ACIER || autreType == Type.FEE) ? 0.5 :
                       1.0;
            case SPECTRE:
                return (autreType == Type.PSY || autreType == Type.SPECTRE) ? 2.0 :
                       (autreType == Type.NORMAL) ? 0.0 :
                       1.0;
            case ACIER:
                return (autreType == Type.GLACE || autreType == Type.ROCHE || autreType == Type.FEE) ? 2.0 :
                       (autreType == Type.FEU || autreType == Type.EAU || autreType == Type.ACIER || autreType == Type.ELECTRIK) ? 0.5 :
                       1.0;
            case FEU:
                return (autreType == Type.PLANTE || autreType == Type.GLACE || autreType == Type.INSECTE || autreType == Type.ACIER) ? 2.0 :
                       (autreType == Type.FEU || autreType == Type.EAU || autreType == Type.ROCHE || autreType == Type.DRAGON) ? 0.5 :
                       1.0;
            case EAU:
                return (autreType == Type.FEU || autreType == Type.SOL || autreType == Type.ROCHE) ? 2.0 :
                       (autreType == Type.EAU || autreType == Type.PLANTE || autreType == Type.DRAGON) ? 0.5 :
                       1.0;
            case PLANTE:
                return (autreType == Type.EAU || autreType == Type.SOL || autreType == Type.ROCHE || autreType == Type.VOL) ? 2.0 :
                       (autreType == Type.FEU || autreType == Type.INSECTE || autreType == Type.POISON || autreType == Type.ACIER || autreType == Type.FEE) ? 0.5 :
                       1.0;
            case ELECTRIK:
                return (autreType == Type.EAU || autreType == Type.VOL) ? 2.0 :
                       (autreType == Type.ELECTRIK || autreType == Type.ACIER) ? 0.5 :
                       1.0;
            case GLACE:
                return (autreType == Type.SOL || autreType == Type.ROCHE || autreType == Type.FEU || autreType == Type.COMBAT) ? 2.0 :
                       (autreType == Type.GLACE) ? 0.5 :
                       1.0;
            case PSY:
                return (autreType == Type.INSECTE || autreType == Type.SPECTRE || autreType == Type.TENEBRES) ? 2.0 :
                       (autreType == Type.COMBAT || autreType == Type.POISON) ? 0.5 :
                       1.0;
            case DRAGON:
                return (autreType == Type.DRAGON) ? 2.0 :
                       (autreType == Type.ACIER) ? 0.5 :
                       1.0;
            case TENEBRES:
                return (autreType == Type.PSY || autreType == Type.SPECTRE) ? 2.0 :
                       (autreType == Type.COMBAT || autreType == Type.TENEBRES || autreType == Type.FEE) ? 0.5 :
                       1.0;
            case FEE:
                return (autreType == Type.TENEBRES || autreType == Type.ACIER) ? 2.0 :
                       (autreType == Type.FEU || autreType == Type.POISON || autreType == Type.ACIER) ? 0.5 :
                       1.0;
            default:
                return 1.0;
        }
    }

    public String getNom() {
        return nom;
    }
}