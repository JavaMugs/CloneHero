package cz.jcu.prf.uai.javamugs.logic;

import junit.framework.TestCase;

import java.io.IOException;

public class ParserTest extends TestCase {

    private static final String TEST_CHARTS_PATH = "./tracks/testCharts/";

    public void testParseFile() throws Exception {
        String correctFilePath = "chart01.prc";
        String missinExtPath = TEST_CHARTS_PATH + "MissingExtension";
        String tooLargePath = TEST_CHARTS_PATH + "TooLarge.prc";
        double timeOffset = 3000;
        PressChart chart;
        Parser parser = new Parser();

        chart = parser.parseFile(correctFilePath, timeOffset);
        assertNotNull(chart);

        try{
            parser.parseFile(missinExtPath, timeOffset);
            fail();
        }
        catch(IOException e){
            if(!e.getMessage().equals("Unexpected extension")) fail();
        }

        try{
            parser.parseFile(tooLargePath, timeOffset);
            fail();
        }
        catch(IOException e){
            if(!e.getMessage().equals("File is too large")) fail();
        }

        //TODO test unauthorized access attempt




    }

}