package service;

import javafx.collections.ObservableList;
import support.dto.Job;
import support.dto.Session;
import support.dto.User;

public interface AdminService {
	
	ObservableList<User> getUsers();

	ObservableList<Job> getJobs();

	ObservableList<Session> getSessions();

	void deleteJob(int jobId);

	void deleteUser(int userId);

	void setUserAsAdmin(int userId);
}
