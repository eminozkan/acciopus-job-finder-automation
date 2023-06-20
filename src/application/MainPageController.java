package application;

import java.net.URL;
import javafx.util.Duration;
import service.UserService;
import support.dto.User;
import support.result.CreationResult;

import java.util.ResourceBundle;

import config.ApplicationConfig;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.TranslateTransition;

public class MainPageController {

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
    	System.out.println("Login button clicked!");
    }
    
    @FXML
    private void signupButtonClick() {
    	final User user = User.createStandartUser(signupName.getText(), signupPassword.getText(), signupEmail.getText());
    	CreationResult result = userService.register(user);
    	System.out.println(result.getMessage());
    }
    
    
    private void clearFields() {
    	signupEmail.clear();
    	signupPassword.clear();
    	signupName.clear();
    	emailTextField.clear();
    	passwordTextField.clear();
    }
    
}
