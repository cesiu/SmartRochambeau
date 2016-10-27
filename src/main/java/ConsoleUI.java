
/**
 * Manages a command-line interface for a game of SmartRochambeau.
 * @author cesiu
 * @version October 12, 2016
 */

import java.util.Scanner;

public class ConsoleUI implements GameUI {
  private GameModerator.GameRound lastRound;
  private UIController curController;
  private Scanner in;

  public ConsoleUI(UIController curController) {
    this.curController = curController;
    in = new Scanner(System.in);
  }

  /**
   * Instructs the UI to display the most recent results.
   */
  public void display() {
  }

  public void run() {
  }

  /**
   * Gets a throw from the user.
   * 
   * @return The user's chosen throw
   */
  private GameModerator.GameThrow getThrow() {
    String tempInput = null;

    do {
      System.out.print("Select \"Rock\", \"Paper\", or \"Scissors\": ");
      tempInput = in.nextLine();
      switch (tempInput) {
        case "Rock":
          return GameModerator.GameThrow.ROCK;
        case "Paper":
          return GameModerator.GameThrow.PAPER;
        case "Scissors":
          return GameModerator.GameThrow.SCISSORS;
        default:
          tempInput = null;
      }
    } while (tempInput == null);

    // This statement should never be reached.
    throw new RuntimeException("Error scanning user's throw.");
  }

  private void showStats() {
  }

  private void deconstruct() {
  }
}
