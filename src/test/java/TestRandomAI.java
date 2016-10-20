/**
 * Contains unit tests for RandomAI.
 * @author cesiu
 * @author cbrown83
 * @version October 19, 2016
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestRandomAI {
	
	private RandomAI randomAI = new RandomAI(); 
	
	@Test 
	public void testEmptyConstructor() {
		assertTrue("Testing RandomAI empty constructor...", 
					randomAI instanceof RandomAI);
	}
	
	@Test
	public void testNonEmptyConstructor() {
		randomAI = new RandomAI(3);
		assertTrue("Testing RandomAI non-empty constructor...",
					randomAI instanceof RandomAI); 
	}
	
	
	@Test
	public void testMakeThrow() {
		assertTrue("Testing make throw...", 
					randomAI.makeThrow() instanceof GameModerator.GameThrow);
	}
	
	@Test 
	public void testStoreResult() {
		int result = 0; 
		randomAI.storeResult(randomAI.makeThrow(), result);
		// function should do nothing
		assertEquals("Testing storeResult for RandomAI does nothing...", 
					0, result);
	}
	
	@Test
	public void testImplementsGameAI() {
		assertTrue("Testing RandomAI implements GameAI...",
					randomAI instanceof GameAI); 
	}
}
