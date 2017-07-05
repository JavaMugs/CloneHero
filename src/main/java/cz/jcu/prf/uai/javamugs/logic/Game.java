package cz.jcu.prf.uai.javamugs.logic;

/**
 * Game logic class.
 * @author Daniel Hryzbil
 */
public class Game {

	private static final int SCORE_BASE = 100;
	private static final int HITS_IN_ROW_MULTIPLY = 5;
	private static final double MULTIPLIER_BASE = 0.1;

	private double timeOffset;
	private PressChart pressChart;
	private Score score;
	private Buffer buffer;
	private int hitsInRow;

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
	 * @param curretTime actual game time.
	 * @param chord chord pressed keys by user or null.
	 * @return game status. Never null.
	 */
	public GameReport tick(double currentTime, Chord chord) {
		Chord next = pressChart.next(currentTime);
		if (next != null)
		{
			buffer.addToBuffer(next, currentTime+timeOffset);
		}
		BufferReport report = buffer.check(chord, currentTime);

		hitsInRow += report.getHit();
		if (report.getMiss() > 0)
		{
			hitsInRow = 0;
			score.resetMultiplier();
		}
		else if (hitsInRow >= HITS_IN_ROW_MULTIPLY)
		{
			hitsInRow = 0;
			score.addMultiplier(MULTIPLIER_BASE);
		}

		if (report.getHit() > 0)
		{
			score.addScore(SCORE_BASE);
		}

		return new GameReport(score, next, report.getExpectedChord());
	}
}
