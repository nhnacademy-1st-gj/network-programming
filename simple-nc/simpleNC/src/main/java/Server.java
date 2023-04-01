import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread{
   int portNum;

   public Server(int portNum) {
      this.portNum = portNum;
   }

   public void startServer(String s) throws IOException {
      try(ServerSocket server = new ServerSocket(portNum)){

         Socket socket = server.accept();
         InputStream inputStream = socket.getInputStream();
         OutputStream outputStream = socket.getOutputStream();
         DataInputStream dataInputStream = new DataInputStream(inputStream);
         DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

         String message = dataInputStream.readUTF();
         dataOutputStream.writeUTF(message);
         System.out.println(message);





      }catch (RuntimeException e){
         e.printStackTrace();
      }
   }

   @Override
   public void run() {
      Scanner scanner = new Scanner(System.in);
      String message = scanner.nextLine();
      try {
         startServer(message);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}
