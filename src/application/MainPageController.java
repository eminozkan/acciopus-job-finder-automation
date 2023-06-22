package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.util.Duration;

public class MainPageController {

    	@FXML
    	private ResourceBundle resources;

    	@FXML
    	private URL location;

    	@FXML
	    private Label addressLabel;

	    @FXML
	    private TextArea addressText;

	    @FXML
	    private AnchorPane anchorpane;

	    @FXML
	    private Button applyButton;

	    @FXML
	    private Rectangle background;

	    @FXML
	    private Label cPasswordLabel;

	    @FXML
	    private Button changePassButton;

	    @FXML
	    private PasswordField currentPasswordText;

	    @FXML
	    private Label cvLabel;

	    @FXML
	    private TextField headerTextField;

	    @FXML
	    private DatePicker jobDate;

	    @FXML
	    private Pane jobListPane;

	    @FXML
	    private TableView<?> jobListTableView;

	    @FXML
	    private Spinner<Integer> jobSalaryField;

	    @FXML
	    private TextArea jobTextArea;

	    @FXML
	    private Label jobs;

	    @FXML
	    private Label jobs1;

	    @FXML
	    private Label logout;

	    @FXML
	    private Pane menu;

	    @FXML
	    private Label menuLabel;

	    @FXML
	    private Label myApplications;

	    @FXML
	    private Pane myApplicationsPane;

	    @FXML
	    private TableView<?> myApplicationsTableView;

	    @FXML
	    private TableView<?> myJobListTableView;

	    @FXML
	    private Spinner<Integer> myJobSalaryField;
	    
	    @FXML
	    private Button seeApplicationsButton;

	    @FXML
	    private TextField myJobsHeaderText;

	    @FXML
	    private Pane myJobsPane;

	    @FXML
	    private TextArea myJobsTextArea;

	    @FXML
	    private Label nPasswordLabel;

	    @FXML
	    private Label nPasswordLabel2;

	    @FXML
	    private Label nameLabel;

	    @FXML
	    private TextField nameText;

	    @FXML
	    private Rectangle navbar;

	    @FXML
	    private PasswordField newPasswordText;

	    @FXML
	    private PasswordField newPasswordText2;

	    @FXML
	    private Button newPostButton;

	    @FXML
	    private Label notificationCounter;

	    @FXML
	    private Pane notificationPane;

	    @FXML
	    private TableView<?> notificationTableView;

	    @FXML
	    private Label notifications;

	    @FXML
	    private Label phoneLabel;

	    @FXML
	    private TextField phoneText;

	    @FXML
	    private Label profile;

	    @FXML
	    private Pane profilePane;

	    @FXML
	    private Button removeApplicationButton;

	    @FXML
	    private Button removePostButton;

	    @FXML
	    private TextField searchField;

	    @FXML
	    private Label surnameLabel;

	    @FXML
	    private TextField surnameText;

	    @FXML
	    private Button updateJobButton;

	    @FXML
	    private Button updateProfileButton;

	    @FXML
	    private Button uploadCvButton;


    @FXML
    void initialize() {
    	profilePane.setVisible(true);
    	SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000);
        jobSalaryField.setValueFactory(valueFactory);
        myJobSalaryField.setValueFactory(valueFactory);
        
    }
    
    @FXML
    void menuClick(MouseEvent event) {
    	if(menu.isVisible()) {
    		menu.setVisible(false);
    		setTransition(menuLabel,93,14,6,6,400);
        	
    	}else {
    		setTransition(menu,-1024,-65,0,0,400);
    		setTransition(menuLabel,6,6,93,14,450);
    		menu.setVisible(true);
    	}
    	
    }

    
    private void setTransition(Node n,double locationFromX,double locationFromY,double locationToX,double locationToY,double millis) {
    	TranslateTransition transition = new TranslateTransition();
		transition.setNode(n);
		Duration d = Duration.millis(millis);
		transition.setDuration(d);
		transition.setFromX(locationFromX - n.getLayoutX());
		transition.setFromY(locationFromY - n.getLayoutY());
		transition.setToX(locationToX - n.getLayoutX());
		transition.setToY(locationToY - n.getLayoutY());
		transition.play();
    }
    
    @FXML
    private void openProfile() {
    	setPanesVisibleFalse();
    	profilePane.setVisible(true);
    }
    
    @FXML
    private void openJobList() {
    	setPanesVisibleFalse();
    	jobListPane.setVisible(true);
    }
    
    
    @FXML
    void openMyJobList() {
    	setPanesVisibleFalse();
    	myJobsPane.setVisible(true);
    	
    }
    
    @FXML
    void openNotificationPane() {
    	setPanesVisibleFalse();
    	notificationPane.setVisible(true);
    }
    
    
    @FXML
    void openApplicationsPane() {
    	setPanesVisibleFalse();
    	myApplicationsPane.setVisible(true);
    }

    
    private void setPanesVisibleFalse() {
    	profilePane.setVisible(false);
    	myJobsPane.setVisible(false);
    	jobListPane.setVisible(false);
    	notificationPane.setVisible(false);
    	myApplicationsPane.setVisible(false);
    }
    
    
    
    
}
