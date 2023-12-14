package td5_poo.messagerie;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Serveur {
    private static final int PORT = 2000;
    private static Map<String, PrintWriter> clients = new ConcurrentHashMap<>();
    private static List<String> messageHistory = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Serveur démarré sur le port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }

    public static void broadcastMessage(String message) {
        messageHistory.add(message);
        for (PrintWriter writer : clients.values()) {
            writer.println(message);
            writer.flush();
        }
    }

    private static void updateOnlineUsers() {
        StringJoiner joiner = new StringJoiner(",");
        for (String user : clients.keySet()) {
            joiner.add(user);
        }
        broadcastMessage("/users " + joiner.toString());
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private String userName;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Première ligne reçue est le nom d'utilisateur
                this.userName = in.readLine().trim();
                clients.put(userName, out);

                // Envoyer l'historique des messages
                for (String message : messageHistory) {
                    out.println(message);
                }

                updateOnlineUsers();

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    broadcastMessage("[" + userName + "]: " + inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                clients.remove(userName);
                updateOnlineUsers();
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

