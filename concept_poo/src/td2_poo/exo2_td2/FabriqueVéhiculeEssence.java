package td2_poo.exo2_td2;

class FabriqueVéhiculeEssence implements FabriqueVéhicule {
    @Override
    public Automobile créerAutomobile(String modèle, int puissance, String couleur, int espace) {
        return new AutomobileEssence(modèle, puissance, couleur, espace);
    }

    @Override
    public Scooter créerScooter(String modèle, String couleur, int puissance) {
        return new ScooterEssence(modèle, couleur, puissance);
    }
}
