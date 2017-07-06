package cz.jcu.prf.uai.javamugs.clonehero.logic;

import junit.framework.TestCase;

/**
 * Created by ivank on 04.07.2017.
 */
public class BufferTest extends TestCase {
    public void testCheck() throws Exception {
        Buffer buffer = new cz.jcu.prf.uai.javamugs.clonehero.logic.Buffer((byte)1);

        BufferReport report;

        Chord chord = new Chord(true, false, true, false, true);
        buffer.addToBuffer(chord, 2000);
        assertEquals(1, buffer.getChordCount());
        assertEquals(1, buffer.getTimesCount());
        report = buffer.check(new Chord(true, false, true, false, false), 1950);
        assertEquals(2, report.getHit());
        report = buffer.check(new Chord(false, false, false, false, false), 2251);
        assertEquals(1, report.getMiss());
    }

}