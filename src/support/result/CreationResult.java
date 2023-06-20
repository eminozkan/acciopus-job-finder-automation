package support.result;

public class CreationResult {
	private boolean isSuccess;
	private String message;
	
	private CreationResult() {}
	
	public static CreationResult success(String message) {
		CreationResult result = new CreationResult();
		result.setSuccess(true);
		result.setMessage(message);
		return result;
	}
	
	public static CreationResult success() {
		return CreationResult.success("");
	}
	
	public static CreationResult failed(String message) {
		CreationResult result = new CreationResult();
		result.setSuccess(false);
		result.setMessage(message);
		return result;
	}
	
	public static CreationResult failed() {
		return CreationResult.failed("");
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
