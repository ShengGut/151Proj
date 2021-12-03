package controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CardDeleterController {
	@FXML private TextField frontEntry;
	@FXML private TextField backEntry;
	
	private int cardID = 0;

	
	
	// Call JSONWriter method in another class to delete the card
	public void deleteCard(ActionEvent event) throws FileNotFoundException, IOException, ParseException {
		
		String title = "DefaultDeck";
		model.JSONWriter.removeCard(title, cardID);
		
		
		// Close pop-up window 
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setTitle("Card deleted");
		window.close();
	}

}
