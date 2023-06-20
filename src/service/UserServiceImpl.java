package service;

import java.sql.Connection;
import java.util.Date;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.ApplicationConfig;
import config.DatabaseConfig;
import support.dto.Role;
import support.dto.User;
import support.encryption.MD5;
import support.result.AuthorizationResult;
import support.result.CreationResult;
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
            preparedStatement.setInt(4, user.getRole() == Role.STANDART ? 2 : 1);
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
					userFromDB.setRole(Role.setRole(resultSet.getInt("userRoleId")));
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
	
	

}
