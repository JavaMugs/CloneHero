package cz.jcu.prf.uai.javamugs.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by ivank on 04.07.2017.
 */
public class Buffer {

    public Buffer(byte difficulty) {
        throw new NotImplementedException();
    }

    public void addToBuffer(Chord chord, long pressTime) {
        throw new NotImplementedException();
    }

    public Pair check(Chord pressedKeys, long PressTime) {
        throw new NotImplementedException();
    }

    public class Pair {
        private int hit;
        private int miss;

        public Pair(int hit, int miss) {
            this.hit = hit;
            this.miss = miss;
        }

        public int getHit() {
            return hit;
        }

        public int getMiss() {
            return miss;
        }
    }
}
