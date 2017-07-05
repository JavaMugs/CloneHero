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
    private Chord hitChord;
    private Chord missChord;

    /**
     * Creates pair of hits and misses
     *
     * @param hit  number of hits
     * @param miss number of misses
     */
    public BufferReport(int hit, int miss, Chord hitChord, Chord missChord) {
        this.hit = 0;
        for (int i = 0; i < hitChord.getChords().length; i++) {
            if(hitChord.getChords()[i]) this.hit++;
        }
        this.miss = 0;
        for (int i = 0; i < missChord.getChords().length; i++) {
            if(missChord.getChords()[i]) this.miss++;
        }
        this.hitChord = hitChord;
        this.missChord = missChord;
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
     * @return Chord where true is miss
     */
    public Chord getHitChord() {
        return hitChord;
    }

    /**
     * @return Chord where true is hit
     */
    public Chord getMissChord() {
        return missChord;
    }
}
