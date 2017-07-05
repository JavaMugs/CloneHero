package cz.jcu.prf.uai.javamugs.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Parser {

    final private static int B_SIZE_LIMIT = 5*1024*1024;

	 /**
	  * Opens track file and parses into PressChart. 
	  * Expected file format is [milliseconds from song beginning]:[number denoting its color]
      * without braces, one entry per line.
	  * @param fileName Path to the track file to be parsed.
	  * @param timeOffset Defines the offset of chords draw time and press time.
      *                   Positive offset means draw comes before press (recommended).
	  * @return Parsed PressChart, never null.
      * @throws IOException: failed opening file, unexpected format
	  */
	public PressChart parseFile(String fileName, double timeOffset) throws IOException {



		throw new NotImplementedException();
	}

    /**
     *
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

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            return reader;
        }
        catch (IOException e){
            throw new IOException("Error opening file.");
        }
    }

}
