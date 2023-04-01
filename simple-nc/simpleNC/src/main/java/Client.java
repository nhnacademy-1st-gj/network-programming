import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{
   private int portNum;

   public Client(int portNum) {
      this.portNum = portNum;
   }

   String receivedMessage;

   void connectServer(String message) throws IOException {

      try(Socket socket = new Socket("localhost",portNum);){
//         ScannerThread scannerThread = new ScannerThread(socket);
//         scannerThread.start();

         InputStream inputStream = socket.getInputStream();
         DataInputStream dataInputStream = new DataInputStream(inputStream);
         OutputStream outputStream = socket.getOutputStream();
         DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
         dataOutputStream.writeUTF(message);
         dataOutputStream.flush();

         receivedMessage = dataInputStream.readUTF();
         System.out.println(receivedMessage);

      }catch (RuntimeException e){
         e.printStackTrace();
      }
   }

   @Override
   public void run() {
      Scanner scanner = new Scanner(System.in);
      String message = scanner.nextLine();

      try {
         connectServer(message);

      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}
