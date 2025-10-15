import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 1234);
        System.out.println("Connected to server.");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);

        // Thread to read messages from server
        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println(msg);
                }
            } catch (IOException e) {
                System.out.println("Connection closed.");
            }
        }).start();

        // Main thread sends user messages
        while (true) {
            String text = sc.nextLine();
            out.println(text);
            if (text.equalsIgnoreCase("exit")) {
                s.close();
                break;
            }
        }
    }
}
