/**
 * Contains a main method to start and run a game of SmartRochambeau.
 * 
 * @author cesiu
 */

public class SmartRochambeau {
  public static void main(String[] args) {
    UIController curInterface = new UIController(false);
    curInterface.setUI(new ConsoleUI(curInterface));
  }
}
