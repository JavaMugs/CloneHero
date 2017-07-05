package cz.jcu.prf.uai.javamugs.clonehero.logic;

import junit.framework.TestCase;

/**
 * Created by Pavel on 05.07.2017.
 */
public class PressTest extends TestCase {

    public void testGetColor() throws Exception {
        Press press = new Press(Chord.MAGENTA, 500.0);
        assertEquals(Chord.MAGENTA, press.getColor());
    }

    public void testGetDrawTime() throws Exception {
        Press press = new Press(Chord.MAGENTA, 500.0);
        assertEquals(500.0, press.getDrawTime());
    }

}