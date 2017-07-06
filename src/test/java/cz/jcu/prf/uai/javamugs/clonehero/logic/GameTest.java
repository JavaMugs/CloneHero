package cz.jcu.prf.uai.javamugs.clonehero.logic;

import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * Game logic test.
 * @author Daniel Hryzbil
 */
public class GameTest extends TestCase {
	
	private static final double TIME_OFFSET = 1000.0;
	private static final byte DIFFICULTY = 2;
	
	public void testGameLogic() {
		ArrayList<Press> presses = new ArrayList<Press>();
		presses.add(new Press(Chord.RED,      1000.0));
		presses.add(new Press(Chord.YELLOW,   2000.0));
		presses.add(new Press(Chord.MAGENTA,  3000.0));
		presses.add(new Press(Chord.MAGENTA,  4040.0));
		presses.add(new Press(Chord.MAGENTA,  4080.0));
		presses.add(new Press(Chord.RED,      5000.0));
		presses.add(new Press(Chord.RED,      6000.0));
		presses.add(new Press(Chord.RED,      7000.0));
        presses.add(new Press(Chord.BLUE,     8000.0));
        presses.add(new Press(Chord.MAGENTA,  9000.0));
        presses.add(new Press(Chord.MAGENTA, 10000.0));
        presses.add(new Press(Chord.MAGENTA, 11000.0));
        presses.add(new Press(Chord.MAGENTA, 12000.0));
        presses.add(new Press(Chord.YELLOW,  13000.0));
        presses.add(new Press(Chord.YELLOW,  14000.0));
        presses.add(new Press(Chord.YELLOW,  15000.0));
		
        PressChart pressChart = new PressChart(presses);
		Game game = new Game(TIME_OFFSET, DIFFICULTY, pressChart);
		assertNotNull(game);
		
		Chord userInput = new Chord(false, false, false, false, true);  // MAGENTA
		Chord emptyInput = new Chord(false, false, false, false, false);
		GameReport report;
		double currentTime = 0;
		long expectedScore = 0;
		double expectedMultiplier = 1.0;
		
		// no keys are pressed
		for (int i = 0; i < 5; i++) {
			report = game.tick(currentTime, emptyInput);
			assertEquals(expectedScore, report.getScore());
			assertEquals(expectedMultiplier, report.getMultiplier());
			currentTime += 1000;
		}
		
		// key is pressed, score and multiplier are added
		report = game.tick(currentTime, userInput);
		expectedScore += Game.SCORE_BASE;
		expectedMultiplier += game.getScoreMultiplierBase();
		assertEquals(expectedScore, report.getScore());
		assertEquals(expectedMultiplier, report.getMultiplier());
		currentTime += 1000;
		
		// no keys again, score and multiplier should stay unchanged
		report = game.tick(currentTime, emptyInput);
		assertEquals(expectedScore, report.getScore());
		assertEquals(expectedMultiplier, report.getMultiplier());
		currentTime += 1000;
		
		// key is pressed but not the right one, no score is added, reset multiplier
		report = game.tick(currentTime, userInput);
		expectedMultiplier = 1.0;
		assertEquals(expectedScore, report.getScore());
		assertEquals(expectedMultiplier, report.getMultiplier());
		currentTime += 1000;
		
		// no keys are pressed
		for (int i = 0; i < 5; i++) {
			report = game.tick(currentTime, emptyInput);
			assertEquals(expectedScore, report.getScore());
			assertEquals(expectedMultiplier, report.getMultiplier());
			currentTime += 1000;
		}
		
		// key is pressed, score and multiplier are added
		report = game.tick(currentTime, userInput);
		expectedScore += Game.SCORE_BASE;
		expectedMultiplier += game.getScoreMultiplierBase();
		assertEquals(expectedScore, report.getScore());
		assertEquals(expectedMultiplier, report.getMultiplier());
		currentTime += 1000;
		
		// key is pressed, score is multiplied and added, multiplier is added
		report = game.tick(currentTime, userInput);
		expectedScore += Game.SCORE_BASE * expectedMultiplier;
		expectedMultiplier += game.getScoreMultiplierBase();
		assertEquals(expectedScore, report.getScore());
		assertEquals(expectedMultiplier, report.getMultiplier());
		currentTime += 1000;
		
		// key is pressed but not the right one, no score is added, reset multiplier
		report = game.tick(currentTime, userInput);
		expectedMultiplier = 1.0;
		assertEquals(expectedScore, report.getScore());
		assertEquals(expectedMultiplier, report.getMultiplier());
		currentTime += 1000;
		
		// no keys are pressed
		for (int i = 0; i < 20; i++) {
			report = game.tick(currentTime, emptyInput);
			assertEquals(expectedScore, report.getScore());
			assertEquals(expectedMultiplier, report.getMultiplier());
			currentTime += 1000;
		}
	}
}
