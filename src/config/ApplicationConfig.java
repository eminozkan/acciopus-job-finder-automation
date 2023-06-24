package config;

import service.JobService;
import service.JobServiceImpl;
import service.Logger;
import service.NotificationService;
import service.NotificationServiceImpl;
import service.UserService;
import service.UserServiceImpl;

public class ApplicationConfig {

	public static UserService getUserService() {
		return new UserServiceImpl();
	}
	
	public static Logger getLogger() {
		return new Logger();
	}
	
	public static JobService getJobService(){
		return new JobServiceImpl();
	}

	public static NotificationService getNotificationService() {
		return new NotificationServiceImpl();
	}
}
