package smartrochambeau;

/**
 * A Rochambeau AI that makes throws randomly.
 * @author cesiu
 * @version October 12, 2016
 */

import java.io.Serializable;
import java.util.Random;

public class RandomAI implements GameAI, Serializable {
  // A random number generator for use making throws.
  private Random randGen;

  /**
   * Creates a random AI.
   */
  public RandomAI() {
    randGen = new Random();
  }

  /**
   * Creates a seeded random AI.
   * @param seed The seed
   */
  public RandomAI(int seed) {
    randGen = new Random(seed);
  }

  /**
   * Makes a random choice of throw.
   * @return A valid GameThrow
   */
  @Override
  public GameModerator.GameThrow makeThrow() {
    return GameModerator.GameThrow.values()[randGen.nextInt(3)];
  }

  /**
   * This method does nothing; a Random AI has no machine learning component, so
   * it has no need to know what happened in the past.
   */
  @Override
  public void storeResult(GameModerator.GameThrow playerThrow, int result) {
  }
}
