package td2_poo.exo2_td2;

interface FabriqueVéhicule {
    Automobile créerAutomobile(String modèle, int puissance, String couleur, int espace);
    Scooter créerScooter(String modèle, String couleur, int puissance);
}
