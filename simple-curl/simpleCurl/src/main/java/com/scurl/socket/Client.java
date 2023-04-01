package com.scurl.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.scurl.parser.Parsing.parsedMap;

public class Client {
   URL url;
   String requestMessage;
   Socket socket;
   static boolean vFlag;
   String location;
   int count = 0;


   public static void setvFlag(boolean vFlag) {
      Client.vFlag = vFlag;
   }

   public Client() throws MalformedURLException {
      url = new URL(parsedMap.get("url"));
      requestMessage = parsedMap.get("requestMessage");
   }

   public void connectServer() throws IOException {
      try {
         socket = new Socket(url.getHost(), url.getPort() == -1 ? url.getDefaultPort() : url.getPort());
         messageRequest(requestMessage);
         messageResponse();
      } catch (RuntimeException e) {
         e.printStackTrace();
      } finally {
         socket.close();
      }
   }

   public void messageRequest(String requestMessage) throws IOException {
      PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
      printWriter.println(requestMessage);
   }

   public void messageResponse() {
      try {
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         Stream<String> lines = bufferedReader.lines();

         List<String> collect1 = lines.collect(Collectors.toList());
         StringBuilder collectResponse = new StringBuilder();

         int blank = collect1.indexOf("");

         if (vFlag) {
            for (int i = 0; i < collect1.size(); i++) {
               collectResponse.append(collect1.get(i)).append("\n");
               System.out.println(collect1.get(i));
            }
         } else {
            for (int i = blank + 1; i < collect1.size(); i++) {
               collectResponse.append(collect1.get(i)).append("\n");
               System.out.println(collect1.get(i));
            }
         }
         for (String s : collect1) {
            if (s.contains("location:")) {
               String[] splitedLocation = s.split(":");
               location = splitedLocation[1];
               requestMessageWithLocation();

               setvFlag(true);

               if (count == 5) {
                  return;
               }

               count++;
               connectServer();

            }
         }
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   void requestMessageWithLocation() {
      StringBuilder locationMessage = new StringBuilder();
      locationMessage.append("GET")
              .append(location)
              .append(" HTTP/1.0\r\n")
              .append("Host: ")
              .append(parsedMap.get("host"))
              .append("\r\n");
      parsedMap.put("requestMessage", locationMessage.toString());
   }


}
