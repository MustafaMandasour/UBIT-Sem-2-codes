import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

     public static String  displayDirectory (String path, int i){
        File folder = new File(path);
        if (!(folder.exists())|| !(folder.isDirectory()) ) {
             return "ERROR: Path does not eist or not in Directory";
        }
        File[] files = folder.listFiles();
        if (files == null || i < 0 || i >= files.length) {
            return "ERROR: Invalid index or directory is empty.";
        }
        return files[i].getName(); 
    }

    public static void main(String[] args) {
        
        ServerSocket serverSocket = null;
        Socket socket = null ;
        BufferedReader in = null ;
        InputStreamReader reader = null ;
        BufferedWriter out = null;
        OutputStreamWriter writer =null;
        Scanner scanner = null;
        String folderpath = "C:\\Users\\manda\\Desktop\\Future";
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
                
                if (message.equalsIgnoreCase("/bye")){
                    break;
                }

                if (message.equalsIgnoreCase("/file")) {
                    File folder = new File(folderpath);
                    File[] files = folder.listFiles();

                    if (files == null || files.length == 0) {
                        out.write("No files found");
                        out.newLine();
                        out.flush();
                    } else {
                        for (int i = 0; i < files.length; i++) {
                            out.write(displayDirectory(folderpath, i));
                            out.newLine();
                            out.flush();
                        }
                    }

                    out.write("END_OF_LIST");
                    out.newLine();
                    out.flush();

                    String filename = in.readLine();
                    File file = new File(folderpath+"//"+filename);
                    if (file.exists() && file.isFile()) {

                        BufferedReader fileReader = new BufferedReader(new FileReader(file));
                        String line;

                        while ((line = fileReader.readLine()) != null) {
                            out.write(line);
                            out.newLine();
                            out.flush();
                        }

                        fileReader.close();

                      
                        out.write("EOF");
                        out.newLine();
                        out.flush();

                    } else {
                        out.write("ERROR: File does not exist");
                        out.newLine();
                        out.flush();
                    }
                    continue;
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

