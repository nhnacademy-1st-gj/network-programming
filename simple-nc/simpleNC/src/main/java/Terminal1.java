import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Terminal1 {
   static void runSnc() {
      Scanner scanner = new Scanner(System.in);
      String inputFromUser = scanner.nextLine();
      String[] splitInput = inputFromUser.trim().split(" ");

      List<String> inputList = new ArrayList<>(Arrays.asList(splitInput));

      if(inputList.get(1).equals("-l")){
         int portNum =Integer.parseInt(inputList.get(2));
         Server server = new Server(portNum);
         server.start();
      }else{

         int portNum =Integer.parseInt(inputList.get(2));
         Client client = new Client(portNum);
         client.start();
      }

   }
   public static void main(String[] args) throws IOException {
      runSnc();


   }
}
