package com.scurl.parser;

import com.scurl.socket.Client;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.List;

import static com.scurl.parser.Parsing.parsedMap;


public class ArgsParserMain {
   private static final Options options = new Options();


   public static void main(String[] args) throws IOException {
      if (!args[0].equals("scurl")) {
         System.out.println("올바른 명령어를 입력하세요");
         throw new RuntimeException();
      }

      options.addOption("v", "verbose, 요청, 응답 헤더를 출력합니다.");
      options.addOption("H", "임의의 헤더를 서버로 전송합니다.");
      options.addOption("d", "POST, PUT 등에 데이타를 전송합니다.");
      options.addOption("X", "사용할 method 를 지정합니다. 지정되지 않은 경우 기본값은 GET 입니다.");
      options.addOption("L", "서버의 응답이 30x 계열이면 다음 응답을 따라 갑니다.");
      options.addOption("F", "multipart/form-data 를 구성하여 전송합니다. content 부분에 @filename 을 사용할 수 있습니다.");
      CommandLineParser commandLineParser = new DefaultParser();
      Parsing parsing = new Parsing();

      try {
         CommandLine commandLine = commandLineParser.parse(options, args);
         List<String> argsList = commandLine.getArgList();
         parsing.basicParsing(argsList);
         parsing.setStartLine();


         if (commandLine.hasOption("X")) {
            parsing.setMethod(argsList);
         }
         if (commandLine.hasOption("H")) {
            parsing.setHeader(argsList);
            parsing.setHeaderRequest();
         }
         if (commandLine.hasOption("L")) {
            parsing.setPath(argsList);
            parsing.setStartLine();
         }

         parsing.setRequestMessage();

         if (commandLine.hasOption("v")) {
            Client.setvFlag(true);
            System.out.println(parsedMap.get("startLine"));
         }
      } catch (ParseException e) {
         throw new RuntimeException(e);
      } finally {
         Client client = new Client();
         client.connectServer();
      }

   }
}
