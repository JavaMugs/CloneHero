package cz.jcu.prf.uai.javamugs.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Report {

	private long score;
	private int multiplier;
	
	public Report() {
		// TODO Auto-generated constructor stub
	}

	public long getScore() {
		return score;
	}
	
	public long getMultiplier() {
		return multiplier;
	}
	
	public Chord getChordToDraw() {
		throw new NotImplementedException();
	}
}
