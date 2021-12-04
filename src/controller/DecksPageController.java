package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import javafx.application.Platform;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DecksPageController extends SceneController {

	@FXML
	private TextField titleEntry;
	@FXML
	StackPane stckPane;
	@FXML
	Rectangle homeCover;
	private Stage stage;
	private Scene scene;
	private String title, oldTitle;
	int x = 260, deck;
	static int cover;
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
	@FXML
	Button deck1Btn, deck2Btn, deck3Btn, deck4Btn, deck5Btn, deck6Btn, deck7Btn, deck8Btn, deck9Btn, deck10Btn;
	@FXML
	Label deck1, deck2, deck3, deck4, deck5, deck6, deck7, deck8, deck9, deck10, deckSelectedLbl;

	public void deckChooser(ActionEvent event) throws IOException, ParseException, FileNotFoundException {
		Object node = event.getSource();
		Button b = (Button) node;
		if (b.getText().equals("Deck 1")) {
			File deckPath = new File("src/model/decks/DefaultDeck.json");
			deckSelectedLbl.setText("*" + model.JSONReader.getDeckTitleFromFile(deckPath) + " Deck Selected");
			oldTitle = deck1.getText();
			deck = 1;
		} else if (b.getText().equals("Deck 2")) {
			File deckPath = new File("src/model/decks/CustomDeck2.json");
			deckSelectedLbl.setText("*" + model.JSONReader.getDeckTitleFromFile(deckPath) + " Deck Selected");
			oldTitle = deck2.getText();
			deck = 2;
		} else if (b.getText().equals("Deck 3")) {
			File deckPath = new File("src/model/decks/CustomDeck3.json");
			deckSelectedLbl.setText("*" + model.JSONReader.getDeckTitleFromFile(deckPath) + " Deck Selected");
			oldTitle = deck3.getText();
			deck = 3;
		} else if (b.getText().equals("Deck 4")) {
			File deckPath = new File("src/model/decks/CustomDeck4.json");
			deckSelectedLbl.setText("*" + model.JSONReader.getDeckTitleFromFile(deckPath) + " Deck Selected");
			oldTitle = deck4.getText();
			deck = 4;
		} else if (b.getText().equals("Deck 5")) {
			File deckPath = new File("src/model/decks/CustomDeck5.json");
			deckSelectedLbl.setText("*" + model.JSONReader.getDeckTitleFromFile(deckPath) + " Deck Selected");
			oldTitle = deck5.getText();
			deck = 5;
		} else if (b.getText().equals("Deck 6")) {
			File deckPath = new File("src/model/decks/CustomDeck6.json");
			deckSelectedLbl.setText("*" + model.JSONReader.getDeckTitleFromFile(deckPath) + " Deck Selected");
			oldTitle = deck6.getText();
			deck = 6;
		} else if (b.getText().equals("Deck 7")) {
			File deckPath = new File("src/model/decks/CustomDeck7.json");
			deckSelectedLbl.setText("*" + model.JSONReader.getDeckTitleFromFile(deckPath) + " Deck Selected");
			oldTitle = deck7.getText();
			deck = 7;
		} else if (b.getText().equals("Deck 8")) {
			File deckPath = new File("src/model/decks/CustomDeck8.json");
			deckSelectedLbl.setText("*" + model.JSONReader.getDeckTitleFromFile(deckPath) + " Deck Selected");
			oldTitle = deck8.getText();
			deck = 8;
		} else if (b.getText().equals("Deck 9")) {
			File deckPath = new File("src/model/decks/CustomDeck9.json");
			deckSelectedLbl.setText("*" + model.JSONReader.getDeckTitleFromFile(deckPath) + " Deck Selected");
			oldTitle = deck9.getText();
			deck = 9;
		} else if (b.getText().equals("Deck 10")) {
			File deckPath = new File("src/model/decks/CustomDeck10.json");
			deckSelectedLbl.setText("*" + model.JSONReader.getDeckTitleFromFile(deckPath) + " Deck Selected");
			oldTitle = deck10.getText();
			deck = 10;
		}
	}
	
	public void initialize() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					if (cover == 1) {
						homeCover.setTranslateY(-100);
					}
					File deckPath = new File("src/model/decks/DefaultDeck.json");
					deck1.setText(model.JSONReader.getDeckTitleFromFile(deckPath));
					File deckPath2 = new File("src/model/decks/CustomDeck2.json");
					deck2.setText(model.JSONReader.getDeckTitleFromFile(deckPath2));
					File deckPath3 = new File("src/model/decks/CustomDeck3.json");
					deck3.setText(model.JSONReader.getDeckTitleFromFile(deckPath3));
					File deckPath4 = new File("src/model/decks/CustomDeck4.json");
					deck4.setText(model.JSONReader.getDeckTitleFromFile(deckPath4));
					File deckPath5 = new File("src/model/decks/CustomDeck5.json");
					deck5.setText(model.JSONReader.getDeckTitleFromFile(deckPath5));
					File deckPath6 = new File("src/model/decks/CustomDeck6.json");
					deck6.setText(model.JSONReader.getDeckTitleFromFile(deckPath6));
					File deckPath7 = new File("src/model/decks/CustomDeck7.json");
					deck7.setText(model.JSONReader.getDeckTitleFromFile(deckPath7));
					File deckPath8 = new File("src/model/decks/CustomDeck8.json");
					deck8.setText(model.JSONReader.getDeckTitleFromFile(deckPath8));
					File deckPath9 = new File("src/model/decks/CustomDeck9.json");
					deck9.setText(model.JSONReader.getDeckTitleFromFile(deckPath9));
					File deckPath10 = new File("src/model/decks/CustomDeck10.json");
					deck10.setText(model.JSONReader.getDeckTitleFromFile(deckPath10));
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void homeBtnCover() {
		cover = 1;
	}
	
	// This Creates the new Deck
	public void changeDeckName(ActionEvent event) throws IOException, ParseException, FileNotFoundException {
		if (deck == 1) {
			File deckPath = new File("src/model/decks/DefaultDeck.json");
			titleEntry();
			System.out.println(oldTitle);
			System.out.println(model.JSONReader.getDeckTitleFromFile(deckPath));
			model.JSONWriter.publicUpdateDeckTitle(oldTitle, title);
			deck1.setText(model.JSONReader.getDeckTitleFromFile(deckPath));
		} else if (deck == 2) {
			File deckPath = new File("src/model/decks/CustomDeck2.json");
			titleEntry();
			System.out.println(oldTitle);
			System.out.println(model.JSONReader.getDeckTitleFromFile(deckPath));
			model.JSONWriter.publicUpdateDeckTitle(oldTitle, title);
			deck2.setText(model.JSONReader.getDeckTitleFromFile(deckPath));
		} else if (deck == 3) {
			File deckPath = new File("src/model/decks/CustomDeck3.json");
			titleEntry();
			System.out.println(oldTitle);
			System.out.println(model.JSONReader.getDeckTitleFromFile(deckPath));
			model.JSONWriter.publicUpdateDeckTitle(oldTitle, title);
			deck3.setText(model.JSONReader.getDeckTitleFromFile(deckPath));
		} else if (deck == 4) {
			File deckPath = new File("src/model/decks/CustomDeck4.json");
			titleEntry();
			System.out.println(oldTitle);
			System.out.println(model.JSONReader.getDeckTitleFromFile(deckPath));
			model.JSONWriter.publicUpdateDeckTitle(oldTitle, title);
			deck4.setText(model.JSONReader.getDeckTitleFromFile(deckPath));
		} else if (deck == 5) {
			File deckPath = new File("src/model/decks/CustomDeck5.json");
			titleEntry();
			System.out.println(oldTitle);
			System.out.println(model.JSONReader.getDeckTitleFromFile(deckPath));
			model.JSONWriter.publicUpdateDeckTitle(oldTitle, title);
			deck5.setText(model.JSONReader.getDeckTitleFromFile(deckPath));
		} else if (deck == 6) {
			File deckPath = new File("src/model/decks/CustomDeck6.json");
			titleEntry();
			System.out.println(oldTitle);
			System.out.println(model.JSONReader.getDeckTitleFromFile(deckPath));
			model.JSONWriter.publicUpdateDeckTitle(oldTitle, title);
			deck6.setText(model.JSONReader.getDeckTitleFromFile(deckPath));
		} else if (deck == 7) {
			File deckPath = new File("src/model/decks/CustomDeck7.json");
			titleEntry();
			System.out.println(oldTitle);
			System.out.println(model.JSONReader.getDeckTitleFromFile(deckPath));
			model.JSONWriter.publicUpdateDeckTitle(oldTitle, title);
			deck7.setText(model.JSONReader.getDeckTitleFromFile(deckPath));
		} else if (deck == 8) {
			File deckPath = new File("src/model/decks/CustomDeck8.json");
			titleEntry();
			System.out.println(oldTitle);
			System.out.println(model.JSONReader.getDeckTitleFromFile(deckPath));
			model.JSONWriter.publicUpdateDeckTitle(oldTitle, title);
			deck8.setText(model.JSONReader.getDeckTitleFromFile(deckPath));
		}else if (deck == 9) {
			File deckPath = new File("src/model/decks/CustomDeck9.json");
			titleEntry();
			System.out.println(oldTitle);
			System.out.println(model.JSONReader.getDeckTitleFromFile(deckPath));
			model.JSONWriter.publicUpdateDeckTitle(oldTitle, title);
			deck9.setText(model.JSONReader.getDeckTitleFromFile(deckPath));
		}else if (deck == 10) {
			File deckPath = new File("src/model/decks/CustomDeck10.json");
			titleEntry();
			System.out.println(oldTitle);
			System.out.println(model.JSONReader.getDeckTitleFromFile(deckPath));
			model.JSONWriter.publicUpdateDeckTitle(oldTitle, title);
			deck10.setText(model.JSONReader.getDeckTitleFromFile(deckPath));
		}
		// clear the TextEntry when finished
		titleEntry.clear();
	}

}
