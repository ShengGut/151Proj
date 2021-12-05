package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public class CardReviewer {

	static String title = "DefaultDeck";

	// calculate time to study for card
	public static void studyCalculation(Card card, int rating) {
		int repetition = card.getRepetition();
		int interval = card.getInterval();
		double difficulty = card.getDifficultyFactor();

		assert rating >= 0 && rating <= 4 : "Rating is a grading scale between 0 and 4";

		difficulty = Math.max(1.2, difficulty + 0.1 - (5.0 - rating) * (0.08 + (5.0 - rating) * 0.02));

		if (rating < 3)
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
		long nextReviewDate = msInADay + present * interval;
		String deckTitle = title; // Change this so that it takes in all of the files
		int cardID = card.getID();

		try {
			String frontSide = card.getFrontSide();
			String backSide = card.getBackSide();
			model.JSONWriter.updateCard(deckTitle, cardID, frontSide, backSide, rating, repetition, interval,
					nextReviewDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Check if the card is hidden
	public static boolean isCardHidden(Card card) {

		if (System.currentTimeMillis() >= card.getNextReviewDate())
			return false;
		return true;

	}

	//Array for the list of cards
	public static ArrayList<Card> returnShowingCards(String deckTitle)
			throws FileNotFoundException, IOException, ParseException {

		ArrayList<Card> cards = model.JSONReader.getArrayOfDeck(deckTitle);
		ArrayList<Card> shownCards = new ArrayList<>();

		for (Card c : cards) {
			if (!isCardHidden(c))
				shownCards.add(c);
		}

		return shownCards;

	}

	//Change the file path based on the deck that is selected
	public void changer1() throws FileNotFoundException, IOException, ParseException {
		File deckPath = new File("src/model/decks/DefaultDeck.json");
		title = model.JSONReader.getDeckTitleFromFile(deckPath);
	}

	public void changer2() throws FileNotFoundException, IOException, ParseException {
		File deckPath = new File("src/model/decks/CustomDeck2.json");
		title = model.JSONReader.getDeckTitleFromFile(deckPath);
	}

	public void changer3() throws FileNotFoundException, IOException, ParseException {
		File deckPath = new File("src/model/decks/CustomDeck3.json");
		title = model.JSONReader.getDeckTitleFromFile(deckPath);
	}

	public void changer4() throws FileNotFoundException, IOException, ParseException {
		File deckPath = new File("src/model/decks/CustomDeck4.json");
		title = model.JSONReader.getDeckTitleFromFile(deckPath);
	}

	public void changer5() throws FileNotFoundException, IOException, ParseException {
		File deckPath = new File("src/model/decks/CustomDeck5.json");
		title = model.JSONReader.getDeckTitleFromFile(deckPath);
	}

	public void changer6() throws FileNotFoundException, IOException, ParseException {
		File deckPath = new File("src/model/decks/CustomDeck6.json");
		title = model.JSONReader.getDeckTitleFromFile(deckPath);
	}

	public void changer7() throws FileNotFoundException, IOException, ParseException {
		File deckPath = new File("src/model/decks/CustomDeck7.json");
		title = model.JSONReader.getDeckTitleFromFile(deckPath);
	}

	public void changer8() throws FileNotFoundException, IOException, ParseException {
		File deckPath = new File("src/model/decks/CustomDeck8.json");
		title = model.JSONReader.getDeckTitleFromFile(deckPath);
	}

	public void changer9() throws FileNotFoundException, IOException, ParseException {
		File deckPath = new File("src/model/decks/CustomDeck9.json");
		title = model.JSONReader.getDeckTitleFromFile(deckPath);
	}

	public void changer10() throws FileNotFoundException, IOException, ParseException {
		File deckPath = new File("src/model/decks/CustomDeck10.json");
		title = model.JSONReader.getDeckTitleFromFile(deckPath);
	}

}
