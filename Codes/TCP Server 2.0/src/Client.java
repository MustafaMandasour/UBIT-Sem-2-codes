import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            
            Socket socket = new Socket("127.0.0.1", 5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Connected to Server");
            System.out.println("To Disconnect type: /quit  ");
            
            new Thread(() -> {
                String msg;
                try {
                    while ((msg = in.readLine()) != null) {
                        System.out.println("Server: " + msg);
                    }
                } catch (IOException e) {
                    System.out.println("Server disconnected.");
                }
            }).start();

            
            String message;
            while ((message = key.readLine()) != null) {
                if (message.equalsIgnoreCase("/quit")) {
                    out.println(message);
                    out.println("/quit");
                    System.out.println("Disconnected");
                    in.close();
                    out.close();
                    key.close();
                    socket.close();
                    
                    break;
                } else {
                    out.println(message);
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

