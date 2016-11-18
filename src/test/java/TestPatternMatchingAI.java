/**
 * Contains unit tests for PatternMatchingAI.
 * @author cesiu
 * @version November 17, 2016
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestPatternMatchingAI {
  @Test 
  public void testInitialThrow() {
    PatternMatchingAI patternAI = new PatternMatchingAI(); 
     
    assertTrue("Testing PatternMatchingAI's initial throw is random...", 
               patternAI.makeThrow() instanceof GameModerator.GameThrow);
  }
  
  @Test
  public void testToString() {
    PatternMatchingAI patternAI = new PatternMatchingAI(); 
    
    assertTrue("Testing toString...", patternAI.toString() instanceof String);
  }
  
  @Test 
  public void testLearningBasic() {
    PatternMatchingAI patternAI = new PatternMatchingAI(); 
    
    // Pattern is rock-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is paper-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-rock-rock. 
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is rock-rock-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is rock-rock-rock.

    assertEquals("Testing PatternMatchingAI basic learning...", 
                 patternAI.makeThrow(), GameModerator.GameThrow.PAPER);
  }
  
  @Test 
  public void testLearningScissors() {
    PatternMatchingAI patternAI = new PatternMatchingAI(); 
    
    // Pattern is rock-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is paper-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-rock-rock. 
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is rock-rock-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is rock-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-rock-rock. 

    assertEquals("Testing PatternMatchingAI basic learning...", 
                 patternAI.makeThrow(), GameModerator.GameThrow.ROCK);
  }
  
  @Test 
  public void testLearningCombined() {
    PatternMatchingAI patternAI = new PatternMatchingAI(); 
    
    // Pattern is rock-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is paper-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-rock-rock. 
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is rock-rock-paper.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is rock-paper-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is paper-rock-rock. 
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is rock-rock-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is rock-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-paper-rock. 
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-rock-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is rock-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is paper-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is scissors-rock-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is rock-paper-scissors.
    
    assertEquals("Testing PatternMatchingAI basic learning...", 
                 patternAI.makeThrow(), GameModerator.GameThrow.PAPER);
  }
  
  @Test 
  public void testLearningRockScissors1() {
    PatternMatchingAI patternAI = new PatternMatchingAI(); 
    
    // Pattern is rock-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is paper-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-rock-rock.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is rock-rock-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is rock-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-rock-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is rock-rock-rock.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is rock-rock-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is rock-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-rock-rock.
    
    GameModerator.GameThrow result = patternAI.makeThrow();
    assertTrue("Testing PatternMatchingAI basic learning...", 
               result == GameModerator.GameThrow.PAPER
               || result == GameModerator.GameThrow.ROCK);
  }
  
  @Test 
  public void testLearningRockScissors2() {
    PatternMatchingAI patternAI = new PatternMatchingAI(); 
    
    // Pattern is rock-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is paper-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-rock-rock.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is rock-rock-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is rock-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-rock-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is rock-rock-rock.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is rock-rock-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is rock-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-rock-rock.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is rock-rock-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is rock-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-rock-rock.
    
    assertEquals("Testing PatternMatchingAI basic learning...", 
                 patternAI.makeThrow(), GameModerator.GameThrow.ROCK);
  }
  
  @Test 
  public void testLearningPaperScissors1() {
    PatternMatchingAI patternAI = new PatternMatchingAI(); 
    
    // Pattern is rock-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is scissors-paper-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is paper-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is scissors-paper-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-paper-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is paper-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is scissors-paper-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is paper-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is scissors-paper-paper.
    
    assertEquals("Testing PatternMatchingAI basic learning...", 
                 patternAI.makeThrow(), GameModerator.GameThrow.ROCK);
  }
  
  @Test 
  public void testLearningPaperScissors2() {
    PatternMatchingAI patternAI = new PatternMatchingAI(); 
    
    // Pattern is rock-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is scissors-paper-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is paper-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is scissors-paper-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-paper-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is paper-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is scissors-paper-paper.
    
    GameModerator.GameThrow result = patternAI.makeThrow();
    assertTrue("Testing PatternMatchingAI basic learning...", 
               result == GameModerator.GameThrow.SCISSORS
               || result == GameModerator.GameThrow.ROCK);
  }
  
  @Test 
  public void testLearningPaperScissors3() {
    PatternMatchingAI patternAI = new PatternMatchingAI(); 
    
    // Pattern is rock-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is scissors-paper-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is paper-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is scissors-paper-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-paper-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is paper-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is scissors-paper-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-paper-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is paper-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is scissors-paper-paper.
    
    assertEquals("Testing PatternMatchingAI basic learning...", 
                 patternAI.makeThrow(), GameModerator.GameThrow.SCISSORS);
  }
  
  @Test 
  public void testLearningRockPaper() {
    PatternMatchingAI patternAI = new PatternMatchingAI(); 
    
    // Pattern is rock-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-scissors-paper.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is scissors-paper-rock.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-rock-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is rock-paper-scissors.
    patternAI.storeResult(GameModerator.GameThrow.ROCK, -1);
    // Pattern is paper-scissors-rock.
    patternAI.storeResult(GameModerator.GameThrow.PAPER, -1);
    // Pattern is paper-rock-paper.
    patternAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
    // Pattern is rock-paper-scissors.
    
    
    GameModerator.GameThrow result = patternAI.makeThrow();
    assertTrue("Testing PatternMatchingAI basic learning...", 
               result == GameModerator.GameThrow.SCISSORS
               || result == GameModerator.GameThrow.PAPER);
  }
} 
