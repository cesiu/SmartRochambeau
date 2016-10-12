/**
 * Represents the game moderator of the SmartRochambeau application.
 * @author sarahpadlipsky
 * @version October 11, 2016
 */

import java.util.HashMap; 

public class GameModerator {
   // Enumeration of possible throws
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
   }

   /**
    * @param round The GameRound to run
    */
   public void runRound(GameRound round) {
   }

   /**
    * @param newAI The new AI to use
    */
   public void setAI(GameAI newAI) {
      currentAI = newAI;
   }

   /**
    * @return The last round played
    */
   public GameRound getLastRound() {
      return lastRound;
   }

   /**
    * @return The stats for the AIs
    */
   public HashMap<Class, int[]> getStats() {
      return aiStats;
   }

   /**
    * @param throw The throw played by the player
    * @return The inverse for the incoming throw 
    */
   public GameThrow getInverse(GameThrow throw1) {
      return GameThrow.ROCK;   
   }

   /**
    * @param throw1 The throw played by the first user
    * @param throw2 The throw played by the second user
    * @return The winner of the round
    */
   private int getWinner(GameThrow throw1, GameThrow throw2) {
      return 0;
   }

   private class GameRound {
      // The throw made by the player
      public GameThrow playerThrow;
      // The throw made by the AI
      public GameThrow aiThrow;
      // The player who won the round
      public boolean playerWon;
   }
}
