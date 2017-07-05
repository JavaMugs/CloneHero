package cz.jcu.prf.uai.javamugs.clonehero.logic;

import cz.jcu.prf.uai.javamugs.clonehero.logic.GameReport;
import cz.jcu.prf.uai.javamugs.clonehero.logic.PressChart;
import cz.jcu.prf.uai.javamugs.clonehero.logic.Score;

/**
 * Game logic class.
 * @author Daniel Hryzbil
 */
public class Game {

	public static final int SCORE_BASE = 100;
	public static final double MULTIPLIER_BASE = 0.5;

	private double timeOffset;
	private PressChart pressChart;
	private Score score;
	private Buffer buffer;

	/**
	 * Creates game logic.
	 * @param timeOffset game speed.
	 * @param difficulty game difficulty.
	 * @param pressChart loaded press chart. Never null.
	 */
	public Game(double timeOffset, byte difficulty, PressChart pressChart) {
		this.timeOffset = timeOffset;
		this.pressChart = pressChart;
		score = new Score();
		buffer = new Buffer(difficulty);
	}
	
	/**
	 * Updates and reports current game status.
	 * @param currentTime actual game time.
	 * @param chord chord pressed keys by user.
	 * @return game status. Never null.
	 */
	public GameReport tick(double currentTime, Chord chord) {
		Chord next = pressChart.next(currentTime);
		buffer.addToBuffer(next, currentTime+timeOffset);
		BufferReport report = buffer.check(chord, currentTime);

		if (report.getMiss() > 0) {
			score.resetMultiplier();
		}

		if (report.getHit() > 0) {
			score.addScore(SCORE_BASE * report.getHit());
			score.addMultiplier(MULTIPLIER_BASE);
		}

		return new GameReport(score, next, report.getHitChord(), report.getMissChord());
	}
	
	public double getTimeOffset() {
		return timeOffset;
	}
}
