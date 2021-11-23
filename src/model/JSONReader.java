package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controller.Card;

public class JSONReader {

	protected static ArrayList<Card> cards = new ArrayList<Card>();

    //@SuppressWarnings("unchecked")
	public static ArrayList<Card> getCardsArray(File deckPath)
			throws FileNotFoundException, IOException, ParseException {
		generateDeck(deckPath);
		return cards;
	}
	
	public static int generateCardID(File deckPath) 
			throws FileNotFoundException, IOException, ParseException {
		//Creates a cardID int based solely on the latest cardID found in the array.  
		
		generateDeck(deckPath);
		int cardID = cards.get(cards.size() - 1).getID() + 1;
		return cardID;
	}
	
	public static int cardIDToArrayIndex(File deckPath, int cardID)
			throws FileNotFoundException, IOException, ParseException {
		//Converts cardID to the card's index in the ArrayList "cards".
		
		int ia = 0;
		generateDeck(deckPath);
		
		//Search through each card in "cards" for one with a matching "cardID".  
		for(Card c : cards) {
			if(c.getID() == cardID)
				return ia;
			ia++;
		}
		return ia;
	}

	public static JSONArray getDeckJSONArray(File deckPath)
			throws FileNotFoundException, IOException, ParseException {
		//Gets and returns the JSONArray that stores each card within the JSON file "deckPath".  
		
		//Creates readers and parsers that run through the given JSON file.  
		JSONParser reader = new JSONParser();
		FileReader fr = new FileReader(deckPath);
		//Grabs the set of cards in the given deck as a JSONArray.  
		//Must start with the object that contains this array, "deck".  
		JSONObject deck = (JSONObject) reader.parse(fr);
		JSONArray hand = (JSONArray) deck.get("deck");
		fr.close();
		return hand;
	}

    public static Card setupCardFromJSONObject(JSONObject joker) {
        //Creates the attributes of the Card instance to be made.  
        int cardID = (int) (long) joker.get("cardID");
        String frontSide = (String) joker.get("frontSide");
        String backSide = (String) joker.get("backSide");
        int rating = (int) (long) joker.get("rating");
        int repetition = (int) (long) joker.get("repetition");
        int interval = (int) (long) joker.get("interval");
        long nextReviewDate = (long) joker.get("nextReviewDate");
        //Instantiates the card.  
        Card jack = new Card(cardID, frontSide, backSide, rating, repetition, interval, nextReviewDate);
        return jack;
    }
    
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
        cards.removeAll(cards);
        int deckSize = getDeckJSONArray(deckPath).size();
        
        for(ia = 0; ia < deckSize; ia++) {
            card = generateCard(deckPath, ia);
            cards.add(card);
        }

        return ia;
        
    }

    public static Card deliverCard(File deckPath, int cardID) 
            throws FileNotFoundException, IOException, ParseException {
        //Generates a Card instance using cardID rather than cardIndex.  
            //It does this by calling the same "generateCard()" method as normally used, but fills in the cardIndex parameter with cardID.  

        Card jack = generateCard(deckPath, cardIDToArrayIndex(deckPath, cardID));
        return jack;
    }
	   
    public static ArrayList<Card> getArrayOfDeck(File deckPath) 
           throws FileNotFoundException, IOException, ParseException {
    	
    	generateDeck(deckPath);
    	ArrayList<Card> hand = new ArrayList<Card>();
    	
    	for(int ia = 0; ia < getDeckJSONArray(deckPath).size(); ia++)
    		hand.add(generateCard(deckPath, ia));
    	
    	return hand;
    	
    }

}