package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public class CardReviewer {
	
	static String title = "DefaultDeck";
	
	// calculate time to study for card
	public static void studyCalculation (Card card, int rating) { 
		int repetition = card.getRepetition();
		int interval = card.getInterval();
		double difficulty = card.getDifficultyFactor();
		
		System.out.println("Testing studyCalculation activation");
		assert rating >= 0  && rating <= 5: "Rating is a grading scale between 0 and 5";
		
		difficulty = Math.max(1.2, difficulty + 0.1 - (5.0 - rating) * (0.08 + (5.0 - rating) * 0.02));
		
		if ( rating < 3)
			repetition = 0;
		else
			repetition += 1;
		
		if (repetition <= 1)
			interval = 1;
		else if (repetition == 2)
			interval = 6;
		else
			interval = (int) Math.round(interval * difficulty);

		System.out.println("StudyCalculation rating: " + rating);
		System.out.println("StudyCalculation repetition: " + repetition);
		System.out.println("StudyCalculation interval: " + interval);
		int msInADay = 60 * 60 * 24 * 1000;
		long present = System.currentTimeMillis();
		long nextReviewDate =  msInADay + present * interval;
		String deckTitle = title; //Change this so that it takes in all of the files
		int cardID = card.getID();
		
		
		try {
			String frontSide = card.getFrontSide();
			String backSide = card.getBackSide();
			model.JSONWriter.updateCard(deckTitle, cardID, frontSide, backSide, rating, repetition, interval, nextReviewDate);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isCardHidden(Card card) {
		
		if(System.currentTimeMillis() >= card.getNextReviewDate())
			return false;
		return true;
		
	}
	
	public static ArrayList<Card> returnShowingCards(String deckTitle) 
			throws FileNotFoundException, IOException, ParseException {

		ArrayList<Card> cards = model.JSONReader.getArrayOfDeck(deckTitle);
		ArrayList<Card> shownCards = new ArrayList<>();
		
		for(Card c : cards) {
			if(!isCardHidden(c))
				shownCards.add(c);
		}
		
		return shownCards;
		
	}
	
	public void changer1() {
		title = "DefaultDeck";
	}
	
	public void changer2() {
		title = "CustomDeck2";
	}
	
	public void changer3() {
		title = "CustomDeck3";
	}
	
	public void changer4() {
		title = "CustomDeck4";
	}
	
	public void changer5() {
		title = "CustomDeck5";
	}
	
	public void changer6() {
		title = "CustomDeck6";
	}
	
	public void changer7() {
		title = "CustomDeck7";
	}
	
	public void changer8() {
		title = "CustomDeck8";
	}
	
	public void changer9() {
		title = "CustomDeck9";
	}
	
	public void changer10() {
		title = "CustomDeck10";
	}
	
}
