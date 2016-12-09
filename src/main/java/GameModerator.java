
/**
 * Represents the game moderator of the SmartRochambeau application.
 * @author sarahpadlipsky
 * @author cesiu
 * @version October 12, 2016
 */

import java.io.Serializable;
import java.util.HashMap;

public class GameModerator implements Serializable {
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
    savedAIs.put(MarkovAI.class, new MarkovAI());
    savedAIs.put(PatternMatchingAI.class, new PatternMatchingAI());
    savedAIs.put(NaiveBayesAI.class, new NaiveBayesAI());
    
    aiStats.put(RandomAI.class, new int[]{0,0,0});
    aiStats.put(MarkovAI.class, new int[]{0,0,0});
    aiStats.put(PatternMatchingAI.class, new int[]{0,0,0});
    aiStats.put(NaiveBayesAI.class, new int[]{0,0,0});
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
    currentAI.storeResult(round.playerThrow, round.result);
    
    int idx = 0;
    switch (lastRound.result) {
      case -1:
        idx = 1;
        break;
      case 1:
        idx = 0;
        break;
      default:
        idx = 2;
    }
    aiStats.get(currentAI.getClass())[idx]++;
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
  public class GameRound implements Serializable {
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

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result + ((aiThrow == null) ? 0 : aiThrow.hashCode());
      result = prime * result + ((playerThrow == null) ? 0 : playerThrow.hashCode());
      result = prime * result + this.result;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      GameRound other = (GameRound) obj;
      if (!getOuterType().equals(other.getOuterType()))
        return false;
      if (aiThrow != other.aiThrow)
        return false;
      if (playerThrow != other.playerThrow)
        return false;
      if (result != other.result)
        return false;
      return true;
    }

    private GameModerator getOuterType() {
      return GameModerator.this;
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((aiStats == null) ? 0 : aiStats.hashCode());
    result = prime * result + ((currentAI == null) ? 0 : currentAI.hashCode());
    result = prime * result + ((lastRound == null) ? 0 : lastRound.hashCode());
    result = prime * result + ((savedAIs == null) ? 0 : savedAIs.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GameModerator other = (GameModerator) obj;
    if (aiStats == null) {
      if (other.aiStats != null)
        return false;
    } else if (!aiStats.equals(other.aiStats))
      return false;
    if (currentAI == null) {
      if (other.currentAI != null)
        return false;
    } else if (!currentAI.equals(other.currentAI))
      return false;
    if (lastRound == null) {
      if (other.lastRound != null)
        return false;
    } else if (!lastRound.equals(other.lastRound))
      return false;
    if (savedAIs == null) {
      if (other.savedAIs != null)
        return false;
    } else if (!savedAIs.equals(other.savedAIs))
      return false;
    return true;
  }
}
