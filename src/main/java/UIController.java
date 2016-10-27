/**
 * Controls interactions between interfaces and game moderators.
 * 
 * @author cesiu
 * @version October 12, 2016
 */

public class UIController {
  private GameModerator curGame;
  private GameUI curUI;

  /**
   * Initializes the game with a GameModerator.
   * @param doRestore Whether or not to load a previous game
   */
  public UIController(boolean doRestore) {
    if (doRestore) {
      restoreGame();
    }
    else {
      curGame = new GameModerator();
    }
  }

  /**
   * Notifies the UI that it's time to run the next round.
   * @param lastRound The previous round to display, null if inapplicable.
   */
  public void runRound(GameModerator.GameRound lastRound) {

  }

  /**
   * Sets the UI for this controller to interact with.
   * @param curUI The new UI
   */
  public void setUI(GameUI curUI) {
    this.curUI = curUI;
  }

  /** 
   * Gets a reference to the game this controller is interacting with.
   * @return The current game
   */
  public GameModerator getGame() {
    return curGame;
  }

  public void saveGame() {
  }

  public void restoreGame() {
  }
}
