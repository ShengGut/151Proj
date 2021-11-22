package controller;

import java.io.*;
import java.util.ArrayList;

public class FileController {
    
    private ArrayList<File> decks;
    String directoryPath = "src/model";
    File binder = new File(directoryPath);
    
    public boolean fillDecks() {
        String[] fileList = binder.list();
        if(fileList != null) {
            for(String s : fileList)
                decks.add(new File(directoryPath + "/" + s));
            return true;
        }
        return false;
    }
    
    public int getDecksLength() {
        return binder.list().length;
    }

    public File getDeckFile(String title) {
        
        //check if deck exists first.  
        return new File(directoryPath + "/" + title + ".json");
    }
    
}