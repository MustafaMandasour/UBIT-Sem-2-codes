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
            System.out.println("To view file directory type: /file");
            System.out.println("To disconnect type:/bye");

            while (true) {

                String message = scanner.nextLine();

                out.write(message);
                out.newLine();
                out.flush();

                System.out.println("Server : "+in.readLine());
                
                if (message.equalsIgnoreCase("/bye")){
                    break;
                }

                if (message.equalsIgnoreCase("/file")) {
                    System.out.println("File List-----");

                    String response;

                    while (!(response = in.readLine()).equals("END_OF_LIST")) {
                        System.out.println(response);
                    }

                    System.out.println("End of List-----");

                    System.out.println("please enter file name");
                    
                    String filename = scanner.nextLine();
                    out.write(filename);
                    out.newLine();
                    out.flush();

                    System.out.println("\nFile Content-----");
                    while (!(response = in.readLine()).equals("EOF")) {
                        if (response.startsWith("ERROR")) { 
                            System.out.println(response);
                            break;
                        }
                        System.out.println(response);
                    }
                    System.out.println("End of File-----\n");

                    continue;
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

