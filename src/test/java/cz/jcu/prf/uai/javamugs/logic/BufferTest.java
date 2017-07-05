package cz.jcu.prf.uai.javamugs.logic;

import junit.framework.TestCase;

/**
 * Created by ivank on 04.07.2017.
 */
public class BufferTest extends TestCase {
    public void testCheck() throws Exception {
        Buffer buffer = new Buffer((byte)1);

        /*BufferReport report = buffer.check(new Chord(false, false, true, false, false), 100);
        assertEquals(0, report.getHit());
        assertEquals(1, report.getMiss());*/

        BufferReport report;

        Chord chord1 = new Chord(true, false, false, false, false);
        buffer.addToBuffer(chord1, 1000);
        report = buffer.check(new Chord(false, false, false, false, false), 900);
        report = buffer.check(new Chord(false, false, false, false, false), 950);
        report = buffer.check(new Chord(false, false, false, false, false), 1000);
        report = buffer.check(new Chord(false, false, false, false, false), 1151);
        assertEquals(1, report.getMiss());
        /*assertEquals(1, report.getHit());
        assertEquals(0, report.getMiss());
        assertEquals(0, buffer.getChordCount());
        assertEquals(0, buffer.getTimesCount());
        assertEquals(chord1, report.getExpectedChord());*/

        Chord chord2 = new Chord(true, false, true, false, true);
        buffer.addToBuffer(chord2, 2000);
        assertEquals(1, buffer.getChordCount());
        assertEquals(1, buffer.getTimesCount());
        report = buffer.check(new Chord(true, false, true, false, false), 1950);
        assertEquals(2, report.getHit());
        assertEquals(1, report.getMiss());
        assertEquals(chord2, report.getExpectedChord());

        buffer.check(new Chord(false, false, false, false, false), 2251);

        assertEquals(0, buffer.getChordCount());

        Chord chord3 = new Chord(true, false, true, false, true);
        buffer.addToBuffer(chord3, 5000);
        Chord chord4 = new Chord(true, false, true, false, true);
        buffer.addToBuffer(chord4, 10000);
        report = buffer.check(new Chord(true, false, true, false, true), 10100);
        buffer.check(new Chord(false, false, false, false, false), 10151);
        assertEquals(true, report.getExpectedChord().getChords()[0]);
        assertEquals(0, buffer.getChordCount());
        assertEquals(0, buffer.getTimesCount());
        assertEquals(3, report.getHit());
        assertEquals(1, report.getMiss());

        Chord chord5 = new Chord(true, true, true, true, true);
        buffer.addToBuffer(chord5, 15000);

        Chord chord6 = new Chord(true, true, true, true, true);
        buffer.addToBuffer(chord6, 15200);

        Chord chord7 = new Chord(true, true, true, true, true);
        buffer.addToBuffer(chord7, 15400);

        Chord chord8 = new Chord(true, true, true, true, true);
        buffer.addToBuffer(chord8, 15600);

        Chord chord9 = new Chord(true, true, true, true, true);
        buffer.addToBuffer(chord9, 15800);

        Chord chord10 = new Chord(true, true, true, true, true);
        buffer.addToBuffer(chord10, 16000);

        report = buffer.check(new Chord(true, true, true, false, false), 15000);
        assertEquals(3, report.getHit());
        report = buffer.check(new Chord(false, false, false, true, false), 15200);
        report = buffer.check(new Chord(false, false, false, false, false), 15250);
        report = buffer.check(new Chord(false, false, true, false, false), 15300);
        report = buffer.check(new Chord(false, false, false, false, false), 15320);
        report = buffer.check(new Chord(false, false, false, false, false), 15340);
        report = buffer.check(new Chord(true, true, true, false, false), 15600);
        assertEquals(3, report.getHit());
        report = buffer.check(new Chord(true, true, true, false, false), 15900);
        assertEquals(3, report.getHit());


    }

}