package com.nhnacademy.socket;

import java.io.*;
import java.net.Socket;

public class Client extends Thread{

   private int portNum;
   private String host;

   public Client(String host,int portNum){
      this.host = host;
      this.portNum = portNum;
   }

   void connectServer() throws IOException {
      try(Socket socket = new Socket(host, portNum)){
         DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

         InputMessage inputMessage = new InputMessage(socket);
         inputMessage.start();

         while(!isInterrupted()){
            String receivedMessage = dataInputStream.readUTF();
            System.out.println(receivedMessage);
         }
      }catch (RuntimeException e){
         e.printStackTrace();
      }
   }

   @Override
   public void run() {
      try {
         connectServer();
      } catch (IOException e) {
         System.exit(0);
      }
   }
}
