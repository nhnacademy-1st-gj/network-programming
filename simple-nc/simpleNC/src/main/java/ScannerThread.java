import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ScannerThread extends Thread{
   void getMessageFromUser() throws IOException {
      Scanner scanner = new Scanner(System.in);
      String message;
      message = scanner.nextLine();
      OutputStream outputStream = socket.getOutputStream();
      DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
      dataOutputStream.writeUTF(message);
      dataOutputStream.flush();
   }

   Socket socket;

   public ScannerThread(Socket socket) {
      this.socket = socket;
   }

   @Override
   public void run() {
      while(true){ //인터럽트 안 걸린 순간에만 돌고 걸리면 빠져나간다
         try {
            getMessageFromUser();
         } catch (IOException e) {
            throw new RuntimeException(e);
         }

      }
   }
}
