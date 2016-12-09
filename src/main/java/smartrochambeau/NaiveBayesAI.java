package smartrochambeau;
/*
 * A Rochambeau AI that makes throws based on Bayes' rule.
 * @author cesiu
 * @version November 17, 2016
 */

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.LinkedHashMap;
import java.util.Random;

public class NaiveBayesAI extends GameAI implements Serializable {
  // The number of recent throws to keep.
  public static final int MAX_RECENT = 3;
  // The seen rock-prior throw frequencies.
  private int[] rockPriorThrows;
  // The seen paper-prior throw frequencies.
  private int[] paperPriorThrows;
  // The seen scissors-prior throw frequencies.
  private int[] scissorsPriorThrows;
  // The recently seen throws.
  private ArrayBlockingQueue<GameModerator.GameThrow> recentThrows;
  // A random number generator
  private Random randGen;

  /**
   * Constructs a NaiveBayesAI with no training.
   */
  public NaiveBayesAI() {
    rockPriorThrows = new int[] {0, 0, 0}; 
    paperPriorThrows = new int[] {0, 0, 0}; 
    scissorsPriorThrows = new int[] {0, 0, 0}; 
    recentThrows = new ArrayBlockingQueue<>(MAX_RECENT);
    randGen = new Random();
  }

  /**
   * Checks the current state, makes a prediction, and returns the next throw.
   * 
   * @return A valid GameThrow
   */
  @Override
  public GameModerator.GameThrow makeThrow() {
    int numRocks = 1, numPapers = 1, numScissors = 1;

    // For each of the recent throws, check how often that throw has lead to
    //  each throw.
    for (GameModerator.GameThrow tempThrow : recentThrows) {
      numRocks += rockPriorThrows[tempThrow.ordinal()];
      numPapers += paperPriorThrows[tempThrow.ordinal()];
      numScissors += scissorsPriorThrows[tempThrow.ordinal()];
    }

    // Since Naive Bayes gives us a general probability over the history, and
    //  isn't that sensitive to abrupt changes, always include some element of
    //  randomness.
    int temp = randGen.nextInt(numRocks + numPapers + numScissors);
    if (temp < numRocks) {
      return GameModerator.GameThrow.PAPER;
    }
    else if (temp < numPapers) {
      return GameModerator.GameThrow.SCISSORS;
    }
    else {
      return GameModerator.GameThrow.ROCK;
    }
  }

  /**
   * Updates the frequencies with the result of the last round.
   * 
   * @param playerThrow What the player threw last round
   * @param result The result of the last round
   */
  @Override
  public void storeResult(GameModerator.GameThrow playerThrow, int result) {
    // For each recent throw, update the frequencies.
    for (GameModerator.GameThrow tempThrow : recentThrows) {
      if (playerThrow == GameModerator.GameThrow.ROCK) {
        ++rockPriorThrows[tempThrow.ordinal()];
      }
      else if (playerThrow == GameModerator.GameThrow.PAPER) {
        ++paperPriorThrows[tempThrow.ordinal()];
      }
      else {
        ++scissorsPriorThrows[tempThrow.ordinal()];
      }
    }

    // Save the throw, kicking an older one out if necessary.
    if (recentThrows.size() == MAX_RECENT) {
      recentThrows.remove();
    }
    recentThrows.add(playerThrow);
  }
  
  /**
   * Stringifies the current state of this AI.
   *
   * @return The string
   */
  @Override
  public String toString() {
    String retStr = "In the past, throws leading to rock:\n   "
                    + rockPriorThrows[0] + "," + rockPriorThrows[1] + ","
                    + rockPriorThrows[2] + "\nLeading to paper:\n   "
                    + paperPriorThrows[0] + "," + paperPriorThrows[1] + ","
                    + paperPriorThrows[2] + "\nLeading to scissors:\n   "
                    + scissorsPriorThrows[0] + "," + scissorsPriorThrows[1] + ","
                    + scissorsPriorThrows[2] + "\nAnd the recent throws are:\n   ";

    for (GameModerator.GameThrow tempThrow : recentThrows) {
      retStr += tempThrow.toString() + ",";
    }
    
    return retStr + "\n";
  }
}
