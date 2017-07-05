package cz.jcu.prf.uai.javamugs.logic;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Pavel on 05.07.2017.
 */
public class PressChartTest extends TestCase {
    public void testNext() throws Exception {
        ArrayList<Press> arrList = new ArrayList<Press>();
        arrList.add(new Press(Chord.RED, 10.0));
        arrList.add(new Press(Chord.YELLOW, 12.0));
        arrList.add(new Press(Chord.MAGENTA, 13.0));

        arrList.add(new Press(Chord.MAGENTA, 20.0));

        arrList.add(new Press(Chord.MAGENTA, 30.0));
        arrList.add(new Press(Chord.BLUE, 40.0));
        arrList.add(new Press(Chord.YELLOW, 42.0));

        PressChart ps = new PressChart(arrList);

        boolean[] actual = ps.next(5.0).getChords();
        boolean[] expected = new boolean[] {false, false, false, false, false};
        for(int i = 0; i < actual.length; i++){
            assertEquals(expected[i], actual[i]);
        }

        actual = ps.next(18.0).getChords();
        expected = new boolean[] {true, true, false, false, true};
        for(int i = 0; i < actual.length; i++){
            assertEquals(expected[i], actual[i]);
        }


        actual = ps.next(21.0).getChords();
        expected = new boolean[] {false, false, false, false, true};
        for(int i = 0; i < actual.length; i++){
            assertEquals(expected[i], actual[i]);
        }

        actual = ps.next(50.0).getChords();
        expected = new boolean[] {false, true, false, true, true};
        for(int i = 0; i < actual.length; i++){
            assertEquals(expected[i], actual[i]);
        }

        actual = ps.next(70.0).getChords();
        expected = new boolean[] {false, false, false, false, false};
        for(int i = 0; i < actual.length; i++){
            assertEquals(expected[i], actual[i]);
        }

        try{
            actual = ps.next(40.0).getChords();
            fail();
        }catch (Exception e){
            //Expected
        }

        try{
            PressChart ps2 = new PressChart(null);
            fail();
        }catch (Exception e){
            //Expected
        }

    }

}