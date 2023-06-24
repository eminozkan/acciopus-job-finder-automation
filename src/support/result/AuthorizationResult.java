package support.result;

import support.dto.Role;

public class AuthorizationResult {
	private boolean isSuccess;
	private String message;
	private Role userRole;
	
	private AuthorizationResult() {}
	
	public static AuthorizationResult success(String message,Role userRole) {
		AuthorizationResult result = new AuthorizationResult();
		result.setSuccess(true);
		result.setMessage(message);
		result.setUserRole(userRole);
		return result;
	}
	
	public static AuthorizationResult success(Role userRole) {
		return AuthorizationResult.success("",userRole);
	}
	
	public static AuthorizationResult failed(String message) {
		AuthorizationResult result = new AuthorizationResult();
		result.setSuccess(false);
		result.setMessage(message);
		return result;
	}
	
	public static AuthorizationResult failed() {
		return AuthorizationResult.failed("");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}
	
}
