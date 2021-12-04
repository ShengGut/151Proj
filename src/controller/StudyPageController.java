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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class StudyPageController extends SceneController {

	private double newX = 0;
	int startingCard = 1;
	int i, pi;
	private int cardID;
	int totalCards, frontside, backside, toggle = 0;
	@FXML Label frontSide, backSide, cardPosition, deckTitle, removeLbl;
	@FXML Button removeBtn, confirmBtn, finishBtn, easyBtn, normalBtn, hardBtn, difficultBtn;
	@FXML Line line1, line2;
	static int x = 0;

	// Test
	static String title = "DefaultDeck";
	//static File path = model.JSONReader.getDeckFileFromTitle(title);
	static File deckPath = new File("src/model/decks/" + title + ".json");

	// added from StudyController
	private static int rating;

	// This is for the toggle button that displays the cards side-by-side
	@FXML
	Rectangle firstSquare, secondSquare, blocker;
	@FXML
	Polygon leftTriangle, rightTriangle;

	public int setNumberOfCards() throws FileNotFoundException, IOException, ParseException {
		ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(title);
		totalCards = (Cards.size());
		return totalCards;
	}
	
	public int setNumberOfCards2() throws FileNotFoundException, IOException, ParseException {
		ArrayList<Card> Cards = controller.CardReviewer.returnShowingCards(title);
		totalCards = (Cards.size());
		return totalCards;
	}
	
	// Initialize the text to show the total amount of Cards in the deck
	public void initialize() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					deckTitle.setText(title);
					if (setNumberOfCards() == 0) {
						if (x == 0) {
							easyBtn.setTranslateY(200);
							normalBtn.setTranslateY(200);
							hardBtn.setTranslateY(200);
							difficultBtn.setTranslateY(200);
						} else {
							easyBtn.setTranslateY(0);
							normalBtn.setTranslateY(0);
							hardBtn.setTranslateY(0);
							difficultBtn.setTranslateY(-0);
						}
						deckTitle.setText(title);
						System.out.println(title);
						startingCard = 0;
						cardPosition.setText(startingCard + "/" + setNumberOfCards());
						frontSide.setText("Hello, please add a card");
						backSide.setText("Professor Yazdankhah is the best");
					} else {
						if (x == 0) {
							easyBtn.setTranslateY(200);
							normalBtn.setTranslateY(200);
							hardBtn.setTranslateY(200);
							difficultBtn.setTranslateY(200);
							cardPosition.setText(startingCard + "/" + setNumberOfCards());
							model.JSONReader.getArrayOfDeck(title);
							ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(title);
							// set the frontSide and backSide text on the first card to display
							frontSide.setText(Cards.get(frontside).getFrontSide());
							backSide.setText(Cards.get(backside).getBackSide());
							// Get initial Card ID
							cardID = Cards.get(i).getID();
						} else {
							easyBtn.setTranslateY(0);
							normalBtn.setTranslateY(0);
							hardBtn.setTranslateY(0);
							difficultBtn.setTranslateY(-0);
							ArrayList<Card> Cards = controller.CardReviewer.returnShowingCards(title);
							if (setNumberOfCards2() == 0) {
								cardPosition.setText("0/0");
								frontSide.setText("You Are Done Reviewing");
								backSide.setText("Again, Professor Yazdankhah is the best");
							} else {
								cardPosition.setText(startingCard + "/" + setNumberOfCards2());
								// set the frontSide and backSide text on the first card to display
								frontSide.setText(Cards.get(frontside).getFrontSide());
								backSide.setText(Cards.get(backside).getBackSide());
								// Get initial Card ID
								cardID = Cards.get(i).getID();
							}
						}
					}
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
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
		if ((x == 0) && (startingCard != setNumberOfCards())) {
				ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(title);
				// Go through Cards to get their ID for removeCard
				i++;
				// Make cardID equal to the cardID of the index of i
				cardID = Cards.get(i).getID();
				frontside++;
				backside++;
				startingCard++;
				cardPosition.setText(startingCard + "/" + setNumberOfCards());
				frontSide.setText(Cards.get(frontside).getFrontSide());
				backSide.setText(Cards.get(backside).getBackSide());
				//Change for review
		}
		if ((x == 1) && (startingCard != setNumberOfCards2())) {
				ArrayList<Card> Cards = controller.CardReviewer.returnShowingCards(title);
				// Go through Cards to get their ID for removeCard
				i++;
				// Make cardID equal to the cardID of the index of i
				cardID = Cards.get(i).getID();
				frontside++;
				backside++;
				startingCard++;
				cardPosition.setText(startingCard + "/" + setNumberOfCards2());
				frontSide.setText(Cards.get(frontside).getFrontSide());
				backSide.setText(Cards.get(backside).getBackSide());
		} else if ((x == 1) && (pi == 1)) {
			pi = 0;
		}
		if (startingCard == 10) {
			cardPosition.setPadding(new Insets(0, 0, 0, -4));
		}
	}

	public void backArrow(MouseEvent event) throws IOException, ParseException {
		if (startingCard != 1 && startingCard != 0) {
			if (x == 0) {
				ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(title);
				// Get the previous index
				i--;
				// Make cardID equal to the cardID of the index of i
				cardID = Cards.get(i).getID();
				frontside--;
				backside--;
				startingCard--;
				cardPosition.setText(startingCard + "/" + setNumberOfCards());
				frontSide.setText(Cards.get(frontside).getFrontSide());
				backSide.setText(Cards.get(backside).getBackSide());
			} else {
				ArrayList<Card> Cards = controller.CardReviewer.returnShowingCards(title);
				// Get the previous index
				i--;
				// Make cardID equal to the cardID of the index of i
				cardID = Cards.get(i).getID();
				frontside--;
				backside--;
				startingCard--;
				cardPosition.setText(startingCard + "/" + setNumberOfCards2());
				frontSide.setText(Cards.get(frontside).getFrontSide());
				backSide.setText(Cards.get(backside).getBackSide());
			}
			if (startingCard == 9) {
				cardPosition.setPadding(new Insets(0, 0, 0, 0));
			}
		}
	}
	
	//When there are only two review cards left
	public void reviewAlmostFin() throws FileNotFoundException, IOException, ParseException {
		if(startingCard == 1 && setNumberOfCards2() == 2) {
			ArrayList<Card> Cards = controller.CardReviewer.returnShowingCards(title);
			i++;
			// Make cardID equal to the cardID of the index of i
			cardID = Cards.get(i).getID();
			frontside++;
			backside++;
			frontSide.setText(Cards.get(frontside).getFrontSide());
			backSide.setText(Cards.get(backside).getBackSide());
			cardPosition.setText("1/1");
		}
	}
	
	//When you are finished reviewing
	public void reviewFin() throws FileNotFoundException, IOException, ParseException {
		if (startingCard == setNumberOfCards2()) {
			startingCard = 0;
			cardPosition.setText("0/0");
			frontSide.setText("DONE");
		}
	}
	
	public void getRatingEntry(ActionEvent event) throws FileNotFoundException, IOException, ParseException {
		Object node = event.getSource();
		Button b = (Button) node;
		System.out.println(b.getText());
		if (b.getText().equals("Easy")) {
			rating = 1;
			reviewFin();
			reviewAlmostFin();
		}
		else if (b.getText().equals("Normal"))  {
			rating = 2;
			reviewFin();
			reviewAlmostFin();
		}
		else if (b.getText().equals("Hard")) {
			rating = 3;
			reviewFin();
			reviewAlmostFin();
		}
		else if (b.getText().equals("Difficult")) {
			rating = 4;
			reviewFin();
			reviewAlmostFin();
		}
		System.out.println("rating: " + rating);
	}

	// call studyCalculation in CardReviewer on action when rating is selected
	public void updateCardStats(ActionEvent event) throws IOException, ParseException {
		if (x == 0) {
			if (setNumberOfCards() > 0) {
				getRatingEntry(event);
				Card card = model.JSONReader.generateCard(title, cardID - 1);
				CardReviewer.studyCalculation(card, rating);
			}
		}
		if (x == 1) {
			if (setNumberOfCards2() > 0) {
				getRatingEntry(event);
				Card card = model.JSONReader.generateCard(title, cardID - 1);
				CardReviewer.studyCalculation(card, rating);
			}
		}
	}

	public void confirmDeletion(ActionEvent event) throws IOException {
		if (startingCard != 0) {
		removeBtn.setTranslateY(100);
		finishBtn.setTranslateY(-80);
		finishBtn.setTranslateX(37);
		confirmBtn.setTranslateY(-80);
		confirmBtn.setTranslateX(42);
		removeLbl.setOpacity(1);
		line1.setOpacity(1);
		line2.setOpacity(1);
		}
	}
	
	public void cancelDeletion(ActionEvent event) throws IOException {
		removeBtn.setTranslateY(0);
		finishBtn.setTranslateY(0);
		finishBtn.setTranslateX(0);
		confirmBtn.setTranslateY(0);
		confirmBtn.setTranslateX(0);
		removeLbl.setOpacity(0);
		line1.setOpacity(0);
		line2.setOpacity(0);
	}
	
	// Call JSONWriter method in another class to delete the card
	public void deleteCard(ActionEvent event) throws FileNotFoundException, IOException, ParseException {
		// The deleteCard function only works if there is a card in the deck
		if (startingCard != 0) {
			model.JSONWriter.removeCard(title, cardID);
			// If the user tries to delete a card starting from 2+, after removing the card
			// it would transition to the previous card
			if (startingCard != 1) {
				backArrow(null);
				// If the user tries to remove the first card, then it will transition to the
				// second card
			} else if ((x== 0) && (startingCard == 1 && setNumberOfCards() != 0)) {
				ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(title);
				cardPosition.setText(startingCard + "/" + setNumberOfCards());
				frontSide.setText(Cards.get(frontside).getFrontSide());
				backSide.setText(Cards.get(backside).getBackSide());
				// Get the next cardID
				cardID = Cards.get(i).getID();
				System.out.println(cardID);
			} else if ((x == 1) && (startingCard == 1 && setNumberOfCards2() != 0)) {
				ArrayList<Card> Cards = controller.CardReviewer.returnShowingCards(title);
				cardPosition.setText(startingCard + "/" + setNumberOfCards2());
				frontSide.setText(Cards.get(frontside).getFrontSide());
				backSide.setText(Cards.get(backside).getBackSide());
				// Get the next cardID
				cardID = Cards.get(i).getID();
				System.out.println(cardID);
				// If there are no more card, then set startingCard to display 0/0
			} else {
				startingCard = 0;
				if (x == 0) {
					cardPosition.setText(startingCard + "/" + setNumberOfCards());
					frontSide.setText("All Cards Have Been Deleted");
					backSide.setText("Add A New Card");
				}
				if (x == 1) {
					cardPosition.setText(startingCard + "/" + setNumberOfCards2());
					frontSide.setText("You Are Done Reviewing");
					backSide.setText("Go and Click the Study Button to Study More While You Wait");
				}
			}
		}
		if (startingCard == 0) {
			removeBtn.setTranslateY(0);
			finishBtn.setTranslateY(0);
			finishBtn.setTranslateX(0);
			confirmBtn.setTranslateY(0);
			confirmBtn.setTranslateX(0);
			removeLbl.setOpacity(0);
			line1.setOpacity(0);
			line2.setOpacity(0);
		}
	}

	public static void switchxTo0() {
		x = 0;	
	}
	
	public static void switchxTo1() {
		x = 1;
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
