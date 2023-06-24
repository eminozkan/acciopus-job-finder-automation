package service;

import javafx.collections.ObservableList;
import support.dto.Notification;
import support.result.CreationResult;

public interface NotificationService {
	ObservableList<Notification> getNotifications(int userId);

	void deleteNotification(int notificationId); 
	
	CreationResult sendNotification(Notification n);

	Integer getNotificationCount(int userId);
}
