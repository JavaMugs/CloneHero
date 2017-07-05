package cz.jcu.prf.uai.javamugs.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Game logic class.
 * @author Daniel Hryzbil
 */
public class Game {

	/**
	 * Creates game logic.
	 * @param timeOffset game speed.
	 * @param difficulty game difficulty.
	 * @param pressChart loaded press chart. Never null.
	 */
	public Game(double timeOffset, byte difficulty, PressChart pressChart) {
		// TODO...
	}
	
	/**
	 * Reports game status.
	 * @param curretTime 
	 * @param chord Chord pressed by user or null.
	 * @return game status. Never null.
	 */
	public GameReport tick(double curretTime, Chord chord) {
		throw new NotImplementedException();
	}
}
