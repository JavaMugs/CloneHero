package cz.jcu.prf.uai.javamugs.logic;

public class Score {

	private long score;
	private int multiplier;

	public Score() {
		score = 0;
		multiplier = 1;
	}

	public long getScore() {
		return score;
	}

	public void addScore(long score) {
		this.score += score*this.multiplier;
	}
	
	public void resetMultiplier() {
		multiplier = 1;
	}
	
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	
	public int getMultiplier() {
		return multiplier;
	}
}
