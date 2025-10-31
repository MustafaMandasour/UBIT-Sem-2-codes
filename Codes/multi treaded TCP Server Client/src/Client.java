import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;


    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1", 9999);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            System.out.println("Connected to chat server. Type /quit to exit.");

            Thread inputThread = new Thread(new InputHandler());
            inputThread.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null) {

                System.out.println(inMessage);
            }


        } catch (IOException e) {
            System.out.println("Connection lost.");
        } finally {
            shutdown();
        }
    }

    public void shutdown() {
        done = true;
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (client != null && !client.isClosed()) client.close();
            System.out.println("Disconnected from chat server.");
        } catch (IOException e) {
            // ignore
        }
    }

    class InputHandler implements Runnable {
        @Override
        public void run() {
            try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (!done) {
                    String message = consoleReader.readLine();
                    if (message == null) continue;

                    if (message.equalsIgnoreCase("/quit")) {
                        out.println("/quit");
                        shutdown();
                        break;
                    } else {
                        out.println(message);
                    }

                }

            } catch (IOException e) {
                shutdown();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        Thread clientThread = new Thread(client);
        clientThread.start();
    }
}
