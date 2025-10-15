import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(1234);
        System.out.println("Server started... waiting for clients...");

        // To store all connected clients
        List<Socket> clients = new ArrayList<>();

        while (true) {
            Socket s = server.accept();
            clients.add(s);
            System.out.println("Client connected: " + s);

            // Start a new thread for each client
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                    out.println("Welcome to Chat! Type 'exit' to leave.");

                    String msg;
                    while ((msg = in.readLine()) != null) {
                        if (msg.equalsIgnoreCase("exit")) {
                            out.println("Goodbye!");
                            break;
                        }

                        // Send message to all connected clients
                        for (Socket c : clients) {
                            if (!c.equals(s)) {
                                new PrintWriter(c.getOutputStream(), true).println("Client says: " + msg);
                            }
                        }
                        System.out.println("Message from client: " + msg);
                    }

                    clients.remove(s);
                    s.close();
                    System.out.println("Client disconnected.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }).start();
        }
    }
}
