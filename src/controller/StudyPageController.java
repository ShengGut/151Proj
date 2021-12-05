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
	private int cardID;
	int totalCards, totalCards2, index, toggle;
	@FXML Label frontSide, backSide, cardPosition, deckTitle, removeLbl, questLbl, ansLbl;
	@FXML Button removeBtn, confirmBtn, finishBtn, easyBtn, normalBtn, hardBtn, difficultBtn;
	@FXML Line line1, line2;
	static int x = 0;
	static String title;
	private ArrayList<Card> Cards;
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
		totalCards2 = (Cards.size());
		System.out.println("This is the total cards " + totalCards2);
		if(totalCards2 == 0) {
			//cardID = 0;
			index = 0;
		}
		return totalCards2;
	}
	
	// Initialize the text to show the total amount of Cards in the deck
	public void initialize() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					deckTitle.setText(title);
					ansLbl.setOpacity(0);
					if (x == 0) {
						easyBtn.setTranslateY(200);
						normalBtn.setTranslateY(200);
						hardBtn.setTranslateY(200);
						difficultBtn.setTranslateY(200);
						removeBtn.setTranslateX(0);
						if (setNumberOfCards() == 0) {
							deckTitle.setText(title);
							System.out.println(title);
							startingCard = 0;
							cardPosition.setText(startingCard + "/" + setNumberOfCards());
							frontSide.setText("Hello, please add a card");
							backSide.setText("Professor Yazdankhah is the best");
						} else {
							cardPosition.setText(startingCard + "/" + setNumberOfCards());
							model.JSONReader.getArrayOfDeck(title);
							Cards = model.JSONReader.getArrayOfDeck(title);
							// set the frontSide and backSide text on the first card to display
							frontSide.setText(Cards.get(index).getFrontSide());
							backSide.setText(Cards.get(index).getBackSide());
							// Get initial Card ID
							System.out.println("index a " + index);
							cardID = Cards.get(index).getID();
							System.out.println("cardID a " + cardID);
						}
					}
					if(x == 1) {
						easyBtn.setTranslateY(0);
						normalBtn.setTranslateY(0);
						hardBtn.setTranslateY(0);
						difficultBtn.setTranslateY(0);
						removeBtn.setTranslateY(200);
						rightTriangle.setTranslateY(400);
						leftTriangle.setTranslateY(400);
						if (setNumberOfCards2() == 0) {
							cardPosition.setText("0/0");
							frontSide.setText("You Are Done Reviewing");
							backSide.setText("Again, Professor Yazdankhah is the best");
						} else {
							ArrayList<Card> Cards = controller.CardReviewer.returnShowingCards(title);
							cardPosition.setText(startingCard + "/" + setNumberOfCards2());
							// set the frontSide and backSide text on the first card to display
							frontSide.setText(Cards.get(index).getFrontSide());
							backSide.setText(Cards.get(index).getBackSide());
							// Get initial Card ID
							System.out.println("index b " + index);
							cardID = Cards.get(index).getID();
							System.out.println("cardID b " + cardID);
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
			questLbl.setTranslateX(newX);
			newX = 200;
			secondSquare.setTranslateX(newX);
			backSide.setTranslateX(newX);
			ansLbl.setOpacity(1);
			ansLbl.setTranslateX(newX);
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
			questLbl.setTranslateX(newX);
			secondSquare.setTranslateX(newX);
			backSide.setTranslateX(newX);
			ansLbl.setOpacity(0);
			ansLbl.setTranslateX(newX);
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
			questLbl.setOpacity(0);
			ansLbl.setOpacity(1);
			toggle = 1;
		} else if (toggle == 1) {
			backSide.setOpacity(0);
			frontSide.setOpacity(1);
			questLbl.setOpacity(1);
			ansLbl.setOpacity(0);
			toggle = 0;
		}
	}

	public void nextArrow(MouseEvent event) throws IOException, ParseException {
		if ((x == 0) && (startingCard != setNumberOfCards())) {
				ArrayList<Card> Cards = model.JSONReader.getArrayOfDeck(title);
				// Go through Cards to get their ID for removeCard
				index++;
				// Make cardID equal to the cardID of the index of i
				cardID = Cards.get(index).getID();
				startingCard++;
				cardPosition.setText(startingCard + "/" + setNumberOfCards());
				frontSide.setText(Cards.get(index).getFrontSide());
				backSide.setText(Cards.get(index).getBackSide());
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
				index--;
				// Make cardID equal to the cardID of the index of i
				cardID = Cards.get(index).getID();
				startingCard--;
				cardPosition.setText(startingCard + "/" + setNumberOfCards());
				frontSide.setText(Cards.get(index).getFrontSide());
				backSide.setText(Cards.get(index).getBackSide());
			}
			if (startingCard == 9) {
				cardPosition.setPadding(new Insets(0, 0, 0, 0));
			}
		}
	}
	
	public void getRatingEntry(ActionEvent event) throws FileNotFoundException, IOException, ParseException {
		Object node = event.getSource();
		Button b = (Button) node;
		System.out.println(b.getText());
		if (b.getText().equals("Easy")) {
			rating = 1;
		}
		else if (b.getText().equals("Normal"))  {
			rating = 2;
		}
		else if (b.getText().equals("Hard")) {
			rating = 3;

		}
		else if (b.getText().equals("Difficult")) {
			rating = 4;
		}

		System.out.println("Break " + totalCards2);
		System.out.println("index " + index);
		if (totalCards2 != 1) {
			ArrayList<Card> Cards = controller.CardReviewer.returnShowingCards(title);
			System.out.println(cardID + " This is card ID");
			frontSide.setText(Cards.get(1).getFrontSide());
			backSide.setText(Cards.get(1).getBackSide());
			cardPosition.setText("1/" + (totalCards2-1));
		}
		if (totalCards2 == 1) {
			frontSide.setText("Done");
			cardPosition.setText("0/0");
		}
		// Get initial Card ID
		ArrayList<Card> Cards = controller.CardReviewer.returnShowingCards(title);
		System.out.println("index c " + index);
		cardID = Cards.get(index).getID();
		System.out.println("cardID c " + cardID);
		System.out.println("rating: " + rating);
		setNumberOfCards2();
		
	}

    // call studyCalculation in CardReviewer on action when rating is selected
    public void updateCardStats(ActionEvent event) throws IOException, ParseException {
        if (setNumberOfCards2() > 0) {
            getRatingEntry(event);
            int cardIndex = model.JSONReader.cardIDToArrayIndex(title, cardID);
            System.out.println("ucs cardid " + cardID);
            System.out.println("ucs cardindex " + cardIndex);
        	Card card = model.JSONReader.generateCard(title, cardIndex);
            CardReviewer.studyCalculation(card, rating);

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
				frontSide.setText(Cards.get(index).getFrontSide());
				backSide.setText(Cards.get(index).getBackSide());
				// Get the next cardID
				cardID = Cards.get(index).getID();
				System.out.println(cardID);
			} else {
				startingCard = 0;
				if (x == 0) {
					cardPosition.setText(startingCard + "/" + setNumberOfCards());
					frontSide.setText("All Cards Have Been Deleted");
					backSide.setText("Add A New Card");
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
