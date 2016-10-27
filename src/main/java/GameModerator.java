
/**
 * Represents the game moderator of the SmartRochambeau application.
 * @author sarahpadlipsky
 * @author cesiu
 * @version October 12, 2016
 */

import java.util.HashMap;

public class GameModerator {
  /*
   * Represents possible throws.
   */
  public enum GameThrow {
    ROCK, PAPER, SCISSORS
  }

  // The current AI being used
  private GameAI currentAI;
  // The AIs that have been saved
  private HashMap<Class, GameAI> savedAIs;
  // The stats for the AIs
  private HashMap<Class, int[]> aiStats;
  // The information for the last round
  private GameRound lastRound;

  public GameModerator() {
    savedAIs = new HashMap<Class, GameAI>();
    aiStats = new HashMap<Class, int[]>();

    savedAIs.put(RandomAI.class, new RandomAI());
  }

  /**
   * Takes the user's input, queries the AI, and runs the round. 
   * @param playerThrow The player's throw for the round
   */
  public void runRound(GameThrow playerThrow) {
    GameRound round = new GameRound(playerThrow);
    round.aiThrow = currentAI.makeThrow();
    round.result = getWinner(round.playerThrow, round.aiThrow);
    // Don't set the lastRound until it's complete, because the GUI may be
    //  accessing it.
    lastRound = round;
  }

  /**
   * Changes the currently used AI.
   * @param newAI The new AI to use
   */
  public void setAI(Class newAI) {
    currentAI = savedAIs.get(newAI);
  }

  /**
   * Returns the last completed round.
   * @return The last round played
   */
  public GameRound getLastRound() {
    return lastRound;
  }

  /**
   * Returns the win-loss-draw states for the AIs.
   * @return The stats for the AIs
   */
  public HashMap<Class, int[]> getStats() {
    return aiStats;
  }

  /**
   * Returns the throw that would beat a given throw.
   * @param inThrow A valid throw
   * @return The throw that would beat inThrow
   */
  public GameThrow getInverse(GameThrow inThrow) {
    // Return the next throw in the enum declaration.
    return GameThrow.values()[(inThrow.ordinal() + 1) % 3];
  }

  /**
   * Computes the winner of a round given two throws.
   * @param throw1 The throw played by the first user
   * @param throw2 The throw played by the second user
   * @return -1 if throw1 wins, 1 if throw2 wins, else 0
   */
  public int getWinner(GameThrow throw1, GameThrow throw2) {
    if (throw1.ordinal() == throw2.ordinal()) {
      return 0;
    } else if ((throw1.ordinal() + 1) % 3 == throw2.ordinal()) {
      return 1;
    } else {
      return -1;
    }
  }

  /**
   * Collects information related to one round of the game.
   */
  public class GameRound {
    // The throw made by the player
    public GameThrow playerThrow;
    // The throw made by the AI
    public GameThrow aiThrow;
    // The outcome
    public int result;

    /**
     * Constructs a GameRound -- only the player's throw is known at time of
     *  construction.
     * @param playerThrow The player's throw for this round
     */
    public GameRound(GameThrow playerThrow) {
      this.playerThrow = playerThrow;
    }
  }
}
