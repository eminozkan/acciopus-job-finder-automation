package support.dto;

public record ChangePasswordRequest(
		String oldPassword,
		String newPassword
		) {

}
