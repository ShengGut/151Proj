package controller;

import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DecksPageController extends SceneController {

	@FXML
	private TextField titleEntry;
	@FXML
	StackPane stckPane;
	private Stage stage;
	private Scene scene;
	private String title;
	int x = 260;
	HomePageController hp = new HomePageController();
	MakeCardController mc = new MakeCardController();
	StudyPageController sp = new StudyPageController();

	public void titleEntry() {
		title = titleEntry.getText();
	}

	// This is the controller to switch to DefaultDeck
	public void switchToStudyPage1(MouseEvent event) throws IOException, ParseException {
		hp.changer1();
		mc.changer1();
		sp.changer1();
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
		FlowPane root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// This Creates the new Deck
	public void createDeck(ActionEvent event) throws IOException, ParseException {
		titleEntry();
		File newDeck = new File("src/model/decks/" + title + ".json");
		newDeck.createNewFile();
		// Create the Rectangle Boxes + the Label which takes the name of the title
		Rectangle rect = new Rectangle();
		Label lbl = new Label(title);
		lbl.setFont(Font.font(null, FontWeight.BOLD, 16));
		StackPane.setMargin(rect, new Insets(0, x, 200, 0));
		rect.setFill(Color.web("#dadada"));
		rect.setStrokeWidth(1);
		rect.setStroke(Color.web("000000"));
		StackPane.setMargin(lbl, new Insets(0, x, 200, 0));
		x = x - 370;
		rect.setWidth(150);
		rect.setHeight(200);
		stckPane.getChildren().addAll(rect, lbl);
		// Set the Event to change scenes when the box and mouse is clicked
		rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				FlowPane root = null;
				try {
					root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		});
		lbl.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				FlowPane root = null;
				try {
					root = (FlowPane) FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		});
		// clear the TextEntry when finished
		titleEntry.clear();
	}

}
