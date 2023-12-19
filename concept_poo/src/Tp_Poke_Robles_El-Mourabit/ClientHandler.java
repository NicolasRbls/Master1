import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ServeurCombat serveur;
    private PrintWriter out;
    private BufferedReader in;
    private String nomDresseur;
    private String dernierMessageRecu; // Attribut pour stocker le dernier message

    


    public ClientHandler(Socket socket, ServeurCombat serveur) {
        this.clientSocket = socket;
        this.serveur = serveur;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (nomDresseur == null) {
                    nomDresseur = inputLine;
                    System.out.println("Message reçu de " + nomDresseur + ": " + inputLine);
                    serveur.notifierMessageRecu(this); // Notifie le serveur qu'un message a été reçu

                    continue;
                }
                dernierMessageRecu = inputLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fermerConnexion();
        }
    }

    // Cette méthode renvoie le nom du dresseur associé à ce client
    public String getNomDresseur() {
        return nomDresseur;
    }

    public void envoyerMessage(String message) {
        System.out.println("Demande envoyée au client " + nomDresseur + ": " + message);
        out.println(message);
    }


    public String getDernierMessageRecu() {
        return dernierMessageRecu; // Retourner le dernier message reçu
    }

    private void fermerConnexion() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
