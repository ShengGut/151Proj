package controller;

import java.io.File;

public class CardReviewer {
	
	// calculate time to study for card
	public static void studyCalculation (Card card, int rating) { 
		int repetition = card.getRepetition();
		int interval = card.getInterval();
		double difficulty = card.getDifficultyFactor();
		
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
		
		int msInADay = 60 * 60 * 24 * 1000;
		
		long present = System.currentTimeMillis();
		long nextReviewDate = present + msInADay * interval;
		
		File path = new File("src/model/DefaultDeck.json");
		int cardID = card.getID();
		
		try {
		model.JSONWriter.updateCard(path, cardID, "nextReviewDate", nextReviewDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
