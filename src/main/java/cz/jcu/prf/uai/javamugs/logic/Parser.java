package cz.jcu.prf.uai.javamugs.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Parser {

	public Parser() {
		// TODO Auto-generated constructor stub
	}
	 /**
	  * Opens track file and parses into PressChart. 
	  * Times in PressChart are draw times in miliseconds from song begining. 
	  * Throws various exceptions when: file is not found, file cannot be opened,
	  * file does not have expected extension (.prc), file is too large (max 5MB), 
	  * @param fileName Path to the track file to be parsed.
	  * @param timeOffset Defines the offset of chords draw time and press time.
	  * @return Parsed PressChart, never null.
	  */
	public PressChart parseFile(String fileName, double timeOffset) {
		throw new NotImplementedException();
	}

}
