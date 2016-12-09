package smartrochambeau;
/**
 * Contains unit tests for MarkovAI.
 * @author cesiu
 * @version November 16, 2016
 */

import static org.junit.Assert.*;

import org.junit.Test;

import smartrochambeau.GameModerator;
import smartrochambeau.MarkovAI;

public class TestMarkovAI {
  @Test 
  public void testInitialThrow() {
     MarkovAI markovAI = new MarkovAI(); 
     
     assertTrue("Testing MarkovAI's initial throw is random...", 
                markovAI.makeThrow() instanceof GameModerator.GameThrow);
  }
  
  @Test
  public void testToString() {
    MarkovAI markovAI = new MarkovAI(); 
    
    assertTrue("Testing toString...", markovAI.toString() instanceof String);
  }
  
  
  @Test 
  public void testStoreResult() {
     MarkovAI markovAI = new MarkovAI(); 
     
     // From rock-win, play rock.
     markovAI.storeResult(GameModerator.GameThrow.ROCK, -1);
     // Current state is now rock-win.
     
     assertEquals("Testing MarkovAI basic learning...", 
                  markovAI.makeThrow(), GameModerator.GameThrow.PAPER);
  }
  
  @Test 
  public void testLearningBasicCombined() {
     MarkovAI markovAI = new MarkovAI(); 
     
     // From rock-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play rock.
     markovAI.storeResult(GameModerator.GameThrow.ROCK, -1);
     // Current state is now rock-win.
     
     assertEquals("Testing MarkovAI basic learning...", 
                  markovAI.makeThrow(), GameModerator.GameThrow.ROCK);
  }
  
  @Test 
  public void testLearningBasicPaper() {
     MarkovAI markovAI = new MarkovAI(); 
     
     // From rock-win, play paper.
     markovAI.storeResult(GameModerator.GameThrow.PAPER, -1);
     // From paper-win, play paper.
     markovAI.storeResult(GameModerator.GameThrow.PAPER, -1);
     // Current state is now paper-win.
     
     assertEquals("Testing MarkovAI basic learning...", 
                  markovAI.makeThrow(), GameModerator.GameThrow.SCISSORS);
  }
  
  @Test 
  public void testLearningBasicScissors() {
     MarkovAI markovAI = new MarkovAI(); 
     
     // From rock-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // Current state is now scissors-win.
     
     assertEquals("Testing MarkovAI basic learning...", 
                  markovAI.makeThrow(), GameModerator.GameThrow.ROCK);
  }
  
  @Test 
  public void testLearningBasicRockScissors1() {
     MarkovAI markovAI = new MarkovAI(); 
    
     // From rock-win, play rock.
     markovAI.storeResult(GameModerator.GameThrow.ROCK, -1);
     // From rock-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play rock.
     markovAI.storeResult(GameModerator.GameThrow.ROCK, -1);
     // From rock-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play rock.
     markovAI.storeResult(GameModerator.GameThrow.ROCK, -1);
     // Current state is now rock-win.
     
     assertEquals("Testing MarkovAI basic learning...", 
                  markovAI.makeThrow(), GameModerator.GameThrow.ROCK);
  }
  
  @Test 
  public void testLearningBasicRockScissors2() {
     MarkovAI markovAI = new MarkovAI(); 
     
     // From rock-win, play rock.
     markovAI.storeResult(GameModerator.GameThrow.ROCK, -1);
     // From rock-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play rock.
     markovAI.storeResult(GameModerator.GameThrow.ROCK, -1);
     // Current state is now rock-win.
     
     GameModerator.GameThrow temp = markovAI.makeThrow();
     assertTrue("Testing MarkovAI basic learning...", 
                temp == GameModerator.GameThrow.PAPER
                || temp == GameModerator.GameThrow.ROCK);
  }
  
  @Test 
  public void testLearningBasicScissorsPaper1() {
     MarkovAI markovAI = new MarkovAI(); 
    
     // From rock-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play paper.
     markovAI.storeResult(GameModerator.GameThrow.PAPER, -1);
     // From paper-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // Current state is now scissors-win.
     
     assertEquals("Testing MarkovAI basic learning...", 
                  markovAI.makeThrow(), GameModerator.GameThrow.ROCK);
  }
  
  @Test 
  public void testLearningBasicScissorsPaper2() {
     MarkovAI markovAI = new MarkovAI(); 
    
     // From rock-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play paper.
     markovAI.storeResult(GameModerator.GameThrow.PAPER, -1);
     // From paper-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play paper.
     markovAI.storeResult(GameModerator.GameThrow.PAPER, -1);
     // From paper-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // Current state is now scissors-win.
     
     assertEquals("Testing MarkovAI basic learning...", 
                  markovAI.makeThrow(), GameModerator.GameThrow.SCISSORS);
  }
  
  @Test 
  public void testLearningBasicScissorsPaper3() {
     MarkovAI markovAI = new MarkovAI(); 
    
     // From rock-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // From scissors-win, play paper.
     markovAI.storeResult(GameModerator.GameThrow.PAPER, -1);
     // From paper-win, play scissors.
     markovAI.storeResult(GameModerator.GameThrow.SCISSORS, -1);
     // Current state is now scissors-win.
     
     GameModerator.GameThrow temp = markovAI.makeThrow();
     assertTrue("Testing MarkovAI basic learning...", 
                temp == GameModerator.GameThrow.SCISSORS
                || temp == GameModerator.GameThrow.ROCK);
  }
  
  @Test 
  public void testLearningBasicRockPaper() {
     MarkovAI markovAI = new MarkovAI(); 
    
     // From rock-win, play rock.
     markovAI.storeResult(GameModerator.GameThrow.ROCK, -1);
     // From rock-win, play paper.
     markovAI.storeResult(GameModerator.GameThrow.PAPER, -1);
     // From paper-win, play rock.
     markovAI.storeResult(GameModerator.GameThrow.ROCK, -1);
     // Current state is now rock-win.
     
     GameModerator.GameThrow temp = markovAI.makeThrow();
     assertTrue("Testing MarkovAI basic learning...", 
                temp == GameModerator.GameThrow.SCISSORS
                || temp == GameModerator.GameThrow.PAPER);
  }
}


