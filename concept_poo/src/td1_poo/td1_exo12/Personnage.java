package td1_poo.td1_exo12;

abstract class Personnage {
    protected String nom;
    protected int pointsDeVie;
    protected int pointsDeForce;
    protected Arme arme;
    protected int vitesse;
    protected double critRate = 0.20;  // 20% de chance par défaut
    protected double critMultiplier = 2.0;  // Double dégâts par défaut


    public Personnage(String nom, int pointsDeVie, int pointsDeForce , int vitesse) {
        this.nom = nom;
        this.pointsDeVie = pointsDeVie;
        this.pointsDeForce = pointsDeForce;
        this.vitesse = vitesse;
    }

    public Personnage(String nom, int pointsDeVie) {
        this.nom = nom;
        this.pointsDeVie = pointsDeVie;
    }

    public void seDeplacer() {
        System.out.println(nom + " se déplace.");
    }

    public int attaquer(Personnage adversaire) {
        int degatsTotaux = 0;
    
        int degats = this.pointsDeForce + (this.arme != null ? this.arme.getDegats() : 0);
    
        if (isCriticalHit()) {
            degats *= critMultiplier;
            System.out.println(this.getNom() + " inflige un coup critique!");
        }
    
        adversaire.subirDegats(degats);
        degatsTotaux += degats;
    
        if (hasDoubleAttackChance()) {
            System.out.println(this.getNom() + " utilise une double attaque!");
            if (isCriticalHit()) {
                degats *= critMultiplier;
                System.out.println(this.getNom() + " inflige un coup critique lors de la double attaque!");
            }
            adversaire.subirDegats(degats);
            degatsTotaux += degats;
        }
    
        return degatsTotaux; 
    }
    

    public void seDefendre() {
        System.out.println(nom + " se défend.");
    }

    public void subirDegats(int degats) {
        this.pointsDeVie -= degats;
        if (this.pointsDeVie <= 0) {
            this.pointsDeVie = 0;
        }
    }

    public boolean hasDoubleAttackChance() {
        double chance = Math.random() * 100;
        return chance < this.vitesse;
    }

    public boolean isCriticalHit() {
        return Math.random() < critRate;  // Si un nombre aléatoire entre 0 et 1 est inférieur au critRate, c'est un coup critique.
    }    

    public void setArme(Arme arme) {
        this.arme = arme;
        System.out.println(nom + " équipe " + arme.getNom());
    }

    public String getNom(){
        return this.nom;
    }

    public int getPointsDeVie(){
        return this.pointsDeVie;
    }

    public int getVitesse() {
        return vitesse;
    }


}

