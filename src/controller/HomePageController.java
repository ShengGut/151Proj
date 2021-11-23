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
	@FXML MenuButton menuBtn;
	@FXML Label totalCardsLbl, newCardLbl;
	int newCardIni, newCardAft, totalCards;
	
	//This is the controller to switch to the main HomePage
	public void switchToHomePage(ActionEvent event) throws IOException, ParseException, FileNotFoundException {
		FlowPane root = (FlowPane)FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//This is the controller to switch to the main DecksPage
	public void switchToDecksPage(ActionEvent event) throws IOException {
		FlowPane root = (FlowPane)FXMLLoader.load(getClass().getResource("../view/DecksPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
			
	//This is the controller to switch to the HelpPage
	public void switchToHelpPage(ActionEvent event) throws IOException {
		FlowPane root = (FlowPane)FXMLLoader.load(getClass().getResource("../view/HelpPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//This is the controller to switch to the StudyPage
	public void switchToStudyPage(ActionEvent event) throws IOException {
		FlowPane root = (FlowPane)FXMLLoader.load(getClass().getResource("../view/StudyPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//This is the controller for the deckChooser button
	public void deckChooserBtn(ActionEvent event) throws IOException {
		popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Deck Viewer");
		FlowPane root = (FlowPane)FXMLLoader.load(getClass().getResource("../view/DeckChooserPopup.fxml"));
		scene = new Scene(root);
		popup.setScene(scene);
		popup.showAndWait();;
	}
	
	public int setNumberOfCards(File filePath) throws FileNotFoundException, IOException, ParseException {
		ArrayList<Card> cards = model.JSONReader.getArrayOfDeck(filePath);
		newCardIni = totalCards;
		totalCards = (cards.size());
		totalCardsLbl.setText("" + totalCards);
		System.out.println(newCardIni);
		System.out.println(totalCards);
		return totalCards;
	}
	
	public void initialize() {
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				  try {
					setNumberOfCards(new File("src/model/decks/DefaultDeck.json"));
				} catch (IOException | ParseException e) {
					e.printStackTrace();
				}
			  }
			});
	}
	
	//Increase the amount totalCards
	public void addCards(ActionEvent event) throws IOException, ParseException {
		popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Add Card");
		FlowPane root = (FlowPane)FXMLLoader.load(getClass().getResource("../view/AddCardPage.fxml"));
		scene = new Scene(root);
		popup.setScene(scene);
		popup.showAndWait();
		setNumberOfCards(new File("src/model/decks/DefaultDeck.json"));
		if(newCardIni + 1 == totalCards) {
			newCardAft++;
			newCardLbl.setText("" + newCardAft);
		}
	}
}
