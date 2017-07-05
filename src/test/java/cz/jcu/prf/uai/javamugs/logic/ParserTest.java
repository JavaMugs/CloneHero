package cz.jcu.prf.uai.javamugs.logic;

import junit.framework.TestCase;

public class ParserTest extends TestCase {

    public void testParseFile() throws Exception {
        String path = "chart.prc";
        double timeOffset = 3000;

        PressChart chart;

        Parser parser = new Parser();

        try{
            chart = parser.parseFile(path, timeOffset);

            Chord nextchord = chart.next(50000);
            while(!nextchord.isEmpty()){
                System.out.println(nextchord.getChords());
                nextchord = chart.next(50000);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }




    }

}