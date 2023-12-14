package td2_poo.exo2_td2;

abstract class Scooter {
    private String modèle;
    private String couleur;
    private int puissance;

    public Scooter(String modèle, String couleur, int puissance) {
        this.modèle = modèle;
        this.couleur = couleur;
        this.puissance = puissance;
    }

    public void afficherCaractéristiques() {
        System.out.println("Modèle: " + modèle);
        System.out.println("Couleur: " + couleur);
        System.out.println("Puissance: " + puissance + " watts");
    }
}
