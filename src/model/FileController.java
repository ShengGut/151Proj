package model;

import java.io.*;
import java.util.ArrayList;

public class FileController {
    
    private static ArrayList<File> decks;
    private static String directoryPath = "src/model";
    private static File binder = new File(directoryPath);

    public static int getDecksLength() {
        return binder.list().length;
    }
    
    public static ArrayList<File> returnDecks(){
    	return decks;
    }

    public static boolean fillDecks() {
        String[] fileList = binder.list();
        if(fileList != null) {
            for(String s : fileList)
                decks.add(new File(directoryPath + "/" + s));
            return true;
        }
        return false;
    }
    
    public static File getDeckFileFromTitle(String title) {
        
        //check if deck exists first.  
        return new File(directoryPath + "/" + title + ".json");
    }
    
}