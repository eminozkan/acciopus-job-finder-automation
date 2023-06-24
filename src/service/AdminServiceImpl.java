package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.DatabaseConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import support.dto.Job;
import support.dto.Role;
import support.dto.Session;
import support.dto.User;
import support.session.UserSession;

public class AdminServiceImpl implements AdminService{

	@Override
	public ObservableList<User> getUsers() {
		ObservableList<User> userList = FXCollections.observableArrayList();
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Select * from users";
        
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					int roleId = resultSet.getInt("userRoleId");
					User user = new User()
							.setUserId(resultSet.getInt("userId"))
							.setName(resultSet.getString("userName"))
							.setSurname(resultSet.getString("userSurname"))
							.setUserEmail(resultSet.getString("userEmail"))
							.setPhoneNumber(resultSet.getString("userPhoneNumber"))
							.setAdress(resultSet.getString("userAddress"))
							.setHasUserCV(resultSet.getBoolean("hasUserCV"))
							.setRole(Role.setRole(roleId));
					
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
	public ObservableList<Job> getJobs() {
		ObservableList<Job> jobList = FXCollections.observableArrayList();
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Select * from jobs INNER JOIN users ON users.userId = jobUserId";
        
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Job job = new Job().setJobId(resultSet.getInt("jobId"))
					.setJobHeader(resultSet.getString("jobHeader"))
					.setJobUserName(resultSet.getString("userName") + " " + resultSet.getString("userSurname"))
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
	public ObservableList<Session> getSessions() {
		ObservableList<Session> sessionList = FXCollections.observableArrayList();
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Select * from sessions INNER JOIN users ON users.userId = sessions.userId";
        
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Session session = new Session()
							.setSessionId(resultSet.getInt("sessionId"))
							.setSessionUserName(resultSet.getString("userName") + " " + resultSet.getString("userSurname"))
							.setTimestamp(resultSet.getTimestamp("createdAt"));
					
					sessionList.add(session);
				}
				
				preparedStatement.close();
				connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return sessionList;
	}

	@Override
	public void deleteJob(int jobId) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
            String insertQuery = "Delete from jobs where jobId = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,jobId);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(int userId) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
            String insertQuery = "Delete from users where userId = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,userId);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void setUserAsAdmin(int userId) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
            String insertQuery = "Update users SET userRoleId = 1 where userId = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,userId);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
