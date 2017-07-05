package cz.jcu.prf.uai.javamugs.logic;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Saver {

    private List<Press> toSave;

    /**
     * Adds a press to be processed.
     * @param pressToAdd MUST CONTAIN PRESS TIME, NOT DRAW TIME.
     */
    public void addPress(Press pressToAdd){
        toSave.add(pressToAdd);
    }

    /**
     * Saves contained presses into .prc file.
     * @param path Where file should be saved.
     * @throws IOException in case there is a problem with processing the file.
     */
    public void save(String path) throws IOException {
        if (toSave.isEmpty()) throw new IOException("Nothing to save.");

        if (!path.endsWith(".prc")) path += ".prc";

        try{
            PrintWriter writer =  new PrintWriter(new BufferedWriter(new FileWriter(path)));

            Press toWrite;
            while (!toSave.isEmpty()){
                toWrite = toSave.remove(0);
                writer.println(toWrite.getDrawTime() + ':' + toWrite.getColor());   //Draw time is actually
                                                                                    // press time int this case.
            }
            writer.close();
        }
        catch(IOException e){
            throw new IOException("Problem with writing specified file: " + e.getMessage());
        }

    }

}
