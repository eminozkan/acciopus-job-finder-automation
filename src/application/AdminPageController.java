package application;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;

import config.ApplicationConfig;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ButtonType;
import javafx.scene.shape.Rectangle;
import service.AdminService;
import support.dto.Job;
import support.dto.Role;
import support.dto.Session;
import support.dto.User;

public class AdminPageController {

	private AdminService adminService = ApplicationConfig.getAdminService();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Rectangle background;

    @FXML
    private TableColumn<Job, Integer> jobId;
    
    @FXML
    private TableColumn<Job, String> jobCompanyName;

    @FXML
    private TableColumn<Job, Timestamp> jobDate;

    @FXML
    private TableView<Job> jobListTableView;

    @FXML
    private TableColumn<Job, Integer> jobSalary;

    @FXML
    private TableColumn<Job, String> jobText;

    @FXML
    private TableColumn<Job, String> jobUserName;



    @FXML
    private Tab jobsTab;

    @FXML
    private Button removeJobButton;

    @FXML
    private Button removeUserButton;

    @FXML
    private TableColumn<Session, Integer> sessionId;

    @FXML
    private Tab sessionListTab;

    @FXML
    private TableColumn<Session, Timestamp> sessionTime;

    @FXML
    private TableColumn<Session, String> sessionUserName;

    @FXML
    private Button setUserAsAdmin;

    @FXML
    private TableColumn<User, String> userAddress;

    @FXML
    private TableColumn<User, Boolean> userCV;

    @FXML
    private TableColumn<User, String> userEmail;

    @FXML
    private TableColumn<User, Integer> userId;

    @FXML
    private Label userListLabel;

    @FXML
    private TableColumn<User, String> userName;

    @FXML
    private TableColumn<User, String> userPhone;

    @FXML
    private TableColumn<User, Role> userRole;

    @FXML
    private TableView<Session> userSessionList;

    @FXML
    private TableView<User> userTableView;

    @FXML
    private Tab usersTab;

    @FXML
    void removeJobButton(MouseEvent event) {
    	int index = jobListTableView.getSelectionModel().getSelectedIndex();
    	if(index >= 0) {
    	boolean result = showConfirmationAlert();
			if(result) {
	    		int jobId = jobListTableView.getSelectionModel().getSelectedItem().getJobId();
	        	adminService.deleteJob(jobId);
	        	fillJobTableView();
			}
    	}else {
			Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setTitle("ERROR");
			alert1.setHeaderText("Failed");
			alert1.setContentText("Select item in list");
			alert1.showAndWait();
    	}
    }

	private boolean showConfirmationAlert() {
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Confirmation");
		alert1.setHeaderText("Confirmation");
		alert1.setContentText("Do you want to delete this job?");
		alert1.getButtonTypes().clear();
		ButtonType yesButton = new ButtonType("Yes");
		ButtonType noButton = new ButtonType("No");
		alert1.getButtonTypes().add(yesButton);
		alert1.getButtonTypes().add(noButton);
		
		Optional<ButtonType> result = alert1.showAndWait();
		return result.isEmpty() ? false : (result.get() == yesButton ? true : false);
	}

    @FXML
    void removeUserButton(MouseEvent event) {
    	int index = userTableView.getSelectionModel().getSelectedIndex();
    	if(index >= 0) {
    		boolean result = showConfirmationAlert();
    		if(result) {
    			int userId = userTableView.getSelectionModel().getSelectedItem().getUserId();
        		adminService.deleteUser(userId);
        	 	fillUserTableView();
    		}
    	
    	}else {
			Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setTitle("ERROR");
			alert1.setHeaderText("Failed");
			alert1.setContentText("Select item in list");
			alert1.showAndWait();
    	}
    }

    @FXML
    void setUserAsAdmin(MouseEvent event) {
    	int index = userTableView.getSelectionModel().getSelectedIndex();
    	if(index >= 0) {
    		boolean result = showConfirmationAlert();
    		if(result) {
        		int userId = userTableView.getSelectionModel().getSelectedItem().getUserId();
        		adminService.setUserAsAdmin(userId);
        		fillUserTableView();
    		}
    	}else {
			Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setTitle("ERROR");
			alert1.setHeaderText("Failed");
			alert1.setContentText("Select item in list");
			alert1.showAndWait();
    	}
    }

    @FXML
    void initialize() {
    	fillUserTableView();
    	fillJobTableView();
    	fillSessionTableView();
    }
    
    
    private void fillUserTableView() {
    	ObservableList<User> userList = adminService.getUsers();
    	userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
    	userName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    	userPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    	userEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
    	userAddress.setCellValueFactory(new PropertyValueFactory<>("adress"));
    	userRole.setCellValueFactory(new PropertyValueFactory<>("roleAsString"));
    	userCV.setCellValueFactory(new PropertyValueFactory<>("hasUserCVAsString"));
    	
    	userTableView.setItems(userList);
    }
    
    private void fillJobTableView() {
    	ObservableList<Job> jobList = adminService.getJobs();
    	jobId.setCellValueFactory(new PropertyValueFactory<>("jobId"));
    	jobCompanyName.setCellValueFactory(new PropertyValueFactory<>("jobHeaderWithCompanyName"));
    	jobText.setCellValueFactory(new PropertyValueFactory<>("jobText"));
    	jobSalary.setCellValueFactory(new PropertyValueFactory<>("jobSalary"));
    	jobDate.setCellValueFactory(new PropertyValueFactory<>("jobPostedDate"));
    	jobUserName.setCellValueFactory(new PropertyValueFactory<>("jobUserName"));
    	
    	jobListTableView.setItems(jobList);
    	
    }
    
    private void fillSessionTableView() {
    	ObservableList<Session> sessionList = adminService.getSessions();
    	sessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
    	sessionUserName.setCellValueFactory(new PropertyValueFactory<>("sessionUserName"));
    	sessionTime.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
    	userSessionList.setItems(sessionList);
    }

}
