package service;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.util.Date;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.ApplicationConfig;
import config.DatabaseConfig;
import support.dto.CVObject;
import support.dto.ChangePasswordRequest;
import support.dto.Role;
import support.dto.User;
import support.encryption.MD5;
import support.result.AuthorizationResult;
import support.result.CreationResult;
import support.result.UpdateResult;
import support.session.UserSession;

public class UserServiceImpl implements UserService {
	
	private final Logger logger = ApplicationConfig.getLogger();
	
	@Override
	public CreationResult register(User user) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
            String insertQuery = "INSERT INTO users (userName, userPassword,userEmail,userRoleId) VALUES (?,?,?,?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2, MD5.encryptWithMD5(user.getPasswordHash()));
            preparedStatement.setString(3, user.getUserEmail());
            preparedStatement.setInt(4, 2);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            logger.information("User : \'" + user.getUserEmail() + "\' has registered successfully");
            return CreationResult.success("User registered successfully");
		}catch(Exception e) {
			logger.debug("User : \'" + user.getUserEmail() + "\' failed to registration. Reason : " + e.getMessage());
			return CreationResult.failed(e.getMessage());
		}
	}
	
	@Override
	public AuthorizationResult login(User user) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Select * from users where userEmail = ? and userPassword = ?";
        
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1,user.getUserEmail());
			preparedStatement.setString(2,  MD5.encryptWithMD5(user.getPasswordHash()));
			ResultSet resultSet = preparedStatement.executeQuery();
			User userFromDB = new User();
				while (resultSet.next()) {
					userFromDB.setUserId(resultSet.getInt("userId"));
					userFromDB.setUserEmail(resultSet.getString("userEmail"));
					userFromDB.setName(resultSet.getString("userName"));
					userFromDB.setSurname(resultSet.getString("userSurname"));
					userFromDB.setAdress(resultSet.getString("userAddress"));
					userFromDB.setPhoneNumber(resultSet.getString("userPhoneNumber"));
					userFromDB.setRole(Role.setRole(resultSet.getInt("userRoleId")));
					userFromDB.setHasUserCV(resultSet.getBoolean("hasUserCV"));
				}
				

				preparedStatement.close();
				connection.close();
				if(userFromDB.getUserId() == 0) {
					logger.debug("User \'" + user.getUserEmail() + "\' failed to login. Reason : " + "Invalid credentials");
					return AuthorizationResult.failed("Invalid credentials!");
				}
				createSession(userFromDB);
				logger.information("User :" + userFromDB.getUserId() + " has logged into account");
				return AuthorizationResult.success("Login success!");
	}catch(Exception e) {
		logger.debug("User \'" + user.getUserEmail() + "\' failed to login. Reason : " + e.getMessage());
		return AuthorizationResult.failed(e.getMessage());
	}
	}


	private void createSession(User userDB) {
		UserSession.setUser(userDB);
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
            String insertQuery = "INSERT INTO sessions (userId,createdAt) VALUES (?,?)";
            Date now = new Date();
            Timestamp time = new Timestamp(now.getTime()); 
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,userDB.getUserId());
            preparedStatement.setTimestamp(2,time);
            
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            
            logger.information("Session created for User : \'" + userDB.getUserEmail() + "\'.");
		}catch(Exception e) {
			logger.debug("Session creation failed for User : \'" + userDB.getUserEmail() + "\'. Reason : " + e.getMessage());
		}
	}
	
	
	@Override
	public UpdateResult updateProfile(User user) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();

            String insertQuery = "UPDATE users SET userName = ?, userSurname = ?, userPhoneNumber = ?, userAddress = ? where userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getAdress());
            preparedStatement.setInt(5, UserSession.getUserId());
            
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            
            logger.information("User \'" + UserSession.getUserEmail() + "\'  updated successfully.");
            user.setUserId(UserSession.getUserId());
            user.setUserEmail(UserSession.getUserEmail());
            user.setHasUserCV(UserSession.getUser().hasUserCV());
            user.setRole(UserSession.getUserRole());
            UserSession.setUser(user);
            return UpdateResult.success("Updated successfully");
		}catch(Exception e) {
			logger.debug("User \'" + UserSession.getUserEmail() + "\' can't be updated!. Reason : " + e.getMessage());
			return UpdateResult.failed("Update operation failed");
		}
	}
	
	@Override
	public UpdateResult uploadCV(File file) {
        
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			deleteResume();
			updateUserHasCV();
			FileInputStream inputStream = new FileInputStream(file);
            String insertQuery = "INSERT INTO resumes (resumeName,resume,resumeUserId) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, file.getName());
            preparedStatement.setBinaryStream(2,inputStream);
            preparedStatement.setInt(3, UserSession.getUserId());
            
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            
            logger.information("User \'" + UserSession.getUserEmail() + "\' has uploaded cv successfully.");
            UserSession.getUser().setHasUserCV(true);
            return UpdateResult.success("Upload success");
		}catch(Exception e) {
			logger.debug("User \'" + UserSession.getUserEmail() + "\' failed to upload cv!. Reason : " + e.getMessage());
			return UpdateResult.failed("Upload operation failed");
		}
	}
	
	private void deleteResume() {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String deleteQuery = "DELETE FROM resumes where resumeUserId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, UserSession.getUserId());
        
			preparedStatement.executeUpdate();
        
			preparedStatement.close();
			connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateUserHasCV() {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();

			String insertQuery = "UPDATE users SET hasUserCV = 1 where userId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, UserSession.getUserId());
        
			preparedStatement.executeUpdate();
        
			preparedStatement.close();
			connection.close();
		}catch(Exception e) {
			
		}
	}
	
	
	@Override
	public CVObject getCV() {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Select * from resumes where resumeUserId = ?";
			String fileName = "";
			byte[] blobData = null;
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1,UserSession.getUserId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Blob blob = resultSet.getBlob("resume");
			    blobData = blob.getBytes(1, (int) blob.length());
				fileName = resultSet.getString("resumeName");
			}
			
			return new CVObject(blobData)
				.setFileName(fileName);
		}catch(Exception e) {
			return new CVObject();
		}
	}
	
	
	@Override
	public UpdateResult changePassword(ChangePasswordRequest request) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();

            String insertQuery = "UPDATE users SET userPassword = ? where userId = ? and userPassword = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, MD5.encryptWithMD5(request.newPassword()));
            preparedStatement.setInt(2, UserSession.getUserId());
            preparedStatement.setString(3, MD5.encryptWithMD5(request.oldPassword()));
            
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows > 0) {
            	return UpdateResult.success();
            }else {
            	return UpdateResult.failed("Invalid credentials");
            }
		}catch(Exception e) {
			return UpdateResult.failed(e.getMessage());
		}
		
	}

	@Override
	public CVObject getCV(int userId) {
		try {
			Connection connection = DatabaseConfig.getConnection().orElseThrow();
			String selectQuery = "Select * from resumes where resumeUserId = ?";
			String fileName = "";
			byte[] blobData = null;
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1,userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Blob blob = resultSet.getBlob("resume");
			    blobData = blob.getBytes(1, (int) blob.length());
				fileName = resultSet.getString("resumeName");
			}
			
			return new CVObject(blobData)
				.setFileName(fileName);
		}catch(Exception e) {
			return new CVObject();
		}
	}

}
