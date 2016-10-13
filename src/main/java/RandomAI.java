/**
 * A Rochambeau AI that makes throws randomly.
 * @author cesiu
 * @version October 12, 2016
 */

import java.util.Random;

public class RandomAI implements GameAI 
{
   // A random number generator for use making throws.
   private Random randGen;

   public RandomAI() {
      randGen = new Random();
   }

   public RandomAI(int seed) {
      randGen = new Random(seed);
   }

   /**
    * @return A valid GameThrow
    */
   public GameModerator.GameThrow makeThrow() {
      return GameModerator.GameThrow.values()[randGen.nextInt(3)];
   }

   /**
    * This method does nothing; a Random AI has no machine learning component,
    *  so it has no need to know what happened in the past.
    */
   public void storeResult(GameModerator.GameThrow playerThrow, int result) {
   }
}
