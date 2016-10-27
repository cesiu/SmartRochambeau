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
    init();
  }

  /**
   * Presents the initial options.
   */
  public void init() {
    System.out.print("Select \"RandomAI\": ");
    String temp = in.nextLine();
    curController.getGame().setAI(RandomAI.class);
  }

  /**
   * Instructs the UI to display the most recent results.
   */
  public void display() {
  }

  /**
   * Runs the ConsoleUI indefinitely.
   */
  public void run() {
    boolean isRunning = true;
    String tempInput = null;
    
    while (isRunning) {
      System.out.print("Select \"[p]lay\", \"[s]tats\", or \"[q]uit\": ");
      tempInput = in.nextLine();
      switch (tempInput) {
        case "p":
          curController.runRound(getThrow());
          break;
        case "s":
          showStats();
          break;
        case "q":
          deconstruct();
          isRunning = false;
          break;
        default:
          break;
      }
    }
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
