import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

     

    public static void main(String[] args) {
        
        ServerSocket serverSocket = null;
        Socket socket = null ;
        BufferedReader in = null ;
        InputStreamReader reader = null ;
        BufferedWriter out = null;
        OutputStreamWriter writer =null;
        Scanner scanner = null;

        try {

            serverSocket = new ServerSocket(1234);
            System.out.println("Server Started");
            socket = serverSocket.accept();
            System.out.println("Client Connected");
            reader = new InputStreamReader(socket.getInputStream());
            writer = new OutputStreamWriter(socket.getOutputStream());
            in = new BufferedReader(reader);
            out = new BufferedWriter(writer);
            scanner =new Scanner(System.in);

            while (true) {

                String message = in.readLine();
                System.out.println("Client : " + message);
                out.write("message recived");
                out.newLine();
                out.flush();
                
                if (message.equalsIgnoreCase("bye")){
                    break;
                }
            }

            socket.close();
            reader.close();
            writer.close();
            in.close();
            scanner.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

