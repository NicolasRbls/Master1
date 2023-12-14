package td5_poo.messagerie;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Connecté au serveur de messagerie. " + in.readLine());
        new Thread(new ReceiveMessage(socket)).start();

        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
        }
    }
}

class ReceiveMessage implements Runnable {
    private final Socket socket;

    public ReceiveMessage(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            System.err.println("Erreur en recevant un message : " + e.getMessage());
        }
    }
}
