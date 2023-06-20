package support.result;

public class AuthorizationResult {
	private boolean isSuccess;
	private String message;
	
	private AuthorizationResult() {}
	
	public static AuthorizationResult success(String message) {
		AuthorizationResult result = new AuthorizationResult();
		result.setSuccess(true);
		result.setMessage(message);
		return result;
	}
	
	public static AuthorizationResult success() {
		return AuthorizationResult.success("");
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
	
}
