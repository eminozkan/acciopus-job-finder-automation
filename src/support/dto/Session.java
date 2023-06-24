package support.dto;

import java.sql.Timestamp;

public class Session {
	private int sessionId;
	private String sessionUserName;
	private Timestamp timestamp;
	
	
	public int getSessionId() {
		return sessionId;
	}
	public Session setSessionId(int sessionId) {
		this.sessionId = sessionId;
		return this;
	}
	public String getSessionUserName() {
		return sessionUserName;
	}
	public Session setSessionUserName(String sessionUserName) {
		this.sessionUserName = sessionUserName;
		return this;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public Session setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	
	
}
