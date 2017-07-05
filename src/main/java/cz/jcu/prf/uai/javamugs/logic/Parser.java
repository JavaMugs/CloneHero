package cz.jcu.prf.uai.javamugs.logic;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



public class Parser {

    private static final int B_SIZE_LIMIT = 5*1024*1024;
    private static final int COLOR_MAX = 4;
    private static final int COLOR_MIN = 0;
    private static final String SEPARATOR = ":";

    /**
     * Opens track file and parses it into PressChart.
     * Expected file format is [milliseconds from song beginning]:[number denoting its color]
     * no braces, one entry per line.
     * Presses that would be drawn before time 0 are discarded.
     * @param fileName Path to the track file to be parsed.
     * @param timeOffset Defines the offset of chords draw time and press time.
     *                   Positive offset means draw comes before press (recommended).
     * @return Parsed PressChart, can be empty, never null.
     * @throws IOException: failed opening file, unexpected format
     */
	public PressChart parseFile(String fileName, double timeOffset) throws IOException {

        ArrayList<Press> result = new ArrayList<>();

        BufferedReader openedFile = attemptOpenFile(fileName);

        List<List<String>> parsedFile =   openedFile.lines()                                                            //turn reader into stream
                                                    .map(line -> Arrays.asList(line.split(SEPARATOR)))                  //turn every line into a two field list
                                                    .collect(Collectors.toList());                                      //collect the lists creating a 2D list

        try {
            double hitTime = 0, prevHitTime, drawTime;

            for (List<String> line : parsedFile) {

                prevHitTime = hitTime;
                hitTime = Double.parseDouble(line.get(0));

                if(hitTime < prevHitTime) throw new IOException("Entries in wrong order.");                             //check for time ordering

                drawTime = hitTime - timeOffset;
                if(drawTime < 0.0) continue;                                                                            //discard early presses
                int color = Integer.parseInt(line.get(1));
                if (color < COLOR_MIN || color > COLOR_MAX) throw new IOException("Color out of bounds");               //check for unknown color

                result.add(new Press(color, drawTime));                                                                 //add parsed line to result
            }
        }
        catch(Exception e){
            throw new IOException("Unexpected file format " + e.getMessage());
        }

        openedFile.close();

        return new PressChart(result);
	}

    /**
     * Method used for opening specified file safely.
     * @param fileName Path to the file to be opened.
     * @return Opened reader.
     * @throws IOException: file is not found, file cannot be opened,
     *                      unexpected extension (.prc), file is too large (max 5MB)
     */
	private BufferedReader attemptOpenFile(String fileName) throws IOException {

        if (!fileName.endsWith(".prc")) throw new IOException("Unexpected extension");

        Path path = Paths.get(fileName);

        try{
            if (Files.notExists(path) ) throw new IOException("File not found.");
            if (Files.size(path) > B_SIZE_LIMIT) throw new IOException("File is too large");
        }
        catch (SecurityException e){
            throw new IOException("Access to file denied.");
        }

        try{
            return new BufferedReader(new FileReader(fileName));
        }
        catch (IOException e){
            throw new IOException("Error opening file.");
        }
    }

}
