/**
 * Contains unit tests for RandomAI.
 * @author cbrown83
 * @author cesiu
 * @version October 19, 2016
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestRandomAI
{   
   @Test 
   public void testDefaultConstructor() {
      RandomAI randomAI = new RandomAI(); 
      assertTrue("Testing RandomAI default constructor...", 
                 randomAI instanceof RandomAI);
   }
   
   @Test
   public void testSeededConstructor() {
      RandomAI randomAI = new RandomAI(3);
      assertTrue("Testing RandomAI seeded constructor...",
                 randomAI instanceof RandomAI); 
   }
   
   @Test
   public void testMakeThrow() {
      RandomAI randomAI = new RandomAI(); 
      assertTrue("Testing make throw...", randomAI.makeThrow() 
                 instanceof GameModerator.GameThrow);
   }
   
   @Test 
   public void testStoreResult() {
      RandomAI randomAI = new RandomAI(); 
      int result = 0; 
      randomAI.storeResult(randomAI.makeThrow(), result);
      // function should do nothing
      assertEquals("Testing storeResult for RandomAI does nothing...", 
                   0, result);
   }
   
   @Test
   public void testImplementsGameAI() {
      RandomAI randomAI = new RandomAI(); 
      assertTrue("Testing RandomAI implements GameAI...",
                 randomAI instanceof GameAI); 
   }
}
