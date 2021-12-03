package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MakeCardController {
	@FXML private TextArea frontEntry;
	@FXML private TextArea backEntry;
	@FXML private TextField ratingEntry;
	@FXML private Button close;
	static String title = "DefaultDeck.json";
	
	private String frontSide = "";
	private String backSide = "";
	private int rating = 0;
	private int repetition = 0;
	private int interval = 1;
	private long nextReviewDate;
	private long exactReviewMoment;
	
	public void frontEntry() {
		frontSide = frontEntry.getText();
	}
	public void backEntry() {
		backSide = backEntry.getText();
	}
	public void ratingEntry() {
		rating = Integer.valueOf(ratingEntry.getText());
	}
	
	
	public void createNewCard(ActionEvent event) throws FileNotFoundException, IOException, ParseException {
		
		frontEntry();
		backEntry();
		//ratingEntry();
		
		File path = new File("src/model/decks/"+title);
		
		model.JSONWriter.createCard(path, frontSide, backSide, rating, repetition, interval, nextReviewDate, exactReviewMoment); 

		
		// Close pop-up window 
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setTitle("Card created");
		window.close();
	}
	
	public void closeWindow(ActionEvent event) throws IOException {
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	public void changer1() {
		title = "DefaultDeck.json";
	}
	
	public void changer2() {
		title = "CustomDeck2.json";
	}
	
	public void changer3() {
		title = "CustomDeck3.json";
	}
	
	public void changer4() {
		title = "CustomDeck4.json";
	}
	
	public void changer5() {
		title = "CustomDeck5.json";
	}
	
	public void changer6() {
		title = "CustomDeck6.json";
	}
	
	public void changer7() {
		title = "CustomDeck7.json";
	}
	
	public void changer8() {
		title = "CustomDeck8.json";
	}
	
	public void changer9() {
		title = "CustomDeck9.json";
	}
	
	public void changer10() {
		title = "CustomDeck10.json";
	}

	
}

	
	
