package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
	
	public static boolean createCard(String title, String frontSide, String backSide, int rating, int repetition, int interval, long nextReviewDate, long exactReviewMoment)
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
			cards.add(jack);
			
			//Creates a new JSONObject to store the card instance's attributes in.  
			JSONObject joker = setupJSONObjectFromCard(jack);
			
			//Adds the previous JSONObject into the JSONArray extracted from the file.  
			changer.add(joker);
			//Adds the array into one encompassing JSONObject that represents "deck".  
			JSONObject hand = getInitialJSONObject(deckPath);
			hand.put("deck", changer);

			//Calls writeToJSON() to update the JSONFile.  
			writeToJSON(deckPath, hand);
			
			return true;
		}
		
		return false;
		
	}
	
	//code is under development and is still experimental.
	public static boolean updateCard(String title, int cardID, String key, Object value, String key2, Object value2, String key3, Object value3, String key4, Object value4) 
			throws FileNotFoundException, IOException, ParseException {
		// model.JSONWriter.updateCard(path, cardID, "nextReviewDate", nextReviewDate, "rating", rating, "repetition", repetition, "interval", interval);
		
		//converts the given "title" into the file path for the intended deck
		File deckPath = getDeckFileFromTitle(title);
		
		JSONArray changer = getDeckJSONArrayHand(deckPath);
		int target = cardIDToArrayIndex(title, cardID);
		if(key != null && key != "cardID" && value != null){

			JSONObject jack = (JSONObject) changer.get(target);
			Card joker = cards.get(target);
			
			//Gets the attributes of the card
			String frontSide = joker.getFrontSide();
			String backSide = joker.getBackSide();
			int rating = joker.getRating();
			int repetition = joker.getRepetition();
			int interval = joker.getInterval();
			long nextReviewDate = joker.getNextReviewDate();
			
			jack.put(key, value);
			Card king = setupCardFromJSONObject(jack);
				
			cards.add(king);
			cards.remove(target);
			jack.put(key2, value2);
			jack.put(key3, value3);
			jack.put(key4, value4);
			changer.remove(target);
			changer.add(jack);

			JSONObject hand = getInitialJSONObject(deckPath);
			hand.put("deck", changer);
			writeToJSON(deckPath, hand);
		
			return true;
			
		}
		
		return false;
		
	}
	
}
