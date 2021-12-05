package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import controller.Card;

public class JSONWriter extends JSONReader {

	/*
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
	*/

	@SuppressWarnings("unchecked")
	public static void updateDeckTitle(File deckPath, String title) 
			throws FileNotFoundException, IOException, ParseException {
		
		JSONObject deck = getInitialJSONObject(deckPath);
		deck.put("deckTitle", title);
		writeToJSON(deckPath, deck);
		
	}

	public static void publicUpdateDeckTitle(String oldTitle, String newTitle) 
			throws FileNotFoundException, IOException, ParseException {

		//converts the given "title" into the file path for the intended deck
		File deckPath = getDeckFileFromTitle(oldTitle);

		updateDeckTitle(deckPath, newTitle);
		
	}
	
	public static void removeCard(String title, int cardID) //give a "boolean" confirmation sorta thing
			throws FileNotFoundException, IOException, ParseException {
		//This code simply removes a chosen card from the deck using cardID.
		
		//converts the given "title" into the file path for the intended deck
		File deckPath = getDeckFileFromTitle(title);
		
		//First, it converts the cardID to the cardIndex.
		int cardIndex = cardIDToArrayIndex(title, cardID);
		//Removes the card from the List.  
		cards.remove(cardIndex);
		//Grabs the JSONArray of the deck.  
		JSONArray changer = getDeckJSONArrayHand(deckPath);
		//Removes the given card from the JSONArray.  
		changer.remove(cardIndex);

		//Creates the JSONObject "deck" that the array will be placed in.  Calls writeToJSON() to update the JSONFile.  
		JSONObject hand = getInitialJSONObject(deckPath);
		hand.put("deck", changer);
		writeToJSON(deckPath, hand);
		
	}
	
	public static JSONObject setupJSONObjectFromCard(Card jack) {
		
		JSONObject joker = new JSONObject();
		joker.put("cardID", (long) jack.getID());
		joker.put("frontSide", jack.getFrontSide());
		joker.put("backSide", jack.getBackSide());
		joker.put("rating", jack.getRating());
		joker.put("repetition", jack.getRepetition());
		joker.put("interval", jack.getInterval());
		joker.put("difficultyFactor", 2.5);
		joker.put("nextReviewDate", jack.getNextReviewDate());
		
		return joker;
		
	}
	
	public static boolean createCard(String title, String frontSide, String backSide, int rating, int repetition, int interval, long nextReviewDate)
			throws FileNotFoundException, IOException, ParseException {
		//Creates a Card instance using inputed parameters and adds it to the List of "cards".
		//Includes the Card in the linked JSON file.
		
		//converts the given "title" into the file path for the intended deck
		File deckPath = getDeckFileFromTitle(title);
		
		//If the file is completely empty, then fill the file with a skeleton for the deck.
		 if(deckPath.length() == 0)
	            fillDeckTemplate(deckPath);
		//Grabs the JSONArray of the deck. 
		JSONArray changer = getDeckJSONArrayHand(deckPath);
		
		if(frontSide != null && backSide != null && changer != null) {
			//Sets up the cardID.  
			int cardID = 1;

			//Calls generateCardID() to make a new cardID int based on the latest cardID in the array.  
			cardID = generateCardID(title);
			
			//Creates a Card instance and adds it to the List.  
			Card jack = new Card(cardID, frontSide, backSide, rating, repetition, interval, nextReviewDate);
			
			//Creates a new JSONObject to store the card instance's attributes in.  
			JSONObject joker = setupJSONObjectFromCard(jack);
			
			//Adds the previous JSONObject into the JSONArray extracted from the file.  
			changer.add(joker);
			//Adds the array into one encompassing JSONObject that represents "deck".  
			JSONObject hand = getInitialJSONObject(deckPath);
			hand.put("deck", changer);

			//Calls writeToJSON() to update the JSONFile.  
			writeToJSON(deckPath, hand);

			generateDeck(title);
			return true;
			
		}
		
		return false;
		
	}
	

	public static Card makeNewCard(String title, String frontSide, String backSide, int rating, int repetition, int interval, long nextReviewDate)
			throws FileNotFoundException, IOException, ParseException {

		//Calls generateCardID() to make a new cardID int based on the latest cardID in the array.    
		int cardID = 1;
		cardID = generateCardID(title);
			
		//Creates a Card instance and adds it to the List.  
		Card jack = new Card(cardID, frontSide, backSide, rating, repetition, interval, nextReviewDate);
		return jack;
		
	}
	
	//code is under development and is still experimental.
	public static void updateCard(String deckTitle, int cardID, String frontSide, String backSide, int rating, int repetition, int interval, long nextReviewDate) 
			throws FileNotFoundException, IOException, ParseException {
		
		//converts the given "title" into the file path for the intended deck
		File deckPath = getDeckFileFromTitle(deckTitle);
		
		int cardIndex = cardIDToArrayIndex(deckTitle, cardID);
		JSONArray changer = getDeckJSONArrayHand(deckPath);
		
		Card jack = makeNewCard(deckTitle, frontSide, backSide, rating, repetition, interval, nextReviewDate);
		//System.out.println(jack.getFrontSide() + jack.getBackSide() + jack.getID());
		JSONObject joker = setupJSONObjectFromCard(jack);
		System.out.println("frontSide " + jack.getFrontSide() + " backSide " + jack.getBackSide() + " rating " + rating + " reps " + repetition + " interval " + interval + " nextReviewDate " + nextReviewDate);
		changer.add(joker);
		changer.remove(cardIndex);
		
		JSONObject hand = getInitialJSONObject(deckPath);
		hand.put("deck", changer);
		writeToJSON(deckPath, hand);
		
		generateDeck(deckTitle);
		
	}
	
}
