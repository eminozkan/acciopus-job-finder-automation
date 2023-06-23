package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import config.ApplicationConfig;
import config.DatabaseConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import support.dto.Job;
import support.dto.User;
import support.result.CreationResult;
import support.result.UpdateResult;
import support.session.UserSession;


public class JobServiceImpl implements JobService{
	
	private Logger logger = ApplicationConfig.getLogger();
	
	@Override
	public ObservableList<Job> getJobList() {
		ObservableList<Job> jobList = FXCollections.observableArrayList();
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Select * from jobs";
        
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Job job = new Job().setJobId(resultSet.getInt("jobId"))
					.setJobHeader(resultSet.getString("jobHeader"))
					.setCompanyName(resultSet.getString("companyName"))
					.setJobText(resultSet.getString("jobText"))
					.setJobSalary(resultSet.getInt("jobSalary"))
					.setJobPostedDate(resultSet.getTimestamp("jobPostedDate"))
					.setJobUserId(resultSet.getInt("jobUserId"));
					jobList.add(job);
				}
				
				preparedStatement.close();
				connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return jobList;
	}
	
	@Override
	public CreationResult postNewJob(Job j) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
            String insertQuery = "INSERT INTO jobs (companyName, jobHeader,jobText,jobSalary,jobPostedDate,jobUserId) VALUES (?,?,?,?,?,?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1,j.getCompanyName());
            preparedStatement.setString(2, j.getJobHeader());
            preparedStatement.setString(3, j.getJobText());
            preparedStatement.setInt(4, j.getJobSalary());
            preparedStatement.setTimestamp(5, j.getJobPostedDate());
            preparedStatement.setInt(6, j.getJobUserId());
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            logger.information("User : \'" + UserSession.getUserEmail() + "\' has posted new job.");
            return CreationResult.success("Job posted.");
		}catch(Exception e) {
			logger.debug("Job Posting failed for User : \'" + UserSession.getUserEmail()+ "\'Reason : " + e.getMessage());
			return CreationResult.failed(e.getMessage());
		}
	}

	@Override
	public CreationResult applyForJob(Job j, int userId) {
		
		if(j.getJobUserId() == userId) {
			logger.debug("User : \'" + UserSession.getUserEmail()+ "\' has failed applying for job. Reason : User tried to apply for own job post"); 
			return CreationResult.failed("You can't apply for this job. Reason : You created this post");
		}
		if(isUserAlreadyAppliedForJob(j.getJobId(),userId)) {
			logger.debug("User : \'" + UserSession.getUserEmail()+ "\' has failed applying for job. Reason : User has already applied for job."); 
			return CreationResult.failed("Already applied for this job");
		}
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
            String insertQuery = "INSERT INTO applications (userId, jobId,applicationDate) VALUES (?,?,?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2, j.getJobId());
            Date now = new Date();
            Timestamp time = new Timestamp(now.getTime()); 
            preparedStatement.setTimestamp(3, time);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            logger.information("User : \'" + UserSession.getUserEmail() + "\' has applied for job.");
            return CreationResult.success("Applied for job.");
		}catch(Exception e) {
			logger.debug("User : \'" + UserSession.getUserEmail()+ "\' has failed applying for job. Reason : " + e.getMessage());
			return CreationResult.failed(e.getMessage());
		}
	}

	private boolean isUserAlreadyAppliedForJob(int jobId, int userId) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
            String insertQuery = "Select * from applications where jobId = ? and userId = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,jobId);
            preparedStatement.setInt(2, userId);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()) {
            	return true;
            }
            preparedStatement.close();
            connection.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ObservableList<Job> getJobListByUserId(int userId) {
		ObservableList<Job> jobList = FXCollections.observableArrayList();
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Select * from jobs where jobUserId = ?";
        
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Job job = new Job().setJobId(resultSet.getInt("jobId"))
					.setJobHeader(resultSet.getString("jobHeader"))
					.setCompanyName(resultSet.getString("companyName"))
					.setJobText(resultSet.getString("jobText"))
					.setJobSalary(resultSet.getInt("jobSalary"))
					.setJobPostedDate(resultSet.getTimestamp("jobPostedDate"))
					.setJobUserId(resultSet.getInt("jobUserId"));
					jobList.add(job);
				}
				
				preparedStatement.close();
				connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return jobList;
	}

	@Override
	public UpdateResult updateJobPost(Job j) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
            String insertQuery = "UPDATE jobs SET jobHeader = ? , companyName = ? , jobText = ? , jobSalary = ? where jobId = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1,j.getJobHeader());
            preparedStatement.setString(2, j.getCompanyName());
            preparedStatement.setString(3, j.getJobText());
            preparedStatement.setInt(4, j.getJobSalary());
            preparedStatement.setInt(5, j.getJobId());
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            logger.information("User : \'" + UserSession.getUserEmail() + "\' has updated job.");
            return UpdateResult.success("Job updated.");
		}catch(Exception e) {
			logger.debug("User : \'" + UserSession.getUserEmail()+ "\' has failed update job. Reason : " + e.getMessage());
			return UpdateResult.failed(e.getMessage());
		}
	}

	@Override
	public void deleteJobPost(Job j) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
            String insertQuery = "Delete from jobs where jobId = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,j.getJobId());
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            logger.information("User : \'" + UserSession.getUserEmail() + "\' has deleted job.");
		}catch(Exception e) {
			logger.debug("User : \'" + UserSession.getUserEmail()+ "\' has failed to deleting job. Reason : " + e.getMessage());
		}
		
	}

	@Override
	public ObservableList<User> getApplications(int jobId) {
		ObservableList<User> userList = FXCollections.observableArrayList();
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Select users.userId , users.userName , users.userPhoneNumber, users.userSurname , users.userEmail , users.userAddress , users.hasUserCV from users "
					+ "INNER JOIN applications ON users.userId = applications.userId where applications.jobId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, jobId);
			ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					User user = new User()
							.setName(resultSet.getString("users.userName"))
							.setSurname(resultSet.getString("users.userSurname"))
							.setUserId(resultSet.getInt("users.userId"))
							.setPhoneNumber(resultSet.getString("users.userPhoneNumber"))
							.setUserEmail(resultSet.getString("users.userEmail"))
							.setAdress(resultSet.getString("users.userAddress"))
							.setHasUserCV(resultSet.getBoolean("users.hasUserCV"));
					
					userList.add(user);
							
				}
				
				preparedStatement.close();
				connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return userList;
	}

	@Override
	public ObservableList<Job> getApplicationsByUserId(int userId) {
		ObservableList<Job> jobList = FXCollections.observableArrayList();
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Select applications.applicationId, jobs.companyName , jobs.jobHeader , jobs.jobText, jobs.jobSalary , applications.applicationDate from jobs "
					+ "INNER JOIN applications ON jobs.jobId = applications.jobId where applications.userId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Job j = new Job()
							.setJobId(resultSet.getInt("applicationId"))
							.setCompanyName(resultSet.getString("companyName"))
							.setJobHeader(resultSet.getString("jobHeader"))
							.setJobSalary(resultSet.getInt("jobSalary"))
							.setJobPostedDate(resultSet.getTimestamp("applicationDate"));
					
					jobList.add(j);
							
				}
				
				preparedStatement.close();
				connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return jobList;
	}

	@Override
	public void deleteJobApplication(int applicationId) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
            String insertQuery = "Delete from applications where applicationId = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,applicationId);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            logger.information("User : \'" + UserSession.getUserEmail() + "\' has deleted his application.");
		}catch(Exception e) {
			logger.debug("User : \'" + UserSession.getUserEmail()+ "\' has failed to deleting application. Reason : " + e.getMessage());
		}
		
	}

}
