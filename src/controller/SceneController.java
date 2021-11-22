package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneController {

	private Stage stage, popup;
	private Scene scene;

	//This is the controller to switch to the main HomePage
	public void switchToHomePage(ActionEvent event) throws IOException {
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
	
}
