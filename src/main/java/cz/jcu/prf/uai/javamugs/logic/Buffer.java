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
    private int tolerance;

    /**
     * Creates Buffer with set difficulty
     *
     * @param difficulty 1-3 - tolerance of hits and misses
     */
    public Buffer(byte difficulty) {
        chordQueue = new LinkedList<Chord>();
        pressTimes = new ArrayList<Double>();
        this.difficulty = difficulty;
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
        throw new NotImplementedException();
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
     * Checks if time exists in list and dequeue Chords from queue if so. Cannot be null.
     *
     * @param pressedKeys Chord of pressed keys from
     * @param pressTime   Time of the press
     * @return Pair of hits and misses
     */
    public BufferReport check(Chord pressedKeys, double pressTime) {
        double minTime = pressTime - tolerance;
        double maxTime = pressTime + tolerance;
        double chordTime = -1;
        for (int i = 0; i < pressTimes.size(); i++) {
            double time = pressTimes.get(i);
            if (time > minTime && time < maxTime) {
                chordTime = time;
                pressTimes.remove(i);
                break;
            }
        }
        Chord expectedChord;
        if (chordTime > 0) {
            expectedChord = chordQueue.poll();
        } else {
            expectedChord = new Chord(false, false, false, false, false);
        }
        int hits = 0;
        int misses = 0;
        for (int i = 0; i < 5; i++) {
            if (pressedKeys.getChords()[i] && !expectedChord.getChords()[i] // key pressed, nothing expected
                    || !pressedKeys.getChords()[i] && !expectedChord.getChords()[i]) { // nothing pressed, key press expected
                misses++;
            }
            if (pressedKeys.getChords()[i] && expectedChord.getChords()[i]) {
                hits++;
            }
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
