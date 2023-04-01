package com.nhnacademy.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
   int portNum;

   public Server(int portNum) throws IOException {
      this.portNum = portNum;
   }
   public void startServer() throws IOException{
      try{
         ServerSocket server  = new ServerSocket(portNum);
         Socket socket  = server.accept();

         InputMessage inputMessage = new InputMessage(socket);
         DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

         inputMessage.start();

         while(!isInterrupted()){
            String receivedMessage = dataInputStream.readUTF();
            System.out.println(receivedMessage);
         }

         dataInputStream.close();
      }catch (RuntimeException e){
         e.printStackTrace();
      }
   }

   @Override
   public void run() {
      try{
         startServer();
      } catch (IOException e) {
         System.exit(0);
      }
   }
}
