package cz.jcu.prf.uai.javamugs.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
     * @param fileName
     * @return
     * @throws IOException:  file is not found, file cannot be opened,
     * unexpected extension (.prc), file is too large (max 5MB)
     */
	private Stream<String> attemptOpenFile(String fileName) throws IOException {

        if (!fileName.endsWith(".prc")) throw new IOException("Unexpected extension");

        Path path = Paths.get(fileName);

        try{
            if (Files.notExists(path) ) throw new IOException("File not found.");
            if (Files.size(path) > B_SIZE_LIMIT) throw new IOException("File is too large");
        }
        catch (SecurityException e){
            throw new IOException("Access to file denied.");
        }



        throw new NotImplementedException();
    }

}
