package cz.jcu.prf.uai.javamugs.clonehero.logic;

/**
 * Game score container.
 * @author Daniel Hryzbil
 */
public class Score {

	private long score;
	private double multiplier;

	/**
	 * Creates game score.
	 */
	public Score() {
		score = 0;
		multiplier = 1.0;
	}

	/**
	 * @return current score
	 */
	public long getScore() {
		return score;
	}

	/**
	 * Sets score to 0.
	 */
	public void resetScore() {
		score = 0;
	}
	
	/**
	 * @param number added score. It's multiplied by current multiplier.
	 */
	public void addScore(long number) {
		score += number*multiplier;
	}
	
	/**
	 * @return current multiplier
	 */
	public double getMultiplier() {
		return multiplier;
	}
	
	/**
	 * Sets multiplier to 1.
	 */
	public void resetMultiplier() {
		multiplier = 1.0;
	}
	
	/**
	 * @param number added multiplier.
	 */
	public void addMultiplier(double number) {
		multiplier += number;
	}
}
