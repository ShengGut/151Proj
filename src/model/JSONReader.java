package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controller.Card;

public class JSONReader {

	protected static ArrayList<Card> cards = new ArrayList<Card>();
    private static final String DECKSPATH = "src/model/decks";
    private static final File DECKSDIRECTORY = new File(DECKSPATH);
    private static final int TOTALDECKS = 10;

    //@SuppressWarnings("unchecked")
	public static void writeToJSON(File deckPath, JSONObject hand) throws IOException {
		//This method creates a FileWriter, converts a given JSONObject to JSONString, and then writes it to the given JSONFile.  
		FileWriter fw = new FileWriter(deckPath);
		fw.write(hand.toJSONString());
		fw.close();
	}

	public static void fillDeckTemplate(File deckPath) throws IOException {
		
		JSONObject deck = new JSONObject();
		JSONArray hand = new JSONArray();
		deck.put("deck", hand);
		String deckTitle = deckPath.getName();
		deck.put("deckTitle", deckTitle.substring(0, deckTitle.length() - 5));
		writeToJSON(deckPath, deck);
		
	}
	
	public static JSONObject getInitialJSONObject(File deckPath)
			throws FileNotFoundException, IOException, ParseException {
		//Creates readers and parsers that run through the given JSON file.
		if(deckPath.length() == 0)
			fillDeckTemplate(deckPath);
		JSONParser reader = new JSONParser();
		FileReader fr = new FileReader(deckPath);
		JSONObject deck = (JSONObject) reader.parse(fr);
		fr.close();
		return deck;
	}

	public static JSONArray getDeckJSONArrayHand(File deckPath)
			throws FileNotFoundException, IOException, ParseException {
		//Gets and returns the JSONArray that stores each card within the JSON file "deckPath".  

		JSONObject deck = getInitialJSONObject(deckPath);
		//Grabs the set of cards in the given deck as a JSONArray.  
		//Must start with the object that contains this array, "deck".
		JSONArray hand = (JSONArray) deck.get("deck");
		return hand;
	}

    public static File getDeckFileFromTitle(String title) 
			throws FileNotFoundException, IOException, ParseException {

    	for(File f : DECKSDIRECTORY.listFiles()) {
    		JSONObject deck = getInitialJSONObject(f);
    		if(deck.get("deckTitle").equals(title))
    			return f;	
    	}
    	return null;
    	
    }
    
	public static ArrayList<Card> getCardsArray(String title)
			throws FileNotFoundException, IOException, ParseException {
		generateDeck(title);
		return cards;
	}

	public static int generateCardID(String title) 
			throws FileNotFoundException, IOException, ParseException {
		//Creates a cardID int based solely on the latest cardID found in the array.  
		generateDeck(title);
		int cardID = 1;
		if(cards.size() != 0)
			cardID = cards.get(cards.size() - 1).getID() + 1;
		return cardID;
	}
	
	public static int cardIDToArrayIndex(String title, int cardID)
			throws FileNotFoundException, IOException, ParseException {
		//Converts cardID to the card's index in the ArrayList "cards".
		int ia = 0;
		generateDeck(title);
		
		//Search through each card in "cards" for one with a matching "cardID".  
		for(Card c : cards) {
			if(c.getID() == cardID)
				return ia;
			ia++;
		}
		return ia;
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
    
    public static Card generateCard(String title, int cardIndex) 
            throws FileNotFoundException, IOException, ParseException {
        //Creates and returns a the Card object identified by int "cardIndex" in the JSON file "deckPath".
		
		//converts the given "title" into the file path for the intended deck
		File deckPath = getDeckFileFromTitle(title);
		
        Card jack = null; 
        
        //Grabs the JSONArray of cards in the JSONFile using getDeckJSONArray()
        JSONArray hand = getDeckJSONArrayHand(deckPath);
        //Gets the specific JSONObject that represents the card identified by the cardIndex parameter.  
        JSONObject joker = (JSONObject) hand.get(cardIndex);
        //Calls setupCard() to literally setup the card using information from the JSONObject obtained above.  
        jack = setupCardFromJSONObject(joker);
        return jack;
        
    }
    
    public static int generateDeck(String title) 
            throws FileNotFoundException, IOException, ParseException {
        //Fills the instance List variable "cards" with Card objects found in JSON file "deckPath".
        //Returns total number of cards added when finished.  
		
		//converts the given "title" into the file path for the intended deck
    	File deckPath = getDeckFileFromTitle(title);
		
        int ia = 0;
        Card card = null;
        cards.removeAll(cards);
        int deckSize = getDeckJSONArrayHand(deckPath).size();
        
        for(ia = 0; ia < deckSize; ia++) {
            card = generateCard(title, ia);
            cards.add(card);
        }

        return ia;
        
    }

    public static Card deliverCard(String title, int cardID) 
            throws FileNotFoundException, IOException, ParseException {
        //Generates a Card instance using cardID rather than cardIndex.  
            //It does this by calling the same "generateCard()" method as normally used, but fills in the cardIndex parameter with cardID.
        Card jack = generateCard(title, cardIDToArrayIndex(title, cardID));
        return jack;
    }
	   
    public static ArrayList<Card> getArrayOfDeck(String title) 
           throws FileNotFoundException, IOException, ParseException {

		//converts the given "title" into the file path for the intended deck
		File deckPath = getDeckFileFromTitle(title);
		
    	//generateDeck(title);
    	ArrayList<Card> hand = new ArrayList<Card>();
    	
    	for(int ia = 0; ia < getDeckJSONArrayHand(deckPath).size(); ia++)
    		hand.add(generateCard(title, ia));
    	
    	return hand;
    	
    }

}