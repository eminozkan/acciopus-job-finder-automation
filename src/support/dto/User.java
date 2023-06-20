package support.dto;

public class User {
	private int userId;
	private String name;
	private String passwordHash;
	private String userEmail;
	private Role role;
	
	
	//No-Arg Constructor
	public User() {}
	
	//Copy Constructor
	public User(User user) {
		this.userId = user.userId;
		this.name = user.name;
		this.passwordHash = user.passwordHash;
		this.userEmail = user.userEmail;
		this.role = user.role;
	}
	
	
	//All-Args Constructor
	public User(int id,String username,String passwordHash,String userEmail,Role role) {
		this.userId =id;
		this.name = username;
		this.passwordHash = passwordHash;
		this.userEmail = userEmail;
		this.role = role;
	}
	
	
	public static User createStandartUser(String name,String passwordHash,String userEmail) {
		return new User()
				.setName(name)
				.setUserEmail(userEmail)
				.setPasswordHash(passwordHash)
				.setRole(Role.STANDART);
	}
	
	
	public static User createAdminUser(String name,String passwordHash,String userEmail) {
		return new User()
				.setName(name)
				.setUserEmail(userEmail)
				.setPasswordHash(passwordHash)
				.setRole(Role.ADMIN);
	}
	
	
	//Getter Methods
	public int getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public Role getRole() {
		return role;
	}
	
	//Setter Methods
	public User setUserId(int id) {
		this.userId = id;
		return this;
	}
	
	public User setName(String username) {
		this.name = username;
		return this;
	}
	
	public User setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}
	
	public User setUserEmail(String userEmail) {
		this.userEmail = userEmail;
		return this;
	}
	
	public User setRole(Role r) {
		this.role = r;
		return this;
	}
	
}
