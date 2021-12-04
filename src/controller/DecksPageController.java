package controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DecksPageController extends SceneController {

	@FXML
	private TextField titleEntry;
	@FXML
	StackPane stckPane;
	private Stage stage;
	private Scene scene;
	private String title, oldTitle;
	int x = 260;
	HomePageController hp = new HomePageController();
	MakeCardController mc = new MakeCardController();
	StudyPageController sp = new StudyPageController();
	CardReviewer cr = new CardReviewer();

	public void titleEntry() {
		title = titleEntry.getText();
	}

	// This is the controller to switch to DefaultDeck
	public void switchToStudyPage1(MouseEvent event) throws IOException, ParseException {
		hp.changer1();
		mc.changer1();
		sp.changer1();
		cr.changer1();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to CustomDeck2
	public void switchToStudyPage2(MouseEvent event) throws IOException, ParseException {
		hp.changer2();
		mc.changer2();
		sp.changer2();
		cr.changer2();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to CustomDeck3
	public void switchToStudyPage3(MouseEvent event) throws IOException, ParseException {
		hp.changer3();
		mc.changer3();
		sp.changer3();
		cr.changer3();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to CustomDeck4
	public void switchToStudyPage4(MouseEvent event) throws IOException, ParseException {
		hp.changer4();
		mc.changer4();
		sp.changer4();
		cr.changer4();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to CustomDeck5
	public void switchToStudyPage5(MouseEvent event) throws IOException, ParseException {
		hp.changer5();
		mc.changer5();
		sp.changer5();
		cr.changer5();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to CustomDeck6
	public void switchToStudyPage6(MouseEvent event) throws IOException, ParseException {
		hp.changer6();
		mc.changer6();
		sp.changer6();
		cr.changer6();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to CustomDeck7
	public void switchToStudyPage7(MouseEvent event) throws IOException, ParseException {
		hp.changer7();
		mc.changer7();
		sp.changer7();
		cr.changer7();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to CustomDeck8
	public void switchToStudyPage8(MouseEvent event) throws IOException, ParseException {
		hp.changer8();
		mc.changer8();
		sp.changer8();
		cr.changer8();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to CustomDeck9
	public void switchToStudyPage9(MouseEvent event) throws IOException, ParseException {
		hp.changer9();
		mc.changer9();
		sp.changer9();
		cr.changer9();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This is the controller to switch to CustomDeck10
	public void switchToStudyPage10(MouseEvent event) throws IOException, ParseException {
		hp.changer10();
		mc.changer10();
		sp.changer10();
		cr.changer10();
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// This controller allows you to select a deck
	@FXML Button deck1Btn, deck2Btn, deck3Btn, deck4Btn, deck5Btn, deck6Btn, deck7Btn, deck8Btn, deck9Btn, deck10Btn;
	@FXML Label deck1, deck2, deck3, deck4, deck5, deck6, deck7, deck8, deck9, deck10, deckSelectedLbl;
	public void deckChooser(ActionEvent event) throws IOException {
		Object node = event.getSource();
		Button b = (Button) node;
		if (b.getText().equals("Deck 1")) {
			deckSelectedLbl.setText("*Deck 1 Selected");
			oldTitle = deck1.getText();
			System.out.println(oldTitle);
		} else if (b.getText().equals("Deck 2")) {
			deckSelectedLbl.setText("*Deck 2 Selected");
			oldTitle = deck2.getText();
			System.out.println(oldTitle);
		} else if (b.getText().equals("Deck 3")) {
			deckSelectedLbl.setText("*Deck 3 Selected");
			oldTitle = deck3.getText();
		} else if (b.getText().equals("Deck 4")) {
			deckSelectedLbl.setText("*Deck 4 Selected");
			oldTitle = deck4.getText();
		} else if (b.getText().equals("Deck 5")) {
			deckSelectedLbl.setText("*Deck 5 Selected");
			oldTitle = deck5.getText();
		} else if (b.getText().equals("Deck 6")) {
			deckSelectedLbl.setText("*Deck 6 Selected");
			oldTitle = deck6.getText();
		} else if (b.getText().equals("Deck 7")) {
			deckSelectedLbl.setText("*Deck 7 Selected");
			oldTitle = deck7.getText();
		} else if (b.getText().equals("Deck 8")) {
			deckSelectedLbl.setText("*Deck 8 Selected");
			oldTitle = deck8.getText();
		} else if (b.getText().equals("Deck 9")) {
			deckSelectedLbl.setText("*Deck 9 Selected");
			oldTitle = deck9.getText();
		} else if (b.getText().equals("Deck 10")) {
			deckSelectedLbl.setText("*Deck 10 Selected");
			oldTitle = deck10.getText();
		}
	}
	
	// This Creates the new Deck
	public void changeDeckName(ActionEvent event) throws IOException, ParseException {
		titleEntry();
		System.out.println(oldTitle);
		System.out.println(title);
		model.JSONWriter.publicUpdateDeckTitle(oldTitle, title);
		deck2.setText(title);
		// clear the TextEntry when finished
		titleEntry.clear();
	}

}
