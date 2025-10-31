import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

     

    public static void main(String[] args) {
        
        Socket socket = null ;
        BufferedReader in = null ;
        InputStreamReader reader = null ;
        BufferedWriter out = null;
        OutputStreamWriter writer =null;
        Scanner scanner = null;

        try {

            socket = new Socket("localhost", 1234);
            reader = new InputStreamReader(socket.getInputStream());
            writer = new OutputStreamWriter(socket.getOutputStream());
            in = new BufferedReader(reader);
            out = new BufferedWriter(writer);
            scanner =new Scanner(System.in);

            while (true) {

                String message = scanner.nextLine();

                out.write(message);
                out.newLine();
                out.flush();

                System.out.println("Server : "+in.readLine());
                
                if (message.equalsIgnoreCase("bye")){
                    break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if (socket !=null) socket.close();
                if (in !=null) in.close();
                if (out!=null) out.close();
                if (scanner !=null) scanner.close();
                if (reader!=null) reader.close();
                if (writer!=null) writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

