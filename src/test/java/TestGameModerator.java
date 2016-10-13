/**
 * Contains unit tests for GameModerator.
 * @author cesiu
 * @version October 12, 2016
 */

import static org.junit.Assert.*;
import org.junit.Test;

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
}
