package controller;

import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudyController {
	
	@FXML private Label ratingEntry;
	private static int rating;

	public void ratingEntry() {
		rating = Integer.valueOf(ratingEntry.getText());
	}

	// call studyCalculation in CardReviewer on action when rating is selected
	public static void updateCardStats(ActionEvent event) throws IOException, ParseException {
		String deckTitle = "DefaultDeck";
		Card card = model.JSONReader.generateCard(deckTitle, 0);
		CardReviewer.studyCalculation(card, rating);
	}

}
