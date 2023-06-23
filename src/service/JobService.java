package service;

import javafx.collections.ObservableList;
import support.dto.Job;
import support.dto.User;
import support.result.CreationResult;
import support.result.UpdateResult;

public interface JobService {
	ObservableList<Job> getJobList();

	CreationResult postNewJob(Job j);

	CreationResult applyForJob(Job j, int userId);

	ObservableList<Job> getJobListByUserId(int userId);

	UpdateResult updateJobPost(Job j);

	void deleteJobPost(Job j);

	ObservableList<User> getApplications(int jobId);
}
