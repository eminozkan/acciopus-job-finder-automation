package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class JavaFxApplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Image icon = new Image(getClass().getResourceAsStream("icon.png"));
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
			Scene scene = new Scene(root,700,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(false);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Home Page");
			primaryStage.getIcons().add(icon);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
