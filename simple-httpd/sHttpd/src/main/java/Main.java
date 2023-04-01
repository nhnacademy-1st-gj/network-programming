import com.shttpd.MessageProcess;
import com.shttpd.Server;

import java.io.IOException;

public class Main {
   public static void main(String[] args) throws IOException {
      Server server = new Server();
      MessageProcess messageProcess = new MessageProcess();

      try {
         if (!args[0].equals("shttpd")) {
            System.out.println(args[0]);
            System.out.println("올바른 명령어를 입력하세요");
            throw new RuntimeException();
         }
      } catch (ArrayIndexOutOfBoundsException e) {
         System.out.println("커맨드 라인을 입력하세요");
         e.printStackTrace();
      }

      server.setPortNum(Integer.parseInt(args[1]));

      server.startServer();
      messageProcess.setStartLine(server.getMessageProcess());
      server.sendResponse(messageProcess.setResponseMessage());

   }
}
