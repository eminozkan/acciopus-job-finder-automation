package service;

import javafx.collections.ObservableList;
import support.dto.Job;
import support.result.CreationResult;

public interface JobService {
	ObservableList<Job> getJobList();

	CreationResult postNewJob(Job j);

	CreationResult applyForJob(Job j, int userId);
}
