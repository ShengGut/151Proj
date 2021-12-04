package controller;

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
	@FXML MenuButton menuBtn;
	@FXML Label totalCardsLbl, newCardLbl, reviewLbl;
	int newCardIni, newCardAft, totalCards, newCardReviewIni, totalCardsReview;
	@FXML Label deckTitle;
	static String title = "DefaultDeck";
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
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/DecksPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to the HelpPage
	public void switchToHelpPage(ActionEvent event) throws IOException {
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
	
	// This is the controller to switch to the StudyPage
	public void switchToReviewPage(ActionEvent event) throws IOException {
		controller.StudyPageController.switchxTo1();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/StudyPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller for the deckChooser button
	public void deckChooserBtn(ActionEvent event) throws IOException {
		popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Deck Viewer");
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/DeckChooserPopup.fxml"));
		scene = new Scene(root);
		popup.setScene(scene);
		popup.showAndWait();
		;
	}

	public int setNumberOfCards() throws FileNotFoundException, IOException, ParseException {
		ArrayList<Card> cards = model.JSONReader.getArrayOfDeck(title);
		newCardIni = totalCards;
		totalCards = (cards.size());
		totalCardsLbl.setText("" + totalCards);
		return totalCards;
	}
	
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

	/*
	 * This is for testing the new stuff
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
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
