package smartrochambeau;
/**
 * Contains unit tests for GameModerator.
 * @author cesiu
 * @version October 12, 2016
 */

import static org.junit.Assert.*;
import org.junit.Test;

import smartrochambeau.GameModerator;

public class TestGameModerator
{
   @Test
   public void testGetInverse() {
      GameModerator tempMod = new GameModerator();

      assertEquals("Testing inverse of Rock...",
                   GameModerator.GameThrow.PAPER,
                   tempMod.getInverse(GameModerator.GameThrow.ROCK));
      assertEquals("Testing inverse of Paper...",
                   GameModerator.GameThrow.SCISSORS,
                   tempMod.getInverse(GameModerator.GameThrow.PAPER));
      assertEquals("Testing inverse of Scissors...",
                   GameModerator.GameThrow.ROCK,
                   tempMod.getInverse(GameModerator.GameThrow.SCISSORS));
   }
   
   @Test
   public void testGetWinner() {
      GameModerator tempMod = new GameModerator();

      assertEquals("Testing winner of Rock and Rock...", 0,
                    tempMod.getWinner(GameModerator.GameThrow.ROCK,
                    GameModerator.GameThrow.ROCK));
      assertEquals("Testing winner of Rock and Paper...", 1,
                    tempMod.getWinner(GameModerator.GameThrow.ROCK,
                    GameModerator.GameThrow.PAPER));
      assertEquals("Testing winner of Rock and Scissors...", -1,
                    tempMod.getWinner(GameModerator.GameThrow.ROCK,
                    GameModerator.GameThrow.SCISSORS));
      assertEquals("Testing winner of Paper and Paper...", 0,
                    tempMod.getWinner(GameModerator.GameThrow.PAPER,
                    GameModerator.GameThrow.PAPER));
      assertEquals("Testing winner of Paper and Scissors...", 1,
                    tempMod.getWinner(GameModerator.GameThrow.PAPER,
                    GameModerator.GameThrow.SCISSORS));
      assertEquals("Testing winner of Paper and Rock...", -1,
                    tempMod.getWinner(GameModerator.GameThrow.PAPER,
                    GameModerator.GameThrow.ROCK));
      assertEquals("Testing winner of Scissors and Scissors...", 0,
                    tempMod.getWinner(GameModerator.GameThrow.SCISSORS,
                    GameModerator.GameThrow.SCISSORS));
      assertEquals("Testing winner of Scissors and Rock...", 1,
                    tempMod.getWinner(GameModerator.GameThrow.SCISSORS,
                    GameModerator.GameThrow.ROCK));
      assertEquals("Testing winner of Scissors and Paper...", -1,
                    tempMod.getWinner(GameModerator.GameThrow.SCISSORS,
                    GameModerator.GameThrow.PAPER));
   }
   
   @Test
   public void testRunRound() {
     GameModerator tempMod = new GameModerator();
     tempMod.setAI(PatternMatchingAI.class);
     tempMod.runRound(GameModerator.GameThrow.ROCK);
     tempMod.runRound(GameModerator.GameThrow.ROCK);
     tempMod.runRound(GameModerator.GameThrow.ROCK);
     tempMod.runRound(GameModerator.GameThrow.ROCK);
     tempMod.runRound(GameModerator.GameThrow.ROCK);
     tempMod.runRound(GameModerator.GameThrow.ROCK);
     
     assertEquals("Testing runRound...", tempMod.getLastRound().result, 1);
     
     tempMod.runRound(GameModerator.GameThrow.SCISSORS);
     tempMod.runRound(GameModerator.GameThrow.SCISSORS);
     tempMod.runRound(GameModerator.GameThrow.SCISSORS);
     tempMod.runRound(GameModerator.GameThrow.SCISSORS);
     tempMod.runRound(GameModerator.GameThrow.SCISSORS);
     tempMod.runRound(GameModerator.GameThrow.ROCK);
     
     assertEquals("Testing runRound...", tempMod.getLastRound().result, 0);
     
     tempMod.runRound(GameModerator.GameThrow.PAPER);
     tempMod.runRound(GameModerator.GameThrow.PAPER);
     tempMod.runRound(GameModerator.GameThrow.PAPER);
     tempMod.runRound(GameModerator.GameThrow.PAPER);
     tempMod.runRound(GameModerator.GameThrow.PAPER);
     tempMod.runRound(GameModerator.GameThrow.ROCK);
     
     assertEquals("Testing runRound...", tempMod.getLastRound().result, -1);
   }
   
   @Test
   public void testGetStats() {
     GameModerator tempMod = new GameModerator();
     assertEquals("Testing getStats...", tempMod.getStats().size(), 4);
   }
   
   @Test
   public void testHashCode() {
     GameModerator tempMod1 = new GameModerator();
     GameModerator tempMod2 = new GameModerator();
     
     assertNotEquals("Testing hashCode...", tempMod1.hashCode(), tempMod2.hashCode());
   }
   
   @Test
   public void testEquals() {
     GameModerator tempMod1 = new GameModerator();
     GameModerator tempMod2 = new GameModerator();
     
     assertTrue("Testing equals...", tempMod1.equals(tempMod1));
     assertFalse("Testing equals...", tempMod1.equals(null));
     assertFalse("Testing equals...", tempMod1.equals("foo"));
     
     tempMod2.setAI(RandomAI.class);
     tempMod2.runRound(GameModerator.GameThrow.ROCK);
     assertFalse("Testing equals...", tempMod1.equals(tempMod2));
   }
   
   @Test
   public void testGameRound() {
     GameModerator tempMod = new GameModerator();
     tempMod.setAI(RandomAI.class);
     tempMod.runRound(GameModerator.GameThrow.ROCK);
     GameModerator.GameRound tempRound1 = tempMod.getLastRound();
     tempMod.runRound(GameModerator.GameThrow.PAPER);
     GameModerator.GameRound tempRound2 = tempMod.getLastRound();

     assertTrue("Testing GameRound...", tempRound1.equals(tempRound1));
     assertFalse("Testing GameRound...", tempRound1.equals(null));
     assertFalse("Testing GameRound...", tempRound1.equals("bar"));
     assertFalse("Testing GameRound...", tempRound1.equals(tempRound2));
     assertNotEquals("Testing GameRound...", 
                     tempRound1.hashCode(), tempRound2.hashCode());
   }
}
