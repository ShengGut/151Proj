package controller;

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
	static String title = "DefaultDeck";
	
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
		
		model.JSONWriter.createCard(title, frontSide, backSide, rating, repetition, interval, nextReviewDate); 

		
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