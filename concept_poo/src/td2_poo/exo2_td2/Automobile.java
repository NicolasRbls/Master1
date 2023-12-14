package td2_poo.exo2_td2;

public abstract class Automobile {
    private String modèle;
    private int puissance;
    private String couleur;
    private int espace;

    public Automobile(String modèle, int puissance, String couleur, int espace) {
        this.modèle = modèle;
        this.puissance = puissance;
        this.couleur = couleur;
        this.espace = espace;
    }

    public void afficherCaractéristiques() {
        System.out.println("Modèle: " + modèle);
        System.out.println("Puissance: " + puissance + " chevaux");
        System.out.println("Couleur: " + couleur);
        System.out.println("Espace: " + espace + " litres");
    }
}
