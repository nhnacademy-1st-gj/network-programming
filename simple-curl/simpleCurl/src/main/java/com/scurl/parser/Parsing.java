package com.scurl.parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parsing {
   public static Map<String, String> parsedMap = new HashMap<>();
   StringBuilder startLine;

   StringBuilder header = new StringBuilder();

   void basicParsing(List<String> argsList) throws MalformedURLException {
      parsedMap.put("url", argsList.get(argsList.size() - 1));
      parsedMap.put("method", "GET");
      parsedMap.put("host", setHost());
      parsedMap.put("path", "/get");
   }

   String setHost() throws MalformedURLException {
      String url = parsedMap.get("url");
      if (!url.startsWith("http") && !url.startsWith("https")) {
         url = "http://" + url;
      }
      URL netUrl = new URL(url);
      String host = netUrl.getHost();
      if (host.startsWith("www")) {
         host = host.substring("www".length() + 1);
      }
      return host;
   }


   void setMethod(List<String> argsList) {
      String method = argsList.get(1).toUpperCase();
      parsedMap.put("method", method);
   }

   void setHeader(List<String> argsList) {
      String[] splitHeader = argsList.get(1).split(":");
      parsedMap.put("userHeader", splitHeader[0]);
      parsedMap.put("headerContent", splitHeader[1]);
   }

   void setPath(List<String> argsList) throws MalformedURLException {
      URL url = new URL(argsList.get(1));
      String path = url.getPath();
      parsedMap.put("path", path);
   }

   void setStartLine() {
      startLine = new StringBuilder();
      startLine.append(parsedMap.get("method"))
              .append(" ").append(parsedMap.get("path"))
              .append(" HTTP/1.0\r\n")
              .append("Host: ")
              .append(parsedMap.get("host")).append("\r\n");
      parsedMap.put("startLine", startLine.toString());
   }

   void setHeaderRequest() {
      header.append(parsedMap.get("userHeader")).append(": ")
              .append(parsedMap.get("headerContent")).append("\n");
      parsedMap.put("header", header.toString());
   }

   void setRequestMessage() {
      StringBuilder requestMessage = new StringBuilder();
      parsedMap.put("requestMessage", requestMessage.append(startLine)
              .append(header).toString());
   }


}
