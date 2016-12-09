package smartrochambeau;
/**
 * Represents the skeleton of the Game AI.
 * @author sarahpadlipsky
 * @author cesiu
 * @version October 12, 2016
 */

public interface GameAI {
  public GameModerator.GameThrow makeThrow();
  
  public void storeResult(GameModerator.GameThrow playerThrow, int result);
}
