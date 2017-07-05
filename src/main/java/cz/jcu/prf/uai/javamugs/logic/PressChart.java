package cz.jcu.prf.uai.javamugs.logic;

import java.security.InvalidParameterException;
import java.util.ArrayList;


public class PressChart {
	
	private Press[] presses;
	private ArrayList<Press> pressesBackup;
	private int lastCalledItem;

	/**
	 * Set array of Presses from Array list
	 * @param presses arraylist
	 */
	public PressChart(ArrayList<Press> presses) {
		if(presses == null)
			throw new InvalidParameterException();

		int size = presses.size();
		this.presses = new Press[size];
		this.pressesBackup = new ArrayList<>();
		for(int i = 0; i<size; i++) {
			Press press = new Press(presses.get(i).getColor(), presses.get(i).getDrawTime());

			this.presses[i] = press;
			pressesBackup.add(press);
		}

		this.lastCalledItem = 0;
	}

	/**
	 * Get all Chords which are called between last time and actual call
	 * @param currentTime actual time
	 * @return Chord
	 */
	public Chord next(double currentTime) {
		if(this.lastCalledItem != 0 && currentTime<presses[this.lastCalledItem].getDrawTime())
			throw new InvalidParameterException();

		int i = this.lastCalledItem;
		boolean[] arr = new boolean[5];

		for (int y = 0; y< arr.length; y++)
			arr[y] = false;

		while(i < presses.length && presses[i].getDrawTime() < currentTime) {
			arr[presses[i].getColor()] = true;

			i++;
		};

		this.lastCalledItem = i;

		return new Chord(arr[Chord.RED], arr[Chord.YELLOW], arr[Chord.GREEN], arr[Chord.BLUE], arr[Chord.MAGENTA]);
	}
}
