package td3_poo.exo_3_td3;

public class Banque {
    private int solde;

    public Banque(int soldeInitial) {
        this.solde = soldeInitial;
    }

    public void retrait(int somme) throws RetraitException {
        if (somme > solde) {
            throw new RetraitException("Solde insuffisant pour le retrait de " + somme + " euro.");
        }
        solde -= somme;
        System.out.println("Retrait de " + somme + " € effectué. Solde restant : " + solde + " euro.");
    }
}

