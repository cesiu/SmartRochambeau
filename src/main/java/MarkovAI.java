/*
 * A Rochambeau AI that makes throws based on Markov Chains.
 * @author cesiu
 * @version October 18, 2016
 */

import java.util.LinkedHashMap;
import java.util.Random;

public class MarkovAI implements GameAI 
{
   // The current state of the game
   private GameState curState;

   public MarkovAI() {
      // Create the master hash map that contains all the states.
      LinkedHashMap<GameModerator.GameThrow, GameState[]> allStates 
       = new LinkedHashMap<>();
      
      // Create all the states and add them to the master map.
      for (GameModerator.GameThrow tempThrow 
           : GameModerator.GameThrow.values()) {
         allStates.put(tempThrow, new GameState[] {
                       new GameState(tempThrow, -1),
                       new GameState(tempThrow, 0),
                       new GameState(tempThrow, 1) });
         // Every state has the same copy of the master map of states. They
         //  only need different frequencies.
         for (GameState tempState : allStates.get(tempThrow)) {
            tempState.nextStates = allStates;
         }
      }

      curState = allStates.get(GameModerator.GameThrow.ROCK)[0];
   }

   /**
    * Checks the current state, makes a prediction, and returns the next throw.
    * @return A valid GameThrow
    */
   public GameModerator.GameThrow makeThrow() {
      return null;
   }

   /**
    * Updates the Markov Chain with the result of the last round.
    * @param playerThrow What the player threw last round
    * @param result The result of the last round
    */
   public void storeResult(GameModerator.GameThrow playerThrow, int result) {
      curState.nextFreqs.put(playerThrow,
                             curState.nextFreqs.get(playerThrow) + 1);
      curState = curState.nextStates.get(playerThrow)[result + 1];
   }

   private class GameState
   {
      // The throw represented by this state
      GameModerator.GameThrow curThrow;
      // The result represented by this state
      int result;
      // References to the other states, where the array is indexed as:
      //  0: player win, 1: draw, 2: AI win
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
         return "This state represents a " + curThrow + ": " + result
                + "\n   Frequencies are:\n   Rock: " + nextFreqs
                .get(GameModerator.GameThrow.ROCK) + "\n   Paper: " + nextFreqs
                .get(GameModerator.GameThrow.PAPER) + "\n   Scissors: " 
                + nextFreqs.get(GameModerator.GameThrow.SCISSORS);
      }
   }
}
