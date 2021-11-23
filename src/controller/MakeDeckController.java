package controller;

import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MakeDeckController {
	@FXML private TextField titleEntry;
	@FXML private VBox vBoxYuh;
	private Scene scene;
	
	private String title;

	public void titleEntry() {
		title = titleEntry.getText();
	}
	
	public void createDeck (ActionEvent event) throws IOException, ParseException {
		
		titleEntry();
		File newDeck = new File("src/model/decks/" + title + ".json");
		newDeck.createNewFile();
		
		// close window
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setTitle("Deck created");
		window.close();
	    
	}
	
	//This is the controller for the addCard button
	public void deckAdder(ActionEvent event) throws IOException {
		Stage popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		FlowPane root = (FlowPane)FXMLLoader.load(getClass().getResource("../view/AddDeck.fxml"));
		scene = new Scene(root);
		popup.setScene(scene);
		popup.showAndWait();;
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.show();
		Label b = new Label("jagjag");
		b.setFont(new Font(16));
		vBoxYuh.getChildren().addAll(b);
		b.setTextFill(Color.web("#000000"));
		/*
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../controller/DecksPageController.java"));
		HomePageController controller = loader.getController();
		controller.deckAdder2(event);
		*/
	}
	
	public void closeWindow(ActionEvent event) throws IOException {
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
}