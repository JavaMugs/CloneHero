package cz.jcu.prf.uai.javamugs.clonehero.logic;

import cz.jcu.prf.uai.javamugs.clonehero.logic.Score;

/**
 * Game status container.
 * @author Daniel Hryzbil
 */
public class GameReport {

	private Score score;
	private Chord chordToDraw;
	private Chord hitChord;
	private Chord missChord;
	
	/**
	 * Creates report of game status.
	 * @param score actual score. Never null.
	 * @param chordToDraw next chord to draw.
	 * @param hitChord chord with hits.
	 * @param missChord chord with misses.
	 */
	public GameReport(Score score, Chord chordToDraw, Chord hitChord, Chord missChord) {
		this.score = score;
		this.chordToDraw = chordToDraw;
		this.hitChord = hitChord;
		this.missChord = missChord;
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
	public double getMultiplier() {
		return score.getMultiplier();
	}
	
	/**
	 * @return Chord to draw
	 */
	public Chord getChordToDraw() {
		return chordToDraw;
	}

	/**
	 * @return Chord where true is miss
	 */
	public Chord getHitChord() {
		return hitChord;
	}

	/**
	 * @return Chord where true is hit
	 */
	public Chord getMissChord() {
		return missChord;
	}
}
