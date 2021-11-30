package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public class CardReviewer {
	
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
		
		card.setExactReviewMoment();
		
		System.out.println("StudyCalculation rating: " + rating);
		System.out.println("StudyCalculation repetition: " + repetition);
		System.out.println("StudyCalculation interval: " + interval);
		int msInADay = 60 * 60 * 24 * 1000;
		long present = System.currentTimeMillis();
		long nextReviewDate =  msInADay + present * interval;
		File path = new File("src/model/decks/DefaultDeck.json");
		int cardID = card.getID();
		
		
		try {
		model.JSONWriter.updateCard(path, cardID, "nextReviewDate", nextReviewDate, "rating", rating, "repetition", repetition, "interval", interval);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isCardHidden(Card card) {
		
		if(card.getNextReviewDate() - System.currentTimeMillis() >= card.getExactReviewMoment())
			return false;
		return true;
		
	}
	
	public static ArrayList<Card> returnShowingCards(String deckTitle) 
			throws FileNotFoundException, IOException, ParseException {
		
		File deckFile = model.FileController.getDeckFileFromTitle(deckTitle);
		ArrayList<Card> cards = model.JSONReader.getArrayOfDeck(deckFile);
		ArrayList<Card> shownCards = new ArrayList<>();
		
		for(Card c : cards) {
			if(!isCardHidden(c))
				shownCards.add(c);
		}
		
		return shownCards;
		
	}
	
}
