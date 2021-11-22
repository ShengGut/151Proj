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

public abstract class JSONCardsAbs {
	protected static ArrayList<Card> cards = new ArrayList<Card>();
	
	public static ArrayList<Card> getCardsArray(File deckPath){
		//generateDeck(deckPath);
		return cards;
	}
	
	public static int generateCardID(File deckPath) throws FileNotFoundException, IOException, ParseException {
		//Creates a cardID int based solely on the latest cardID found in the array.  
		
		int cardID = cards.get(cards.size() - 1).getID() + 1;
		return cardID;
	}
	
	public static int cardIDToArrayIndex(int cardID) {
		//Converts cardID to the card's index in the ArrayList "cards".
		
		int ia = 0;
		//Search through each card in "cards" for one with a matching "cardID".  
		for(Object o : cards) {
			JSONObject jack = (JSONObject) o;
			if((int) (long) jack.get("cardID") == cardID)
				return ia;
			ia++;
		}
		return ia;
	}

	public static JSONArray getDeckJSONArray(File deckPath) throws FileNotFoundException, IOException, ParseException {
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
    
}