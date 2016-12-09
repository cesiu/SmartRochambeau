package smartrochambeau;

import java.util.Random;
import java.io.Serializable;

/**
 * Represents the skeleton of the Game AI.
 * @author sarahpadlipsky
 * @author cesiu
 * @version October 12, 2016
 */

public abstract class GameAI implements Serializable {
  /**
   * Makes a throw.
   * @return A valid throw
   */
  public abstract GameModerator.GameThrow makeThrow();
  
  /**
   * Stores a result.
   * @param playerThrow The last throw
   * @param result The result of the last round
   */
  public abstract void storeResult(GameModerator.GameThrow playerThrow, int result);
  
  /**
   * Chooses a throw based on past frequencies. Utility function for different AI to use.
   * @param numRocks The number of past rocks
   * @param numPapers The number of past papers
   * @paran numScissors The number of past scissors
   * @return
   */
  public static GameModerator.GameThrow analyzeThrow(int numRocks, int numPapers, int numScissors) {
    Random randGen = new Random();
    
    if (numRocks > numPapers) {
      if (numRocks > numScissors) {
        // More rocks than papers or scissors -- throw paper.
        return GameModerator.GameThrow.PAPER;
      }
      else if (numRocks < numScissors) {
        // More scissors than rocks or papers -- throw rock.
        return GameModerator.GameThrow.ROCK;
      }
      else {
        // Scissors and rocks tied for first.
        if (randGen.nextInt(2) == 0) {
          return GameModerator.GameThrow.PAPER;
        }
        else {
          return GameModerator.GameThrow.ROCK;
        }
      }
    }
    else if (numRocks < numPapers) {
      if (numPapers > numScissors) {
        // More papers than rocks or scissors -- throw scissors.
        return GameModerator.GameThrow.SCISSORS;
      }
      else if (numPapers < numScissors) {
        // More scissors than rocks or papers -- throw rock.
        return GameModerator.GameThrow.ROCK;
      }
      else {
        // Scissors and papers tied for first.
        if (randGen.nextInt(2) == 0) {
          return GameModerator.GameThrow.SCISSORS;
        }
        else {
          return GameModerator.GameThrow.ROCK;
        }
      }
    }
    else {
      if (numScissors > numRocks) {
        // Rocks and papers are tied. If there are more scissors, throw rock.
        return GameModerator.GameThrow.ROCK;
      }
      else if (numScissors < numRocks) {
        // Rocks and papers tied for first.
        if (randGen.nextInt(2) == 0) {
          return GameModerator.GameThrow.PAPER;
        }
        else {
          return GameModerator.GameThrow.SCISSORS;
        }
      }
      // All three tied.
      return GameModerator.GameThrow.values()[randGen.nextInt(3)];
    }
  }
}
