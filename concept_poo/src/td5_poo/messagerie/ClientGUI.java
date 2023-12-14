package td5_poo.messagerie;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ClientGUI {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private JList<String> onlineUsersList;
    private DefaultListModel<String> onlineUsersModel;
    private PrintWriter out;

    public ClientGUI(String host, int port) {
        initializeGUI();

        try {
            Socket socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String userName = JOptionPane.showInputDialog(frame, "Entrez votre nom d'utilisateur:", "Connexion", JOptionPane.PLAIN_MESSAGE);
            out.println(userName);

            new Thread(() -> {
                try {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.startsWith("/users ")) {
                            updateOnlineUsers(inputLine.substring(7));
                        } else {
                            chatArea.append(inputLine + "\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeGUI() {
        frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Chat Area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        // Message Field
        messageField = new JTextField();
        bottomPanel.add(messageField, BorderLayout.CENTER);

        // Send Button
        sendButton = new JButton("Send");
        bottomPanel.add(sendButton, BorderLayout.EAST);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Online Users List
        onlineUsersModel = new DefaultListModel<>();
        onlineUsersList = new JList<>(onlineUsersModel);
        frame.add(new JScrollPane(onlineUsersList), BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            out.println(message);
            messageField.setText("");
        }
    }

    private void updateOnlineUsers(String usersList) {
        SwingUtilities.invokeLater(() -> {
            onlineUsersModel.clear();
            for (String user : usersList.split(",")) {
                onlineUsersModel.addElement(user.trim());
            }
        });
    }

    public static void main(String[] args) {
        new ClientGUI("localhost", 2000);
    }
}
