package com.nhnacademy.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class InputMessage extends Thread{

   Socket socket;
   Scanner scanner = new Scanner(System.in);

   public InputMessage(Socket socket) {
      this.socket = socket;
   }

   void getMessageFromUser(){
      try{
         String message = scanner.nextLine();

         DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
         dataOutputStream.writeUTF(message);
         dataOutputStream.flush();

      }catch (RuntimeException e){
         e.printStackTrace();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   void socketClose() throws IOException {
      socket.close();
   }


   @Override
   public void run() {
      while(!isInterrupted()){
         getMessageFromUser();
      }
   }
}
