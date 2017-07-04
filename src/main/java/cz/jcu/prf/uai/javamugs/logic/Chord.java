package cz.jcu.prf.uai.javamugs.logic;

public class Chord {
	
	private boolean[] arr = new boolean[5];
	
	public static final int RED = 0;
	public static final int YELLOW = 1;
	public static final int GREEN = 2;
	public static final int BLUE = 3;
	public static final int MAGENTA = 4;

	public Chord(boolean red, boolean yellow, boolean green, boolean blue, boolean magenta) {
		arr[RED] = red;
		arr[YELLOW] = yellow;
		arr[GREEN] = green;
		arr[BLUE] = blue;
		arr[MAGENTA] = magenta;
	}
	
	public boolean[] getChords() {
		return arr; 
	}
}
