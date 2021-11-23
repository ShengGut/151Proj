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
	
	@SuppressWarnings("unchecked")
	public static void writeToJSON(File deckPath, JSONObject hand) throws IOException {
		//This method creates a FileWriter, converts a given JSONObject to JSONString, and then writes it to the given JSONFile.  
		FileWriter fw = new FileWriter(deckPath);
		fw.write(hand.toJSONString());
		fw.close();
	}

	/*
	public static void createNewDeck(String title) throws IOException {
		
		File deckPath = new File("src/model/" + title + ".json");
		deckPath.createNewFile();
		
		fillDeckTemplate(deckPath);
		
	}
	*/
	
	public static void fillDeckTemplate(File deckPath) throws IOException {
		
		JSONObject deck = new JSONObject();
		JSONArray hand = new JSONArray();
		deck.put("deck", hand);
		writeToJSON(deckPath, deck);
		
	}
	
	public static void removeCard(File deckPath, int cardID) //give a "boolean" confirmation sorta thing
			throws FileNotFoundException, IOException, ParseException {
		//This code simply removes a chosen card from the deck using cardID.  
		
		//First, it converts the cardID to the cardIndex.
		int cardIndex = cardIDToArrayIndex(cardID);
		//Removes the card from the List.  
		cards.remove(cardIndex);
		//Grabs the JSONArray of the deck.  
		JSONArray changer = getDeckJSONArray(deckPath);
		//Removes the given card from the JSONArray.  
		changer.remove(cardIndex);

		//Creates the JSONObject "deck" that the array will be placed in.  Calls writeToJSON() to update the JSONFile.  
		JSONObject hand = new JSONObject();
		hand.put("deck", changer);
		writeToJSON(deckPath, hand);
		
	}
	
	public static boolean createCard(File deckPath, String frontSide, String backSide, int rating, int repetition, int interval, long nextReviewDate)
			throws FileNotFoundException, IOException, ParseException {
		//Creates a Card instance using inputed parameters and adds it to the List of "cards".
		//Includes the Card in the linked JSON file.
		//If the file is completely empty, then fill the file with a skeleton for the deck.
		 if(deckPath.length() == 0)
	            fillDeckTemplate(deckPath);
		//Grabs the JSONArray of the deck. 
		JSONArray changer = getDeckJSONArray(deckPath);
		
		if(frontSide != null && backSide != null && changer != null) {
			//Sets up the cardID.  
			int cardID = 0;
			//Creates a Card instance and adds it to the List.  
			Card jack = new Card(cardID, frontSide, backSide, rating, repetition, interval, nextReviewDate);
			cards.add(jack);

			/*
			if(changer.size() != 0)
				cardID = (int) (long) ((JSONObject) changer.get(changer.size() - 1)).get("cardID") + 1;
			*/
			
			//Calls generateCardID() to make a new cardID int based on the latest cardID in the array.  
			cardID = generateCardID(deckPath);
			
			//Creates a new JSONObject to store the card instance's attributes in.  
			JSONObject joker = new JSONObject();
			joker.put("cardID", (long) cardID);
			joker.put("frontSide", frontSide);
			joker.put("backSide", backSide);
			joker.put("rating", rating);
			joker.put("repetition", repetition);
			joker.put("interval", interval);
			joker.put("difficultyFactor", 2.5);
			joker.put("nextReviewDate", nextReviewDate);
			
			//Adds the previous JSONObject into the JSONArray extracted from the file.  
			changer.add(joker);
			//Adds the array into one encompassing JSONObject that represents "deck".  
			JSONObject hand = new JSONObject();
			hand.put("deck", changer);

			//Calls writeToJSON() to update the JSONFile.  
			writeToJSON(deckPath, hand);
			
			return true;
		}
		
		return false;
		
	}
	
	
	//code is under development and is still experimental.
	public static boolean updateCard(File deckPath, int cardID, String key, Object value) 
			throws FileNotFoundException, IOException, ParseException {
		
		JSONArray changer = getDeckJSONArray(deckPath);
		int target = cardIDToArrayIndex(cardID);
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
			changer.remove(target);
			changer.add(jack);

			JSONObject hand = new JSONObject();
			hand.put("deck", changer);
			writeToJSON(deckPath, hand);
		
			return true;
			
		}
		
		return false;
		
	}
	
}
