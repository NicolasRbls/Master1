package td1_poo.td1_exo12;

public class Main {
    public static void main(String[] args) {
        Joueur joueur = new Joueur("Chevalier Arthur", 100, 10 , 50);
        Ennemi[] ennemis = {
            new Ennemi("Orc Gronk", 80, 12,10),
            new Ennemi("Orc Blorg", 75, 10,10)
        };
        PNJ marchand = new PNJ("Marchand Merlin", 50);

        joueur.setArme(new Epee());
        ennemis[0].setArme(new Hache());
        ennemis[1].setArme(new Arc());

        System.out.println("-- Début de l'aventure --");
        System.out.println(joueur.getNom() + " rencontre " + marchand.getNom() + ".");
        marchand.seDeplacer();
        System.out.println(marchand.getNom() + ": Bonjour, aventurier! Méfiez-vous des orcs dans la région.");
        System.out.println("-----------------------------");

        for (Ennemi ennemi : ennemis) {
            if (joueur.getPointsDeVie() <= 0) {
                break;
            }
            
            System.out.println(joueur.getNom() + " croise le chemin de " + ennemi.getNom() + "!");
            while (joueur.getPointsDeVie() > 0 && ennemi.getPointsDeVie() > 0) {
                int degatsInfligesAuEnnemi = joueur.attaquer(ennemi);
                System.out.println(joueur.getNom() + " inflige " + degatsInfligesAuEnnemi + " PV à " + ennemi.getNom() + ". " + ennemi.getNom() + " a maintenant " + ennemi.getPointsDeVie() + " PV.");
                
                if (ennemi.getPointsDeVie() > 0) {
                    int degatsInfligesAuJoueur = ennemi.attaquer(joueur);
                    System.out.println(ennemi.getNom() + " inflige " + degatsInfligesAuJoueur + " PV à " + joueur.getNom() + ". " + joueur.getNom() + " a maintenant " + joueur.getPointsDeVie() + " PV.");
                }
            }
        
            System.out.println("-----------------------------");
        }

        if (joueur.getPointsDeVie() <= 0) {
            System.out.println(joueur.getNom() + " a été vaincu. Fin de la partie.");
        } else {
            System.out.println(joueur.getNom() + " a triomphé de ses adversaires et continue son aventure!");
        }
    }
}
