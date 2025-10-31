import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started waiting for Client");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            new Thread(() -> {
                String msg;
                try {
                    while ((msg = in.readLine()) != null) {
                        if (msg.equalsIgnoreCase("/quit")) {
                            System.out.println("Client Disconnected");
                            break;
                        }
                        else{
                            System.out.println("Client: " + msg);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Server disconnected.");
                }
            }).start();

            String message;
            while ((message = key.readLine()) != null) {
                if (message.equalsIgnoreCase("/quit")) {
                    out.println("/quit");
                    break;
                }
                out.println(message);

            }
            socket.close();
            serverSocket.close();
            System.out.println("Server Closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
