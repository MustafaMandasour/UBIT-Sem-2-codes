import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server implements Runnable {

    private final List<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done;
    private ExecutorService pool;

    public Server() {
        // Thread-safe list
        connections = Collections.synchronizedList(new ArrayList<>());
        done = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(9999);
            pool = Executors.newCachedThreadPool();
            System.out.println("Server started on port 9999...");

            while (!done) {
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        } catch (IOException e) {
            Shutdown();
        }
    }

    public void broadcast(String message) {
        synchronized (connections) {
            for (ConnectionHandler ch : connections) {
                if (ch != null) {
                    ch.sendMessage(message);
                }
            }
        }
    }

    public void Shutdown() {
        try {
            done = true;
            if (pool != null && !pool.isShutdown()) {
                pool.shutdownNow();
            }
            if (server != null && !server.isClosed()) {
                server.close();
            }
            synchronized (connections) {
                for (ConnectionHandler ch : connections) {
                    ch.shutdown();
                }
            }
            System.out.println("Server shut down.");
        } catch (IOException e) {
            // ignore
        }
    }

    class ConnectionHandler implements Runnable {

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String nickname;

        public ConnectionHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                out.println("Please enter a nickname: ");
                nickname = in.readLine();

                if (nickname == null) {
                    shutdown();
                    return;
                }

                System.out.println(nickname + " connected.");
                broadcast(nickname + " joined the chat.");


                String message;
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("/nick ")) {
                        String[] messageSplit = message.split(" ", 2);
                        if (messageSplit.length == 2) {
                            String newNick = messageSplit[1];
                            broadcast(nickname + " renamed themselves to " + newNick);
                            System.out.println(nickname + " renamed themselves to " + newNick);
                            nickname = newNick;
                            out.println("Successfully changed nickname to: " + nickname);
                        } else {
                            out.println("No nickname provided.");
                        }

                    } else if (message.equalsIgnoreCase("/quit")) {
                        System.out.println(nickname + " disconnected.");
						broadcast(nickname + " left the chat.");
                        shutdown();
                        if(connections.isEmpty()){
                            Shutdown();
                        }
                        break;

                    } else {
                        broadcast(nickname + ": " + message);
                    } 
                }
                
                
            } catch (IOException e) {
                shutdown();
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        public void shutdown() {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (client != null && !client.isClosed()) {
                    client.close();
                }
                connections.remove(this);
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
