package cz.jcu.prf.uai.javamugs.clonehero.logic;

import junit.framework.TestCase;

import java.io.IOException;

public class ParserTest extends TestCase {

    private static final String TEST_CHARTS_PATH = "./tracks/testCharts/";
    private static final String VALID_FILE_PATH = TEST_CHARTS_PATH + "ValidChart.prc";
    private static final String MISSING_EXTENSION_PATH = TEST_CHARTS_PATH + "MissingExtension";
    private static final String TOO_LARGE_FILE_PATH = TEST_CHARTS_PATH + "TooLarge.prc";
    private static final String DROPPING_EARLY_PRESSES_PATH = TEST_CHARTS_PATH + "DroppingEarlyPresses.prc";
    private static final String UNEXPECTED_FORMAT_PATH = TEST_CHARTS_PATH + "WrongFormat.prc";
    private static final double TIME_OFFSET = 3000;

    public void testParseFile() throws Exception {
        PressChart chart;
        Parser parser = new Parser();

        chart = parser.parseFile(VALID_FILE_PATH, TIME_OFFSET);
        assertNotNull(chart);

        try {
            parser.parseFile(MISSING_EXTENSION_PATH, TIME_OFFSET);
            fail();
        } catch (IOException e) {
            if (!e.getMessage().equals("Unexpected extension")) fail();
        }

        try {
            parser.parseFile(TOO_LARGE_FILE_PATH, TIME_OFFSET);
            fail();
        } catch (IOException e) {
            if (!e.getMessage().equals("File is too large")) fail();
        }

        //TODO test unauthorized access attempt

        chart = parser.parseFile(DROPPING_EARLY_PRESSES_PATH, TIME_OFFSET);
        assertTrue(chart.next(50000).isEmpty());

        try {
            parser.parseFile(UNEXPECTED_FORMAT_PATH, TIME_OFFSET);
            fail();
        } catch (IOException e) {
            if (!e.getMessage().startsWith("Unexpected file format ")) fail();
        }
    }
}