package td5_poo.exo_serveur;
import java.util.Random;

public class MultiThreadExample {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Processus()).start();
        }
    }

    static class Processus implements Runnable {
        @Override
        public void run() {
            try {
                // Générer un temps d'attente aléatoire entre 0 et 10 secondes
                int sleepTime = new Random().nextInt(11) * 1000;
                Thread.sleep(sleepTime);

                // Afficher le message indiquant que le processus a été exécuté
                System.out.println("Processus " + Thread.currentThread().getId() + " exécuté après " + sleepTime + " millisecondes.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

