package support.result;

public class UpdateResult {
	private boolean isSuccess;
	private String message;
	
	private UpdateResult() {}
	
	public static UpdateResult success(String message) {
		UpdateResult result = new UpdateResult();
		result.setSuccess(true);
		result.setMessage(message);
		return result;
	}
	
	public static UpdateResult success() {
		return UpdateResult.success("");
	}
	
	public static UpdateResult failed(String message) {
		UpdateResult result = new UpdateResult();
		result.setSuccess(false);
		result.setMessage(message);
		return result;
	}
	
	public static UpdateResult failed() {
		return UpdateResult.failed("");
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
