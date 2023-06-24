package support.dto;

import java.sql.Timestamp;

public class Notification {
	private int notificationId;
	private int notificationLocalId;
	private String notificationMessage;
	private Timestamp notificationDate;
	private int notificationUserId;
	
	
	public Notification() {
		
	}


	public Notification(int notificationId, int notificationLocalId, String notificationMessage,
			Timestamp notificationDate, int notificationUserId) {
		this.notificationId = notificationId;
		this.notificationLocalId = notificationLocalId;
		this.notificationMessage = notificationMessage;
		this.notificationDate = notificationDate;
		this.notificationUserId = notificationUserId;
	}
	
	
	public int getNotificationId() {
		return notificationId;
	}
	public Notification setNotificationId(int notificationId) {
		this.notificationId = notificationId;
		return this;
	}
	public int getNotificationLocalId() {
		return notificationLocalId;
	}
	public Notification setNotificationLocalId(int notificationLocalId) {
		this.notificationLocalId = notificationLocalId;
		return this;
	}
	public String getNotificationMessage() {
		return notificationMessage;
	}
	public Notification setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
		return this;
	}
	public Timestamp getNotificationDate() {
		return notificationDate;
	}
	public Notification setNotificationDate(Timestamp notificationDate) {
		this.notificationDate = notificationDate;
		return this;
	}
	public int getNotificationUserId() {
		return notificationUserId;
	}
	public Notification setNotificationUserId(int notificationUserId) {
		this.notificationUserId = notificationUserId;
		return this;
	}
	
	
}
