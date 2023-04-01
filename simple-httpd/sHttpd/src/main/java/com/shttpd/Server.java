package com.shttpd;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
   int portNum;
   Socket socket;
   BufferedReader bufferedReader;

   public void setPortNum(int portNum) {
      this.portNum = portNum;
   }

   public void startServer() {
      try (ServerSocket serverSocket = new ServerSocket(portNum);) {
         socket = serverSocket.accept();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public BufferedReader getMessageProcess() throws IOException {
      try{
         bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      }catch (RuntimeException e){
         e.printStackTrace();
      }
      return bufferedReader;
   }

   public void sendResponse(String responseMessage) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

      bufferedWriter.write(responseMessage);
      bufferedWriter.flush();
      bufferedWriter.close();
   }

}
