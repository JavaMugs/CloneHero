package cz.jcu.prf.uai.javamugs.clonehero.logic;

import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * Game logic test.
 * @author Daniel Hryzbil
 */
public class GameTest extends TestCase {
	
	private static final double TIME_OFFSET = 80.0;
	private static final byte DIFFICULTY = 3;
	
	public void testGameLogic() {
		ArrayList<Press> presses = new ArrayList<Press>();
		presses.add(new Press(Chord.RED,      100.0));
		presses.add(new Press(Chord.YELLOW,   200.0));
		presses.add(new Press(Chord.MAGENTA,  300.0));
		presses.add(new Press(Chord.MAGENTA,  400.0));
		presses.add(new Press(Chord.MAGENTA,  500.0));
		presses.add(new Press(Chord.MAGENTA,  600.0));
		presses.add(new Press(Chord.MAGENTA,  700.0));
		presses.add(new Press(Chord.MAGENTA,  800.0));
		presses.add(new Press(Chord.MAGENTA,  900.0));
        presses.add(new Press(Chord.BLUE,    1000.0));
        presses.add(new Press(Chord.MAGENTA, 1100.0));
        presses.add(new Press(Chord.BLUE,    1200.0));
        presses.add(new Press(Chord.BLUE,    1300.0));
        presses.add(new Press(Chord.RED,     1400.0));
		
        PressChart pressChart = new PressChart(presses);
		Game game = new Game(TIME_OFFSET, DIFFICULTY, pressChart);
		assertNotNull(game);
		
		Chord userInput = new Chord(false, false, false, false, true);  // MAGENTA
		Chord emptyInput = new Chord(false, false, false, false, false);
		GameReport report;
		double currentTime = 0;
		long expectedScore = 0;
		double expectedMultiplier = 1.0;
		
		report = game.tick(currentTime, emptyInput);
		assertEquals(expectedScore, report.getScore());
		assertEquals(expectedMultiplier, report.getMultiplier());
		currentTime += 50;
		
		for (int i = 0; i < 7; i++) {
			report = game.tick(currentTime, userInput);
			assertEquals(expectedScore, report.getScore());
			assertEquals(expectedMultiplier, report.getMultiplier());
			currentTime += 50;
		}
		
		report = game.tick(currentTime, emptyInput);
		assertEquals(expectedScore, report.getScore());
		assertEquals(expectedMultiplier, report.getMultiplier());
		currentTime += 50;
		
		expectedScore += Game.SCORE_BASE;
		expectedMultiplier += Game.MULTIPLIER_BASE;
		report = game.tick(currentTime, userInput);
		assertEquals(expectedScore, report.getScore());
		assertEquals(expectedMultiplier, report.getMultiplier());
		currentTime += 100;
		
		for (int i = 0; i < 3; i++) {
			expectedScore += Game.SCORE_BASE * expectedMultiplier;
			expectedMultiplier += Game.MULTIPLIER_BASE;
			report = game.tick(currentTime, userInput);
			assertEquals(expectedScore, report.getScore());
			assertEquals(expectedMultiplier, report.getMultiplier());
			currentTime += 100;
		}
		
		for (int i = 0; i < 3; i++) {
			expectedScore += Game.SCORE_BASE * expectedMultiplier;
			expectedMultiplier += Game.MULTIPLIER_BASE;
			report = game.tick(currentTime, userInput);
			assertEquals(expectedScore, report.getScore());
			assertEquals(expectedMultiplier, report.getMultiplier());
			currentTime += 100;
		}
		
		report = game.tick(currentTime, emptyInput);
		assertEquals(expectedScore, report.getScore());
		assertEquals(1.0, report.getMultiplier());
		currentTime += 100;
		
		report = game.tick(currentTime, userInput);
		assertEquals(expectedScore, report.getScore());
		assertEquals(1.0, report.getMultiplier());
		expectedScore += Game.SCORE_BASE;
		currentTime += 100;
		
		report = game.tick(currentTime, userInput);
		assertEquals(expectedScore, report.getScore());
		assertEquals(1.0+Game.MULTIPLIER_BASE, report.getMultiplier());
		currentTime += 100;
		
		report = game.tick(currentTime, userInput);
		assertEquals(expectedScore, report.getScore());
		assertEquals(1.0, report.getMultiplier());
	}
}
