package cz.jcu.prf.uai.javamugs.logic;

public class Report {

	private Score score;
	private Chord chordToDraw;
	
	public Report(Score score, Chord chordToDraw) {
		this.score = score;
		this.chordToDraw = chordToDraw;
	}

	public long getScore() {
		return score.getScore();
	}
	
	public long getMultiplier() {
		return score.getMultiplier();
	}
	
	public Chord getChordToDraw() {
		return chordToDraw;
	}
}
