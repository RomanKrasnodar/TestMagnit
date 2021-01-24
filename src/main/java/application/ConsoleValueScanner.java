package application;

import java.util.Scanner;

public class ConsoleValueScanner {

  public static int scanInputNumber() {
    int userNumber = 0;
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println(Constants.INPUT_START_MESSAGE);
      String inputLine = scanner.nextLine();
      try {
        userNumber = Integer.parseInt(inputLine);
        if (userNumber > 0) {
          break;
        } else {
          System.out.println(Constants.INPUT_EXCEPTION_MESSAGE);
        }
      } catch (NumberFormatException e) {
        System.out.println(Constants.INPUT_EXCEPTION_MESSAGE);
      }
    }
    return userNumber;
  }
}
