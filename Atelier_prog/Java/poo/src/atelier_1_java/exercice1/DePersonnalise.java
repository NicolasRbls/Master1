package atelier_1_java.exercice1;

import java.util.List;

public class DePersonnalise extends De {
    private List<Object> facesPersonnalisees; // Liste des valeurs possibles pour les faces du dé

    public DePersonnalise(String nom, List<Object> facesPersonnalisees) {
        super(nom, facesPersonnalisees.size()); // Appel au constructeur de la classe De avec le nombre de faces
        this.facesPersonnalisees = facesPersonnalisees;
    }

    public Object afficherValeurLancee() {
        int indiceLance = lancer() - 1;
        if (indiceLance >= 0 && indiceLance < facesPersonnalisees.size()) {
            Object valeurLancee = facesPersonnalisees.get(indiceLance);
            return valeurLancee;
        } else {
            throw new IllegalArgumentException("Indice de lancer invalide.");
        }
    }
}



