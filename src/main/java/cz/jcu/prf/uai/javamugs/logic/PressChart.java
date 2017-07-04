package cz.jcu.prf.uai.javamugs.logic;

import java.util.ArrayList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class PressChart {
	
	private Press[] presses;

	public PressChart(ArrayList<Press> presses) {
		int size = presses.size();
		this.presses = new Press[size];
		
		for(int i = 0; i<size; i++) {
			
		}
	}
	
	public Chord next(double currentTime) {
		throw new NotImplementedException();
	}
}
