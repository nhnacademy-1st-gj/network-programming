package com.nhnacademy.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Input extends Thread{
   Socket socket;

   public Input(Socket socket) {
      this.socket = socket;
   }

   void getMessageFromUser() throws IOException {
      Scanner scanner = new Scanner(System.in);
      String message = scanner.nextLine();


      DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
      dataOutputStream.writeUTF(message);
      dataOutputStream.flush();
   }

   @Override
   public void run() {
      try {
         getMessageFromUser();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}
