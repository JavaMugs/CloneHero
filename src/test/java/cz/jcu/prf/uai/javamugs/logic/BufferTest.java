package cz.jcu.prf.uai.javamugs.logic;

import junit.framework.TestCase;

/**
 * Created by ivank on 04.07.2017.
 */
public class BufferTest extends TestCase {
    public void testCheck() throws Exception {
        Buffer buffer = new Buffer((byte)1);

        BufferReport report = buffer.check(new Chord(false, false, true, false, false), 100);
        assertEquals(0, report.getHit());
        assertEquals(1, report.getMiss());

        Chord chord1 = new Chord(true, false, false, false, false);
        buffer.addToBuffer(chord1, 1000);
        report = buffer.check(new Chord(true, false, false, false, false), 1000);
        assertEquals(1, report.getHit());
        assertEquals(0, report.getMiss());
        assertEquals(0, buffer.getChordCount());
        assertEquals(0, buffer.getTimesCount());
        assertEquals(chord1, report.getExpectedChord());

        Chord chord2 = new Chord(true, false, true, false, true);
        buffer.addToBuffer(chord2, 2000);
        assertEquals(1, buffer.getChordCount());
        assertEquals(1, buffer.getTimesCount());
        report = buffer.check(new Chord(true, false, true, false, false), 2050);
        assertEquals(2, report.getHit());
        assertEquals(1, report.getMiss());
        assertEquals(chord2, report.getExpectedChord());

        assertEquals(0, buffer.getChordCount());
    }

}