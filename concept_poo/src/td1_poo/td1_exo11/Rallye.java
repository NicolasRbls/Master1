package td1_poo.td1_exo11;

import java.util.ArrayList;

public class Rallye {
    private ArrayList<Voiture> voitures;

    public Rallye() {
        this.voitures = new ArrayList<>();
    }

    public void ajouterVoiture(Voiture v) {
        voitures.add(v);
    }

    public void classementGeneral() {
        for (int i = 0; i < voitures.size() - 1; i++) {
            for (int j = 0; j < voitures.size() - 1 - i; j++) {
                Voiture v1 = voitures.get(j);
                Voiture v2 = voitures.get(j + 1);
    
                if (v1.getCategorie().compareTo(v2.getCategorie()) > 0 || 
                   (v1.getCategorie().equals(v2.getCategorie()) && v1.getTempsTotal() > v2.getTempsTotal())) {
                    voitures.set(j, v2);
                    voitures.set(j + 1, v1);
                }
            }
        }
    
        String currentCategory = "";
        int position = 1; // pour gérer l'ordre du classement
        for (int i = 0; i < voitures.size(); i++) {
            Voiture voiture = voitures.get(i);
            if (!voiture.getCategorie().equals(currentCategory)) {
                currentCategory = voiture.getCategorie();
                System.out.println("\nClassement pour " + currentCategory + " :");
                position = 1; // réinitialisation de l'ordre pour la nouvelle catégorie
            }
            System.out.println(position + ". " + voiture.getModele() + " - " + voiture.getTempsTotal() + " sec");
            position++;
        }
    }
    
}
