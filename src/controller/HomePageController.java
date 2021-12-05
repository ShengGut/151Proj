package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.JSONReader;

public class HomePageController extends JSONReader {

	private Stage stage, popup;
	private Scene scene;
	@FXML
	MenuButton menuBtn;
	@FXML
	Label totalCardsLbl, newCardLbl, reviewLbl;
	int newCardIni, newCardAft, totalCards, newCardReviewIni, totalCardsReview;
	@FXML
	Label deckTitle;
	static String title;
	StudyPageController sp = new StudyPageController();

	// This is the controller to switch to the main HomePage
	public void switchToHomePage(ActionEvent event) throws IOException, ParseException, FileNotFoundException {
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to the main DecksPage
	public void switchToDecksPage(ActionEvent event) throws IOException {
		controller.DecksPageController.homeBtnCover();
		controller.SceneController.homeBtnCover();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/DecksPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to the HelpPage
	public void switchToHelpPage(ActionEvent event) throws IOException {
		controller.DecksPageController.homeBtnCover();
		controller.SceneController.homeBtnCover();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HelpPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to the StudyPage
	public void switchToStudyPage(ActionEvent event) throws IOException {
		controller.StudyPageController.switchxTo0();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/StudyPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to the ReviewPage
	public void switchToReviewPage(ActionEvent event) throws IOException {
		controller.StudyPageController.switchxTo1();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/StudyPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	//This displays the total number of cards in the deck
	public int setNumberOfCards() throws FileNotFoundException, IOException, ParseException {
		ArrayList<Card> cards = model.JSONReader.getArrayOfDeck(title);
		newCardIni = totalCards;
		totalCards = (cards.size());
		totalCardsLbl.setText("" + totalCards);
		return totalCards;
	}
	
	//This displays the total number of review cards in the deck
	public int setNumberOfReviews() throws FileNotFoundException, IOException, ParseException {
		ArrayList<Card> cards = controller.CardReviewer.returnShowingCards(title);
		newCardReviewIni = totalCardsReview;
		totalCardsReview = (cards.size());
		reviewLbl.setText("" + totalCardsReview);
		return totalCardsReview;
	}

	public void initialize() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					deckTitle.setText(title);
					setNumberOfCards();
					setNumberOfReviews();
				} catch (IOException | ParseException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Increase the amount totalCards
	public void addCards(ActionEvent event) throws IOException, ParseException {
		popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Add Card");
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/AddCardPage.fxml"));
		scene = new Scene(root);
		popup.setScene(scene);
		popup.showAndWait();
		setNumberOfCards();
		if (newCardIni + 1 == totalCards) {
			newCardAft++;
			newCardLbl.setText("" + newCardAft);
			setNumberOfReviews();
		}
	}


	//Change the deckPath
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
