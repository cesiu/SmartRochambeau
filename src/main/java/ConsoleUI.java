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

  private int delta;

  public ConsoleUI(UIController curController) {
    this.curController = curController;
    in = new Scanner(System.in);
    init();
  }

  /**
   * Presents the initial options.
   */
  public void init() {
    System.out.print("Select \"[r]andom\", \"[m]arkov\", \"[p]attern\" or \"[n]aive bayes\" AI: ");
    String temp = in.nextLine();
    if (temp.equals("m")) {
      curController.getGame().setAI(MarkovAI.class);
    }
    else if (temp.equals("p")) {
      curController.getGame().setAI(PatternMatchingAI.class);
    }
    else if (temp.equals("n")) {
      curController.getGame().setAI(NaiveBayesAI.class);
    }
    else {
      curController.getGame().setAI(RandomAI.class);
    }
  }

  /**
   * Instructs the UI to display the most recent results.
   */
  public void display() {
    // TODO: Christopher Lee is fleshing this out as his weekly task; I just
    //       need some prints to test other stuff.
    lastRound = curController.getGame().getLastRound();
    System.out.println("Computer played " + lastRound.aiThrow
     + ", result was " + lastRound.result + ".\n"); 
    delta += lastRound.result;
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
    System.exit(0);
  }

  /**
   * Gets a throw from the user.
   * 
   * @return The user's chosen throw
   */
  private GameModerator.GameThrow getThrow() {
    String tempInput = null;

    do {
      System.out.print("Select \"rock\", \"paper\", or \"scissors\": ");
      tempInput = in.nextLine();
      switch (tempInput) {
        case "rock":
          return GameModerator.GameThrow.ROCK;
        case "paper":
          return GameModerator.GameThrow.PAPER;
        case "scissors":
          return GameModerator.GameThrow.SCISSORS;
        default:
          tempInput = null;
      }
    } while (tempInput == null);

    // This statement should never be reached.
    throw new RuntimeException("Error scanning user's throw.");
  }

  private void showStats() {
    System.out.println("The computer has won " + delta + " more times than you.");
  }

  private void deconstruct() {
  }
}
