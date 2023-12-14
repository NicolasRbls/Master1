package td5_poo.exo_serveur;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 2000)) {
            // Mise en place d'un BufferedReader pour lire le message du serveur
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Lecture et affichage du message reçu du serveur
            String messageDuServeur = in.readLine();
            System.out.println("Message du serveur : " + messageDuServeur);
        } catch (UnknownHostException e) {
            System.err.println("Serveur inconnu : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur I/O : " + e.getMessage());
        }
    }
}


