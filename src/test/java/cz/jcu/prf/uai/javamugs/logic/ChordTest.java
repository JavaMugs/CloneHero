package cz.jcu.prf.uai.javamugs.logic;

import junit.framework.TestCase;

/**
 * Created by Pavel on 05.07.2017.
 */
public class ChordTest extends TestCase {
    public void testGetChords() throws Exception {
        Chord chord = new Chord(false, true, false, false, true);

        boolean[] arr = chord.getChords();

        assertFalse(arr[Chord.RED]);
        assertTrue(arr[Chord.YELLOW]);
        assertFalse(arr[Chord.GREEN]);
        assertFalse(arr[Chord.BLUE]);
        assertTrue(arr[Chord.MAGENTA]);
    }

}