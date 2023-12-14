package td5_poo.messagerie;

import java.io.*;
import java.net.*;

public class AcceptClient implements Runnable {
    private final Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String userName;

    public AcceptClient(Socket socket) throws IOException {
        this.clientSocket = socket;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        out.println("Entrez votre nom d'utilisateur:");
        userName = in.readLine();
        System.out.println(userName + " s'est connecté.");
        Serveur.broadcastMessage("[" + userName + "] s'est connecté.");
    }

    @Override
    public void run() {
        try {
            out.println("Votre message :");
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                Serveur.broadcastMessage("[" + userName + "]: " + inputLine);
            }
        } catch (IOException e) {
            System.err.println("Erreur avec le client : " + userName);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}

