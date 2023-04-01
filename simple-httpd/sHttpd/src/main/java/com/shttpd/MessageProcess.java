package com.shttpd;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shttpd.FileProcess.bodyString;

public class MessageProcess {
   FileProcess fileProcess = new FileProcess();
   public static Map<String, String> requestMap = new HashMap<>();

   StringBuilder responseStartLine = new StringBuilder();
   StringBuilder responseHeader = new StringBuilder();
   StringBuilder responseBody = new StringBuilder();
   StringBuilder responseMessage = new StringBuilder();

   boolean isExit = true;
   boolean canRead = true;

   public void setStartLine(BufferedReader bufferedReader) throws IOException {
      requestMap.put("startline", bufferedReader.readLine());
   }

   public void setResponseStartLine() {
      fileProcess.getPath();

      if (!fileProcess.canRead()) {
         responseStartLine.append("HTTP/1.0 403 Forbidden\r\n");
         canRead = false;
      }

      if (!fileProcess.isExist()) {
         responseStartLine.append("HTTP/1.0 404 Not Found\r\n");
         isExit = false;
      } else {
         responseStartLine.append("HTTP/1.0 200 OK\r\n");
      }
   }

   public void setResponseHeader() throws IOException {
      responseHeader.append("Content-Type: ")
              .append(fileProcess.setContentType())
              .append("\r\n")
              .append("Content-Length: ")
              .append(fileProcess.getFileSize())
              .append("\r\n\r\n");
   }

   public void setResponseBody() throws IOException {
      fileProcess.setBodyString();
      responseBody.append(bodyString.toString());
   }

   public String setResponseMessage() throws IOException {
      setResponseStartLine();
      setResponseHeader();
      setResponseBody();

      if (!isExit || !canRead) {
         responseMessage.append(responseStartLine.toString());
      } else {
         responseMessage.append(responseStartLine.toString())
                 .append(responseHeader.toString())
                 .append(responseBody.toString());
      }
      System.out.println(responseMessage.toString());
      return responseMessage.toString();
   }

}
