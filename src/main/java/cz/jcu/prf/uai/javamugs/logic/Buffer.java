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
    private int tolerance;

    /**
     * Creates Buffer with set difficulty
     *
     * @param difficulty 1-3 - tolerance of hits and misses
     */
    public Buffer(byte difficulty) {
        chordQueue = new LinkedList<Chord>();
        pressTimes = new ArrayList<Double>();
        switch (difficulty) {
            case 1:
                this.tolerance = 150;
                break;
            case 2:
                this.tolerance = 100;
                break;
            case 3:
                this.tolerance = 50;
                break;
        }
    }

    /**
     * Adds chord to queue and time to list.
     *
     * @param chord     Chord to add to buffer
     * @param pressTime time to when the chord should be pressed
     */
    public void addToBuffer(Chord chord, double pressTime) {
        chordQueue.add(chord);
        pressTimes.add(pressTime);
    }

    /**
     * Checks if time exists in list and dequeue Chords from queue if so.
     *
     * @param pressedKeys Chord of pressed keys from
     * @param pressTime   Time of the press
     * @return Pair of hits and misses, never null
     */
    public BufferReport check(Chord pressedKeys, double pressTime) {
        double minTime = pressTime - tolerance;
        double maxTime = pressTime + tolerance;
        int hits = 0;
        int misses = 0;
        // delete by time
        for (int i = 0; i < pressTimes.size(); i++) {
            if(pressTimes.get(i) < minTime) {
                pressTimes.remove(i);
                i--;
                chordQueue.poll();
                misses++;
            }
        }
        double chordTime = -1;
        int chordTimeIndex = -1;
        for (int i = 0; i < pressTimes.size(); i++) { // get expected chord time
            double time = pressTimes.get(i);
            if (time > minTime && time < maxTime) {
                chordTime = time;
                chordTimeIndex = i;
                break;
            }
        }
        Chord expectedChord;
        if (chordTime > 0) { // get expected chord
            expectedChord = chordQueue.peek();
        } else {
            expectedChord = new Chord(false, false, false, false, false);
        }
        for (int i = 0; i < 5; i++) {
            if (pressedKeys.getChords()[i] && !expectedChord.getChords()[i] // key pressed, nothing expected
                    || !pressedKeys.getChords()[i] && expectedChord.getChords()[i]) { // nothing pressed, key press expected
                misses++;
            }
            if (pressedKeys.getChords()[i] && expectedChord.getChords()[i]) {
                hits++;
            }
        }
        if(hits > 0) {
            chordQueue.poll();
            pressTimes.remove(chordTimeIndex);
        }
        return new BufferReport(hits, misses, expectedChord);
    }

    /**
     * @return size of Chord queue
     */
    public int getChordCount() {
        return chordQueue.size();
    }

    /**
     * @return size of times list
     */
    public int getTimesCount() {
        return pressTimes.size();
    }
}
