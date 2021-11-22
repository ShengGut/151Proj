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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class StudyPageController extends SceneController {

	private double newX = 0;
	int frontside = 0;
	int backside = 0;
	int totalCards;
	int startingCard = 1;
	private int cardID = 0;
	@FXML Label frontSide;
	@FXML Label backSide;
	@FXML Label cardPosition;
	
	// This is for the toggle button that displays the cards side-by-side
	@FXML
	Rectangle firstSquare;
	@FXML
	Rectangle secondSquare;
	@FXML
	Polygon leftTriangle;
	@FXML
	Polygon rightTriangle;
	
	public int setNumberOfCards(File filePath) throws FileNotFoundException, IOException, ParseException {
		ArrayList<Card> cards = model.JSONReader.getArrayOfDeck(filePath);
		totalCards = (cards.size());
		return totalCards;
	}
	
	//Initialize the text to show the total amount of Cards in the deck
	public void initialize() {
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  try {
					cardPosition.setText(startingCard + "/" + setNumberOfCards(new File("src/model/decks/DefaultDeck.json")));
					File deckPath = new File("src/model/decks/DefaultDeck.json");
					model.JSONReader.getArrayOfDeck(deckPath);
					ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(deckPath);
					frontSide.setText(Cards.get(frontside).getFrontSide());
					backSide.setText(Cards.get(backside).getBackSide());
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			});
	}
	
	public void ToggleBtn(ActionEvent event) throws IOException, ParseException {
		if (newX == 0) {
			newX = -200;
			firstSquare.setTranslateX(newX);
			frontSide.setTranslateX(newX);
			newX = 200;
			secondSquare.setTranslateX(newX);
			backSide.setTranslateX(newX);
			leftTriangle.setVisible(false);
			rightTriangle.setVisible(false);
			backSide.setOpacity(1);
		} else {
			newX = 0;
			firstSquare.setTranslateX(newX);
			frontSide.setTranslateX(newX);
			secondSquare.setTranslateX(newX);
			backSide.setTranslateX(newX);
			leftTriangle.setVisible(true);
			rightTriangle.setVisible(true);
			backSide.setOpacity(0);
		}
	}
	
	public void nextArrow(MouseEvent event) throws IOException, ParseException {
		if (startingCard != setNumberOfCards(new File("src/model/decks/DefaultDeck.json"))) {
			File deckPath = new File("src/model/decks/DefaultDeck.json");
			ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(deckPath);
			frontside++;
			backside++;
			startingCard++;
			cardPosition.setText(startingCard + "/" + setNumberOfCards(new File("src/model/decks/DefaultDeck.json")));
			frontSide.setText(Cards.get(frontside).getFrontSide());
			backSide.setText(Cards.get(backside).getBackSide());
			if (startingCard == 10) {
				cardPosition.setPadding(new Insets(0, 0, 0, -3));
			}
		}
	}
	
	public void backArrow(MouseEvent event) throws IOException, ParseException {
		if (startingCard != 1) {
		File deckPath = new File("src/model/decks/DefaultDeck.json");
		ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(deckPath);
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
	

}
