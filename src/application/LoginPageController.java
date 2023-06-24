package application;

import java.net.URL;
import javafx.util.Duration;
import service.UserService;
import support.dto.Role;
import support.dto.User;
import support.result.AuthorizationResult;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import support.result.CreationResult;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.ResourceBundle;

import config.ApplicationConfig;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;

public class LoginPageController {

	private final UserService userService = ApplicationConfig.getUserService();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Rectangle background;

    @FXML
    private Rectangle chooser;

    @FXML
    private Rectangle chosen;

    @FXML
    private Label emailIcon;

    @FXML
    private Label emailIcon1;

    @FXML
    private Label emailIcon11;

    @FXML
    private TextField emailTextField;

    @FXML
    private Rectangle fixedRectangle;

    @FXML
    private Rectangle login;

    @FXML
    private Rectangle loginButton;

    @FXML
    private Label loginButtonLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Pane loginPane;

    @FXML
    private Label passwordIcon;

    @FXML
    private Label passwordIcon1;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Rectangle signupButton;

    @FXML
    private Label signupButtonLabel;

    @FXML
    private TextField signupEmail;

    @FXML
    private Label signupLabel;

    @FXML
    private TextField signupName;

    @FXML
    private Pane signupPane;

    @FXML
    private PasswordField signupPassword;
    
    @FXML
    private Label tempImage;
    


    @FXML
    void initialize() {        

    }
    
    @FXML
    private void loginLabelClicked() {
    	if(chosen.getLayoutX() != 251) {
        	setTransition(chosen,421,60,342,60,400);
    		loginLabel.setTextFill(Color.WHITE);
    		signupLabel.setTextFill(Color.BLACK);
    		chosen.relocate(251, 60);
    		loginPane.setVisible(true);
    		signupPane.setVisible(false);
    		loginPane.relocate(206, 127);
    		loginPane.setVisible(true);
    		clearFields();
    	}
    }
    
    @FXML
    private void signupLabelClick() {
    	if(chosen.getLayoutX() != 342) {
    		setTransition(chosen,170,60,252,60,400);
    		loginPane.setVisible(false);
    		signupPane.setVisible(true);
    		signupPane.relocate(206, 127);
    		loginLabel.setTextFill(Color.BLACK);
    		signupLabel.setTextFill(Color.WHITE);
			chosen.relocate(342, 60);
			loginPane.setVisible(false);
			clearFields();
    	}
    }
    
    private void setTransition(Node n,double locationFromX,double locationFromY,double locationToX,double locationToY,double millis) {
    	TranslateTransition transition = new TranslateTransition();
		transition.setNode(chosen);
		Duration d = Duration.millis(millis);
		transition.setDuration(d);
		transition.setFromX(locationFromX - n.getLayoutX());
		transition.setFromY(locationFromY - n.getLayoutY());
		transition.setToX(locationToX - n.getLayoutX());
		transition.setToY(locationToY - n.getLayoutY());
		transition.play();
    }
    
    
    @FXML
    private void loginButtonClick() {
    	final User user = new User()
    			.setUserEmail(emailTextField.getText())
    			.setPasswordHash(passwordTextField.getText());
    	
    	AuthorizationResult result = userService.login(user);
    	if(result.isSuccess()){
        		Alert alert1 = new Alert(AlertType.INFORMATION);
        		alert1.setTitle("Information");
        		alert1.setHeaderText("Success");
        		alert1.setContentText("Logged into account!");
        		alert1.showAndWait();
        		if(result.getUserRole() == Role.STANDART) {
        			openMainPage();
        		}else {
        			 Alert alert = new Alert(AlertType.CONFIRMATION);
        			 alert.setTitle("Choose one");
        			 alert.setHeaderText("Choose panel do you want to launch");
        			 alert.setContentText("Choose panel do you want to launch");
        			 ButtonType adminPage = new ButtonType("Go to Admin Panel");
        			 ButtonType userPage = new ButtonType("Go to User Panel");
        			 alert.getButtonTypes().clear();
        			 alert.getButtonTypes().add(adminPage);
        			 alert.getButtonTypes().add(userPage);
        			 Optional<ButtonType> alertResult = alert.showAndWait();
        			 if(alertResult.isPresent() && alertResult.get() == adminPage) {
        				 openAdminPage();
        			 }else if(alertResult.isPresent() && alertResult.get() == userPage) {
        				 openMainPage();
        			 }
        		}
        		
    	}
    	else {
    		Alert alert1 = new Alert(AlertType.ERROR);
    		alert1.setTitle("Error");
    		alert1.setHeaderText("Failed");
    		alert1.setContentText("Failed to login. Reason : " + result.getMessage());
    		alert1.showAndWait();
    	}
    }
    
    private void openAdminPage() {
    	 try {
 			Image icon = new Image(getClass().getResourceAsStream("icon.png"));
         	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
         	Stage activeStage =(Stage) loginButton.getScene().getWindow();
         	activeStage.close();
             Stage stage = new Stage();
             stage.setTitle("Admin Page");
             stage.setScene(new Scene(root, 1024, 600));
             stage.setResizable(false);
             stage.getIcons().add(icon);
             stage.setMaximized(false);
             stage.show();

         }
         catch (Exception e) {
             e.printStackTrace();
         }
	}

	@FXML
    private void signupButtonClick() {
    	final User user = User.createStandartUser(signupName.getText(), signupPassword.getText(), signupEmail.getText());
    	CreationResult result = userService.register(user);
    	if(result.isSuccess()) {
    		Alert alert1 = new Alert(AlertType.INFORMATION);
    		alert1.setTitle("Information");
    		alert1.setHeaderText("Success");
    		alert1.setContentText("User has registered successfully!");
    		alert1.showAndWait();
    	}else {
    		Alert alert1 = new Alert(AlertType.ERROR);
    		alert1.setTitle("ERROR");
    		alert1.setHeaderText("Failed");
    		alert1.setContentText("User has failed to register! Reason : " + result.getMessage());
    		alert1.showAndWait();
    	}
    }
    
    
    private void clearFields() {
    	signupEmail.clear();
    	signupPassword.clear();
    	signupName.clear();
    	emailTextField.clear();
    	passwordTextField.clear();
    }
    
	private void openMainPage() {
        try {
			Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        	Stage activeStage =(Stage) loginButton.getScene().getWindow();
        	activeStage.close();
            Stage stage = new Stage();
            stage.setTitle("Home Page");
            stage.setScene(new Scene(root, 1024, 600));
            stage.setResizable(false);
            stage.getIcons().add(icon);
            stage.setMaximized(false);
            stage.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
    
}
