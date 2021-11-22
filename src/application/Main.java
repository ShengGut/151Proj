package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			
			primaryStage.setTitle("Ruminate Self-Study©");
			FlowPane root = (FlowPane)FXMLLoader.load(getClass().getResource("../view/DecksPage.fxml"));
			//Currently using FlowPane as the root
			
			Scene scene = new Scene(root,800,600);
			
			//This part locks the window because the elements don't flow as it resizes yet
			primaryStage.setMaxHeight(620);
			primaryStage.setMinHeight(620);
			primaryStage.setMaxWidth(820);
			primaryStage.setMinWidth(820);
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
