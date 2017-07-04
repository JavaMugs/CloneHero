package cz.jcu.prf.uai.javamugs.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by ivank on 04.07.2017.
 */
public class Buffer {

    private Queue<Chord> chordQueue;
    private List<Double> pressTimes;
    private int difficulty;

    /**
     * Creates Buffer with set difficulty
     * @param difficulty 1-3 - tolerance of hits and misses
     */
    public Buffer(byte difficulty) {
        chordQueue = new LinkedList<Chord>();
        pressTimes = new ArrayList<Double>();
        this.difficulty = difficulty;
        throw new NotImplementedException();
    }

    /**
     * Adds chord to queue and time to list.
     * @param chord Chord to add to buffer
     * @param pressTime time to when the chord should be pressed
     */
    public void addToBuffer(Chord chord, double pressTime) {
        chordQueue.add(chord);
        pressTimes.add(pressTime);
    }

    /**
     * Checks if time exists in list and dequeue Chords from queue if so
     * @param pressedKeys Chord of pressed keys from
     * @param PressTime Time of the press
     * @return Pair of hits and misses
     */
    public Pair check(Chord pressedKeys, long PressTime) {
        //TODO
        throw new NotImplementedException();
    }

    /**
     * Pair of hites and misses
     */
    public class Pair {
        private int hit;
        private int miss;

        /**
         * Creates pair of hits and misses
         * @param hit number of hits
         * @param miss number of misses
         */
        public Pair(int hit, int miss) {
            this.hit = hit;
            this.miss = miss;
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
    }
}
