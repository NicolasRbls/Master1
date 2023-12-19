import java.io.Serializable;

public class Attaque implements Serializable {
    private String nom;
    private int puissance;
    private Type type;
    private static final long serialVersionUID = 1L;


    public Attaque(String nom, int puissance, Type type) {
        this.nom = nom;
        this.puissance = puissance;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public int getPuissance() {
        return puissance;
    }

    public Type getType() {
        return type;
    }
}

