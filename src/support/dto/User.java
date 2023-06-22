package support.dto;

public class User {
	private int userId;
	private String name;
	private String surname;
	private String phoneNumber;
	private String adress;
	private String passwordHash;
	private String userEmail;
	private boolean hasUserCV;
	private Role role;
	
	
	//No-Arg Constructor
	public User() {}
	
	//Copy Constructor
	public User(User user) {
		this.userId = user.userId;
		this.name = user.name;
		this.surname = user.surname;
		this.phoneNumber = user.phoneNumber;
		this.adress = user.adress;
		this.passwordHash = user.passwordHash;
		this.userEmail = user.userEmail;
		this.role = user.role;
		this.hasUserCV = user.hasUserCV;
	}
	
	
	//All-Args Constructor
	public User(int id,String username,String surname,String passwordHash,String userEmail,String phoneNumber,String address,boolean hasCV,Role role) {
		this.userId =id;
		this.name = username;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.adress = address;
		this.passwordHash = passwordHash;
		this.userEmail = userEmail;
		this.role = role;
		this.hasUserCV = hasCV;

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
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getSurname() {
		return surname;
	}
	
	
	public String getAdress() {
		return adress;
	}
	
	public boolean hasUserCV() {
		return hasUserCV;
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
	
	public User setSurname(String surname) {
		this.surname =  surname;
		return this;
	}
	
	public User setAdress(String adress) {
		this.adress = adress;
		return this;
	}
	
	public User setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	
	public User setHasUserCV(boolean hasCV) {
		this.hasUserCV = hasCV;
		return this;
	}
}
