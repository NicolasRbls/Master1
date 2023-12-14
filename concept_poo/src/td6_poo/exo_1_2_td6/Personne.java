package td6_poo.exo_1_2_td6;

import java.io.Serializable;

public class Personne implements Serializable {
    private String nom;
    private String prenom;
    private int age;

    public Personne(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    // Getters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public int getAge() { return age; }

    // Setters
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                '}';
    }
}
