/**
 * Manages a command-line interface for a game of SmartRochambeau.
 * @author cesiu
 * @version October 12, 2016
 */

public class ConsoleUI implements GameUI 
{
   private GameModerator.GameRound lastRound;
   private UIController curController;

   public ConsoleUI(UIController curController) {
      this.curController = curController;
   }

   public void display() {
   }

   public void run() {
   }

   private GameModerator.GameThrow getThrow() {
      return GameModerator.GameThrow.ROCK;
   }

   private void showStats() {
   }

   private void deconstruct() {
   }
}
