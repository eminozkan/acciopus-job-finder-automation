package support.session;

import java.util.UUID;

import support.dto.Role;
import support.dto.User;

public class UserSession {
	private static User user;
	private static int sessionId;
	
	
	public static void setUser(User user) {
		UserSession.user = user;
	}
	
	public static User getUser() {
		return UserSession.user;
	}
	
	public static int getUserId() {
		return UserSession.user.getUserId();
	}
	
	public static String getUserEmail() {
		return UserSession.user.getUserEmail();
	}
	
	public static String getUserName() {
		return UserSession.user.getName();
	}
	
	public static Role getUserRole() {
		return UserSession.user.getRole();
	}
	
	public static void generateSessionId() {
		UserSession.sessionId = UUID.randomUUID().toString().getBytes().hashCode();
	}

	public static int getSessionId() {
		return UserSession.sessionId;
	}

	public static void clear() {
		UserSession.setUser(new User());
		UserSession.sessionId = 0;
	}
}
