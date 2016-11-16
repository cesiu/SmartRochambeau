
/*
 * A Rochambeau AI that makes throws based on Markov Chains.
 * @author cesiu
 * @version October 18, 2016
 */

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Random;

public class MarkovAI implements GameAI, Serializable {
  // The current state of the game
  private GameState curState;
  // A random number generator
  private Random randGen;

  /**
   * Constructs a MarkovAI with no training.
   */
  public MarkovAI() {
    // Create the master hash map that contains all the states.
    LinkedHashMap<GameModerator.GameThrow, GameState[]> allStates 
     = new LinkedHashMap<>();
    // Initialize the RNG.
    randGen = new Random();

    // Create all the states and add them to the master map.
    for (GameModerator.GameThrow tempThrow : GameModerator.GameThrow.values()) {
      allStates.put(tempThrow,
          new GameState[] { new GameState(tempThrow, -1), 
                            new GameState(tempThrow, 0), 
                            new GameState(tempThrow, 1) });
      // Every state has the same copy of the master map of states. They
      // only need different frequencies.
      for (GameState tempState : allStates.get(tempThrow)) {
        tempState.nextStates = allStates;
      }
    }

    curState = allStates.get(GameModerator.GameThrow.ROCK)[0];
  }

  /**
   * Checks the current state, makes a prediction, and returns the next throw.
   * 
   * @return A valid GameThrow
   */
  public GameModerator.GameThrow makeThrow() {
    int numRocks = curState.nextFreqs.get(GameModerator.GameThrow.ROCK);
    int numPapers = curState.nextFreqs.get(GameModerator.GameThrow.PAPER);
    int numScissors = curState.nextFreqs.get(GameModerator.GameThrow.SCISSORS);

    //System.out.println("\nThe current state is: (" + curState.curThrow + "," + curState.result + ")\n   numRocks: " + numRocks + "\n   numPapers: " + numPapers + "\n   numScissors: " + numScissors + "\n");

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

  /**
   * Updates the Markov Chain with the result of the last round.
   * 
   * @param playerThrow
   *          What the player threw last round
   * @param result
   *          The result of the last round
   */
  public void storeResult(GameModerator.GameThrow playerThrow, int result) {
    curState.nextFreqs.put(playerThrow, curState.nextFreqs.get(playerThrow) + 1);
    curState = curState.nextStates.get(playerThrow)[result + 1];
  }

  private class GameState implements Serializable {
    // The throw represented by this state
    GameModerator.GameThrow curThrow;
    // The result represented by this state
    int result;
    // References to the other states, where the array is indexed as:
    // 0: player win, 1: draw, 2: AI win
    LinkedHashMap<GameModerator.GameThrow, GameState[]> nextStates;
    // Frequencies of the next states
    LinkedHashMap<GameModerator.GameThrow, Integer> nextFreqs;

    private GameState(GameModerator.GameThrow curThrow, int result) {
      this.curThrow = curThrow;
      this.result = result;

      nextFreqs = new LinkedHashMap<GameModerator.GameThrow, Integer>();
      nextFreqs.put(GameModerator.GameThrow.ROCK, 0);
      nextFreqs.put(GameModerator.GameThrow.PAPER, 0);
      nextFreqs.put(GameModerator.GameThrow.SCISSORS, 0);
    }

    public String toString() {
      return "This state represents a " + curThrow + ": " + result + "\n   "
             + "Frequencies are:\n   Rock: " + nextFreqs.get(GameModerator
             .GameThrow.ROCK) + "\n   Paper: " + nextFreqs.get(GameModerator
             .GameThrow.PAPER) + "\n   Scissors: " + nextFreqs.get(GameModerator
             .GameThrow.SCISSORS);
    }
  }
}
