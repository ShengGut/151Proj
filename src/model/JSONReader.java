package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import controller.Card;

public class JSONReader extends JSONCardsAbs {
	
    //@SuppressWarnings("unchecked")
    public static Card generateCard(File deckPath, int cardIndex) 
            throws FileNotFoundException, IOException, ParseException {
        //Creates and returns a the Card object identified by int "cardIndex" in the JSON file "deckPath".  
        
        Card jack = null; 
        
        //Grabs the JSONArray of cards in the JSONFile using getDeckJSONArray()
        JSONArray hand = getDeckJSONArray(deckPath);
        //Gets the specific JSONObject that represents the card identified by the cardIndex parameter.  
        JSONObject joker = (JSONObject) hand.get(cardIndex);
        //Calls setupCard() to literally setup the card using information from the JSONObject obtained above.  
        jack = setupCardFromJSONObject(joker);
        return jack;
        
    }
    
    public static int generateDeck(File deckPath) 
            throws FileNotFoundException, IOException, ParseException {
        //Fills the instance List variable "cards" with Card objects found in JSON file "deckPath".
        //Returns total number of cards added when finished.  
        
        int ia = 0;
        Card card = null;
        int deckSize = getDeckJSONArray(deckPath).size();
        
        for(ia = 0; ia < deckSize; ia++) {
            card = generateCard(deckPath, ia);
            cards.add(card);
        }

        return ia;
        
    }
    
    public static ArrayList<Card> getArrayOfDeck(File deckPath) 
           throws FileNotFoundException, IOException, ParseException {
    	
    	generateDeck(deckPath);
    	ArrayList<Card> hand = new ArrayList<Card>();
    	
    	for(int ia = 0; ia < getDeckJSONArray(deckPath).size(); ia++)
    		hand.add(generateCard(deckPath, ia));
    	
    	return hand;
    	
    }
    
    public static Card deliverCard(File deckPath, int cardID) 
            throws FileNotFoundException, IOException, ParseException {
        //Generates a Card instance using cardID rather than cardIndex.  
            //It does this by calling the same "generateCard()" method as normally used, but fills in the cardIndex parameter with cardID.  
        
        Card jack = generateCard(deckPath, cardIDToArrayIndex(cardID));
        return jack;
    }
    
    public static ArrayList<Card> getCards(File deckPath) throws FileNotFoundException, IOException, ParseException {
        generateDeck(deckPath);
        return cards;
    }
	   
}