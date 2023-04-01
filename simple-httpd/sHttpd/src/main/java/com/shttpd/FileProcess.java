package com.shttpd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.shttpd.MessageProcess.requestMap;


public class FileProcess {
   File file;
   public static StringBuilder bodyString = new StringBuilder();
   String[] splitStartline;
   Path path;

   public void getPath() {
      splitStartline = requestMap.get("startline").split(" ");
      file = new File("." + splitStartline[1]); // new File
      requestMap.put("path", file.toString());
      path = Paths.get(requestMap.get("path"));
   }

   public boolean isExist() {
      return file.exists();
   }

   public long getFileSize(){
      return file.length();
   }

   public boolean canRead() {
      return file.canRead();
   }

   public String setContentType() throws IOException {
      if (file.isDirectory()) {
         return "text/html";
      } else {
         return Files.probeContentType(path);
      }
   }


   public void setBodyString() throws IOException {
      if (file.isDirectory()) {
         String[] list = file.list();
         bodyString.append("<ul>").append("\r\n");
         for (String s : list) {
            bodyString.append("<li><a href=\"")
                    .append(s)
                    .append("\">")
                    .append(s)
                    .append("</a>")
                    .append("</li>").append("\r\n");
         }
         bodyString.append("</ul>");
      } else if (file.isFile()) {
         String bodyContent = Files.readString(path);
         bodyString.append(bodyContent);

      }

   }


}
