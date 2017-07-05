package cz.jcu.prf.uai.javamugs.logic;

/**
 * Game status container.
 * @author Daniel Hryzbil
 */
public class GameReport {

	private Score score;
	private Chord chordToDraw;
	private Chord expectedChord;
	
	/**
	 * Creates report of game status.
	 * @param score actual score. Never null.
	 * @param chordToDraw next chord to draw or null.
	 * @param expectedChord chord expected to be pressed or null.
	 */
	public GameReport(Score score, Chord chordToDraw, Chord expectedChord) {
		this.score = score;
		this.chordToDraw = chordToDraw;
		this.expectedChord = expectedChord;
	}

	/**
	 * @return current score
	 */
	public long getScore() {
		return score.getScore();
	}
	
	/**
	 * @return current score multiplier
	 */
	public long getMultiplier() {
		return score.getMultiplier();
	}
	
	/**
	 * @return this chord should be drawn, possibly null
	 */
	public Chord getChordToDraw() {
		return chordToDraw;
	}

	/**
	 * @return this chord was expected to be pressed, possibly null
	 */
	public Chord getExpectedChord() {
		return expectedChord;
	}
}
