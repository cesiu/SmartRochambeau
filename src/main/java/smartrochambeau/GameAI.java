package smartrochambeau;

import java.io.Serializable;

/**
 * Represents the skeleton of the Game AI.
 * @author sarahpadlipsky
 * @author cesiu
 * @version October 12, 2016
 */

public interface GameAI extends Serializable {
  /**
   * Makes a throw.
   * @return A valid throw
   */
  public GameModerator.GameThrow makeThrow();
  
  /**
   * Stores a result.
   * @param playerThrow The last throw
   * @param result The result of the last round
   */
  public void storeResult(GameModerator.GameThrow playerThrow, int result);
}
