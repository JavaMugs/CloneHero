package cz.jcu.prf.uai.javamugs.clonehero.logic;

import junit.framework.TestCase;

/**
 * Created by Blaidd Drwg on 6.7.2017.
 */
public class SaverTest extends TestCase {
    private final static double TEST_FILE_END_TIME = 700;
    private final static double TEST_FILE_TIME_INCREMENT = 100;
    private final static double TEST_FILE_LOAD_TIME_OFFSET = 0;
    private final static double TEST_FILE_SAVE_TIME_OFFSET = 1;
    private final static String TEST_FILE_LOAD_PATH = "./tracks/testCharts/SaverTest.prc";
    private final static String TEST_FILE_SAVE_PATH = "./tracks/testCharts/SaverTestResult.prc";

    /**
     * SaverTest content:
     * 100:0
     * 100:1
     * 100:2
     * 200:0
     * 300:0
     * 400:0
     * 500:4
     * 600:1
     */

    public void testSave() throws Exception {

        Parser parser = new Parser();
        PressChart chart = parser.parseFile(TEST_FILE_LOAD_PATH, TEST_FILE_LOAD_TIME_OFFSET);

        Saver saver = new Saver();

        double time = TEST_FILE_SAVE_TIME_OFFSET;
        boolean chord[];

        while (time < TEST_FILE_END_TIME) {
            chord = chart.next(time).getChords();
            for (int i = 0; i < chord.length; i++) {
                if (chord[i]) {
                    saver.addPress(new Press(i, time));
                }
            }
            time += TEST_FILE_TIME_INCREMENT;
        }

        saver.save(TEST_FILE_SAVE_PATH);

        PressChart originalChart = parser.parseFile(TEST_FILE_LOAD_PATH, TEST_FILE_LOAD_TIME_OFFSET);
        PressChart newChart = parser.parseFile(TEST_FILE_SAVE_PATH, TEST_FILE_SAVE_TIME_OFFSET);
        assertNotNull(newChart);
        assertTrue(comparePressCharts(originalChart, newChart));
    }

    /**
     * Compares if two PressCharts have the same content.
     * @param a
     * @param b
     * @return true if press colors and times match in provided PressCharts
     */
    private boolean comparePressCharts(PressChart a, PressChart b) {

        Press aPresses[] = a.getPresses();
        Press bPresses[] = b.getPresses();

        if (aPresses == null || bPresses == null || aPresses.length != bPresses.length) return false;

        for (int i = 0; i < aPresses.length; i++) {
            if (!(aPresses[i].getColor() == bPresses[i].getColor()
                    && aPresses[i].getDrawTime() == bPresses[i].getDrawTime()))
                return false;
        }

        return true;
    }
}