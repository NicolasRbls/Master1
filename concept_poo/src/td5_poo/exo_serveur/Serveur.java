package td5_poo.exo_serveur;
import java.io.*;
import java.net.*;

public class Serveur {
    private static int countClients = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2000)) {
            System.out.println("Serveur démarré. En attente de clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new AcceptClient(clientSocket)).start();
                countClients++;
                System.out.println("Nombre total de clients connectés : " + countClients);
            }
        } catch (IOException e) {
            System.err.println("Erreur du serveur : " + e.getMessage());
        }
    }

    static class AcceptClient implements Runnable {
        private Socket clientSocket;

        public AcceptClient(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("Bonjour, vous êtes connecté au serveur !");
                out.flush();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Erreur dans AcceptClient : " + e.getMessage());
            }
        }
    }
}
