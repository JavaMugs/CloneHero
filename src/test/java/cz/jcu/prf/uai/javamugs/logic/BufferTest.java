package cz.jcu.prf.uai.javamugs.logic;

import junit.framework.TestCase;

/**
 * Created by ivank on 04.07.2017.
 */
public class BufferTest extends TestCase {
    public void testCheck() throws Exception {
        Buffer buffer = new Buffer((byte)1);

        Buffer.Pair pair = buffer.check(new Chord(false, false, true, false, false), 100);
        assertEquals(0, pair.getHit());
        assertEquals(1, pair.getMiss());

        buffer.addToBuffer(new Chord(true, false, false, false, false), 1000);
        pair = buffer.check(new Chord(true, false, false, false, false), 1000);
        assertEquals(1, pair.getHit());
        assertEquals(0, pair.getMiss());
        assertEquals(0, buffer.getChordCount());
        assertEquals(0, buffer.getTimesCount());

        buffer.addToBuffer(new Chord(true, false, true, false, true), 2000);
        assertEquals(1, buffer.getChordCount());
        assertEquals(1, buffer.getTimesCount());
        pair = buffer.check(new Chord(true, false, true, false, false), 2050);
        assertEquals(2, pair.getHit());
        assertEquals(1, pair.getMiss());

        assertEquals(0, buffer.getChordCount());
    }

}