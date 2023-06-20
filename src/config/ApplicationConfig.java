package config;

import service.Logger;
import service.UserService;
import service.UserServiceImpl;

public class ApplicationConfig {

	public static UserService getUserService() {
		return new UserServiceImpl();
	}
	
	public static Logger getLogger() {
		return new Logger();
	}
}
