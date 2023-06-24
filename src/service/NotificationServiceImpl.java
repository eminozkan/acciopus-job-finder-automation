package service;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import config.ApplicationConfig;
import config.DatabaseConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import support.dto.Notification;
import support.result.CreationResult;

public class NotificationServiceImpl implements NotificationService{
	
	private Logger logger = ApplicationConfig.getLogger();
	
	@Override
	public ObservableList<Notification> getNotifications(int userId) {
		ObservableList<Notification> notificationList = FXCollections.observableArrayList();
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Select * from notifications where notificationUserId = ?";
        
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			int localId = 1;
				while (resultSet.next()) {
					Notification notification = new Notification()
							.setNotificationId(resultSet.getInt("notificationId"))
							.setNotificationMessage(resultSet.getString("notificationMessage"))
							.setNotificationDate(resultSet.getTimestamp("notificationDate"))
							.setNotificationUserId(resultSet.getInt("notificationUserId"))
							.setNotificationLocalId(localId);
					localId++;
					notificationList.add(notification);
				}
				
				preparedStatement.close();
				connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return notificationList;
	}

	@Override
	public void deleteNotification(int notificationId) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Delete from notifications where notificationId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, notificationId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public CreationResult sendNotification(Notification n) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "insert into notifications (notificationMessage,notificationDate,notificationUserId) values(?,?,?)";
        
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, n.getNotificationMessage());
			Date now = new Date();
	         Timestamp time = new Timestamp(now.getTime()); 
			preparedStatement.setTimestamp(2, time);
			preparedStatement.setInt(3, n.getNotificationUserId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			logger.information("Notification has been sent to User : \'" + n.getNotificationUserId() + "\'.");
			return CreationResult.success();
		}catch(Exception e) {
			e.printStackTrace();
			logger.information("Notification couldn't sent for User : \'" + n.getNotificationUserId() + "\'. Reason : " + e.getMessage());
			return CreationResult.failed(e.getMessage());
		}
	}

	@Override
	public Integer getNotificationCount(int userId) {
		Integer counter = 0;
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Select COUNT(notificationId) from notifications where notificationUserId = ?";
        
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			int localId = 1;
				while (resultSet.next()) {
					counter = resultSet.getInt(1);
				}
				
				preparedStatement.close();
				connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return counter;
	}

}
