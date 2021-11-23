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
	
	private String frontSide = "";
	private String backSide = "";
	private int rating = 0;
	private int repetition = 0;
	private int interval = 1;
	private long nextReviewDate;
	
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
		
		File path = new File("src/model/decks/DefaultDeck.json");
		
		
		model.JSONWriter.createCard(path, frontSide, backSide, rating, repetition, interval, nextReviewDate); 
		
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
	
}

	
	
