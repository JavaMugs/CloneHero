package cz.jcu.prf.uai.javamugs.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by ivank on 04.07.2017.
 */
public class Buffer {

    private ArrayList<BufferRecord> bufferRecords;
    private int tolerance;

    /**
     * Creates Buffer with set difficulty
     *
     * @param difficulty 1-3 - tolerance of hits and misses
     */
    public Buffer(byte difficulty) {
        bufferRecords = new ArrayList<BufferRecord>();
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
        if(!chord.isEmpty()) {
            bufferRecords.add(new BufferRecord(chord, pressTime));
        }
    }

    /**
     * Checks if time exists in list and dequeue Chords from queue if so.
     *
     * @param pressedKeys Chord of pressed keys from
     * @param pressTime   Time of the press
     * @return Pair of hits and misses, never null
     */
    public BufferReport check(Chord pressedKeys, double pressTime) {
        /*double minTime = pressTime - tolerance;
        double maxTime = pressTime + tolerance;
        int hits = 0;
        int misses = 0;
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
            if (pressedKeys.getChords()[i] && !expectedChord.getChords()[i]) { // key pressed, nothing expected
                misses++;
            }
            if (pressedKeys.getChords()[i] && expectedChord.getChords()[i]) {
                hits++;
            }
        }
        if(hits > 0) {
            chordQueue.poll();
            pressTimes.remove(chordTimeIndex);
        } else {
            // delete by time
            for (int i = 0; i < pressTimes.size(); i++) {
                if(pressTimes.get(i) < chordTime - tolerance) {
                    pressTimes.remove(i);
                    i--;
                    misses++;
                    chordQueue.poll();
                }
            }
        }*/
        double minTime = pressTime - tolerance;
        double maxTime = pressTime + tolerance;
        ArrayList<BufferRecord> expectedBufferRecords = new ArrayList<BufferRecord>();
        for (BufferRecord bufferRecord : bufferRecords) { // get expected chords
            double time = bufferRecord.getTime();
            if (time > minTime && time < maxTime) {
                expectedBufferRecords.add(bufferRecord);
            }
        }
        int misses = 0;
        Chord hitChord = new Chord(false, false, false, false, false);
        Chord missChord = new Chord(false, false, false, false, false);
        for (BufferRecord expectedBufferRecord : expectedBufferRecords) {
            expectedBufferRecord.checkHits(pressedKeys);
            misses = expectedBufferRecord.checkUnexpectedPresses(pressedKeys);
            for (int i = 0; i < hitChord.getChords().length; i++) {
                if(expectedBufferRecord.getChord().getChords()[i]) hitChord.getChords()[i] = true;
            }
        }


        //return new BufferReport(hits, misses, expectedChord);
        return null
    }

    /**
     * @return size of Chord queue
     */
    public int getChordCount() {
        return bufferRecords.size();
    }

    /**
     * @return size of times list
     */
    public int getTimesCount() {
        return bufferRecords.size();
    }

    private class BufferRecord {
        private Chord chord;
        private double time;
        private int hits;

        public BufferRecord(Chord chord, double time) {
            this.chord = chord;
            this.time = time;
            this.hits = 0;
        }

        public int getMisses() {
            int expectedHits = 0;
            for(boolean string : chord.getChords()) {
                if(string) expectedHits++;
            }
            return expectedHits - hits;
        }

        public void checkHits(Chord pressedChord) {
            for(int i = 0; i < chord.getChords().length; i++) {
                if(chord.getChords()[i] && pressedChord.getChords()[i]) hits++;
            }
        }

        public int  checkUnexpectedPresses(Chord pressedChord) {
            int misses = 0;
            for(int i = 0; i < chord.getChords().length; i++) {
                if(!chord.getChords()[i] && pressedChord.getChords()[i]) misses++;
            }
            return misses;
        }

        public double getTime() {
            return time;
        }

        public int getHits() {
            return hits;
        }

        public Chord getChord() {
            return chord;
        }
    }
}
