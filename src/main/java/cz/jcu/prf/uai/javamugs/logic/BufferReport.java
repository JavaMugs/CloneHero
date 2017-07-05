package cz.jcu.prf.uai.javamugs.logic;

/**
 * Created by ivank on 05.07.2017.
 */

/**
 * Report of hites and misses and Chord of expected hits
 */
public class BufferReport {
    private int hit;
    private int miss;
    private Chord expectedChord;

    /**
     * Creates pair of hits and misses
     *
     * @param hit  number of hits
     * @param miss number of misses
     */
    public BufferReport(int hit, int miss, Chord expectedChord) {
        this.hit = hit;
        this.miss = miss;
        this.expectedChord = expectedChord;
    }

    /**
     * @return Hit count
     */
    public int getHit() {
        return hit;
    }

    /**
     * @return Miss count
     */
    public int getMiss() {
        return miss;
    }

    /**
     * @return Expected Chord
     */
    public Chord getExpectedChord() {
        return expectedChord;
    }
}
