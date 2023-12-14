package td3_poo.exo_3_td3;

public class Main {
    public static void main(String[] args) {
        Banque compte = new Banque(1000); // Solde initial de 1000 €

        try {
            compte.retrait(50); // Premier retrait de 50 €
            compte.retrait(1000); // Second retrait de 1000 € - devrait lever une exception
        } catch (RetraitException e) {
            System.out.println(e.getMessage());
        }
    }
}

