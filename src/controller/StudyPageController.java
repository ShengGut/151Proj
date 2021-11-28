package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class StudyPageController extends SceneController {

	private double newX = 0;
	int startingCard = 1;
	int i;
	private int cardID;
	int totalCards, frontside, backside, toggle = 0;
	@FXML
	Label frontSide, backSide, cardPosition;
	
	// added from StudyController
	private static int rating;
	
	public void getRatingEntry(ActionEvent event) {
		Object node = event.getSource();
		ToggleButton b = (ToggleButton) node;
		System.out.println(b.getText());
		if (b.getText().equals("Easy"))
			rating = 1;
		else if (b.getText().equals("Normal"))
			rating = 2;
		else if (b.getText().equals("Hard"))
			rating = 3;
		else if (b.getText().equals("Difficult"))
			rating = 4;
		System.out.println("rating: " +rating);
	}

	// This is for the toggle button that displays the cards side-by-side
	@FXML
	Rectangle firstSquare, secondSquare, blocker;
	@FXML
	Polygon leftTriangle, rightTriangle;

	public int setNumberOfCards(File filePath) throws FileNotFoundException, IOException, ParseException {
		ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(filePath);
		totalCards = (Cards.size());
		return totalCards;
	}

	// Initialize the text to show the total amount of Cards in the deck
	public void initialize() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					if (setNumberOfCards(new File("src/model/decks/DefaultDeck.json")) == 0) {
						startingCard = 0;
						cardPosition.setText(startingCard + "/" + setNumberOfCards(new File("src/model/decks/DefaultDeck.json")));
						frontSide.setText("Hello, please add a card");
						backSide.setText("Professor Yazdankhah is the best");
					} else {
						cardPosition.setText(startingCard + "/" + setNumberOfCards(new File("src/model/decks/DefaultDeck.json")));
						File deckPath = new File("src/model/decks/DefaultDeck.json");
						model.JSONReader.getArrayOfDeck(deckPath);
						ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(deckPath);
						// set the frontSide and backSide text on the first card to display
						frontSide.setText(Cards.get(frontside).getFrontSide());
						backSide.setText(Cards.get(backside).getBackSide());
						// Get initial Card ID
						cardID = Cards.get(i).getID();
					}
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	// call studyCalculation in CardReviewer on action when rating is selected
		public void updateCardStats(ActionEvent event) throws IOException, ParseException {
			getRatingEntry(event);
			File path = new File("src/model/decks/DefaultDeck.json");
			Card card = model.JSONReader.generateCard(path, cardID - 1);
			CardReviewer.studyCalculation(card, rating);
		}

	public void ToggleBtn(ActionEvent event) throws IOException {
		if (newX == 0) {
			newX = -200;
			firstSquare.setTranslateX(newX);
			frontSide.setTranslateX(newX);
			newX = 200;
			secondSquare.setTranslateX(newX);
			backSide.setTranslateX(newX);
			leftTriangle.setVisible(false);
			rightTriangle.setVisible(false);
			blocker.setTranslateX(225);
			blocker.setOpacity(1);
			blocker.setFill(Color.web("f4f4f4"));
			frontSide.setOpacity(1);
			backSide.setOpacity(1);
		} else {
			newX = 0;
			firstSquare.setTranslateX(newX);
			frontSide.setTranslateX(newX);
			secondSquare.setTranslateX(newX);
			backSide.setTranslateX(newX);
			leftTriangle.setVisible(true);
			rightTriangle.setVisible(true);
			blocker.setTranslateX(0);
			blocker.setOpacity(0);
			backSide.setOpacity(0);
		}
	}

	public void toggleFlip(ActionEvent event) throws IOException {
		System.out.println("Testing toggleflip");
		if (toggle == 0) {
			backSide.setOpacity(1);
			frontSide.setOpacity(0);
			toggle = 1;
		} else if (toggle == 1) {
			backSide.setOpacity(0);
			frontSide.setOpacity(1);
			toggle = 0;
		}
	}

	public void nextArrow(MouseEvent event) throws IOException, ParseException {
		if (startingCard != setNumberOfCards(new File("src/model/decks/DefaultDeck.json"))) {
			System.out.println(setNumberOfCards(new File("src/model/decks/DefaultDeck.json")));
			System.out.println(startingCard);
			File deckPath = new File("src/model/decks/DefaultDeck.json");
			ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(deckPath);
			// Go through Cards to get their ID for removeCard
			i++;
			//Make cardID equal to the cardID of the index of i
			cardID = Cards.get(i).getID();
			frontside++;
			backside++;
			startingCard++;
			cardPosition.setText(startingCard + "/" + setNumberOfCards(new File("src/model/decks/DefaultDeck.json")));
			frontSide.setText(Cards.get(frontside).getFrontSide());
			backSide.setText(Cards.get(backside).getBackSide());
			if (startingCard == 10) {
				cardPosition.setPadding(new Insets(0, 0, 0, -4));
			}
		}
	}

	public void backArrow(MouseEvent event) throws IOException, ParseException {
		if (startingCard != 1 && startingCard != 0) {
			File deckPath = new File("src/model/decks/DefaultDeck.json");
			ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(deckPath);
			//Get the previous index
			i--;
			//Make cardID equal to the cardID of the index of i
			cardID = Cards.get(i).getID();
			frontside--;
			backside--;
			startingCard--;
			cardPosition.setText(startingCard + "/" + setNumberOfCards(new File("src/model/decks/DefaultDeck.json")));
			frontSide.setText(Cards.get(frontside).getFrontSide());
			backSide.setText(Cards.get(backside).getBackSide());
			if (startingCard == 9) {
				cardPosition.setPadding(new Insets(0, 0, 0, 0));
			}
		}
	}

	// Call JSONWriter method in another class to delete the card
	public void deleteCard(ActionEvent event) throws FileNotFoundException, IOException, ParseException {
		//The deleteCard function only works if there is a card in the deck
		if (startingCard != 0) {
			File deckPath = new File("src/model/decks/DefaultDeck.json");
			model.JSONWriter.removeCard(deckPath, cardID);
			//If the user tries to delete a card starting from 2+, after removing the card it would transition to the previous card
			if (startingCard != 1) {
				backArrow(null);
			//If the user tries to remove the first card, then it will transition to the second card
			} else if (startingCard == 1 && setNumberOfCards(new File("src/model/decks/DefaultDeck.json")) != 0) {
				ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(deckPath);
				cardPosition.setText(startingCard + "/" + setNumberOfCards(new File("src/model/decks/DefaultDeck.json")));
				frontSide.setText(Cards.get(frontside).getFrontSide());
				backSide.setText(Cards.get(backside).getBackSide());
				//Get the next cardID
				cardID = Cards.get(i).getID();
				System.out.println(cardID);
			//If there are no more card, then set startingCard to display 0/0
			} else {
				startingCard = 0;
				cardPosition.setText(startingCard + "/" + setNumberOfCards(new File("src/model/decks/DefaultDeck.json")));
				frontSide.setText("");
				backSide.setText("");
			}
		}
	}
}
