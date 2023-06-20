package support.dto;

public enum Role {
	ADMIN,
	STANDART;
	

	
	public static Role setRole(int value) {
		if(value == 1) {
			return Role.ADMIN;
		}
		
		return Role.STANDART;
	}
}
