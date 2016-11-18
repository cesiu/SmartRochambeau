/**
 * Contains unit tests for NaiveBayesAI.
 * @author cesiu
 * @version November 17, 2016
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestNaiveBayesAI {
  @Test 
  public void testInitialThrow() {
    NaiveBayesAI bayesAI = new NaiveBayesAI(); 
     
    assertTrue("Testing NaiveBayesAI's initial throw is random...", 
               bayesAI.makeThrow() instanceof GameModerator.GameThrow);
  }
  
  @Test
  public void testToString() {
    NaiveBayesAI bayesAI = new NaiveBayesAI(); 
    
    assertTrue("Testing toString...", bayesAI.toString() instanceof String);
  }
  
  @Test
  public void testStoreResult() {
    NaiveBayesAI bayesAI = new NaiveBayesAI();
    
    bayesAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    bayesAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    assertEquals("Testing storeResult...", bayesAI.toString(), 
     "In the past, throws leading to rock:\n   1,0,0\nLeading to paper:\n   "
     + "0,0,0\nLeading to scissors:\n   0,0,0\nAnd the recent throws are:\n   "
     + "ROCK,ROCK,\n");
  }
  
  @Test
  public void testStoreResultLong() {
    NaiveBayesAI bayesAI = new NaiveBayesAI();
    
    bayesAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    bayesAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    bayesAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    bayesAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    assertEquals("Testing storeResult kicks out a throw...", bayesAI.toString(), 
     "In the past, throws leading to rock:\n   1,1,1\nLeading to paper:\n   "
     + "1,0,0\nLeading to scissors:\n   1,1,0\nAnd the recent throws are:\n   "
     + "PAPER,SCISSORS,ROCK,\n");
  }
  
  @Test
  public void testMakeThrow() {
    NaiveBayesAI bayesAI = new NaiveBayesAI();
    
    bayesAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    bayesAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    bayesAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    bayesAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    
    assertTrue("Testing makeThrow with data...", 
               bayesAI.makeThrow() instanceof GameModerator.GameThrow);
  }
} 
