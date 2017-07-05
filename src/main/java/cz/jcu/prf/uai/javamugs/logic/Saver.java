package cz.jcu.prf.uai.javamugs.logic;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Saver {

    private List<Press> toSave;

    public void addPress(Press pressToAdd){
        toSave.add(pressToAdd);
    }

    public void save(String path) throws IOException {
        if (toSave.isEmpty()) throw new IOException("Nothing to save.");

        try{
            BufferedWriter writer =  new BufferedWriter(new FileWriter(path));


        }
        catch(IOException e){
            throw new IOException("Problem with writing specified file: " + e.getMessage());
        }

    }

}
