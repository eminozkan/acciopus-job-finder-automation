package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

public class DatabaseConfig {
	private static final String DB_URL = "jdbc:mysql://localhost/202503049";
	private static final String DB_USERNAME = "admin";
	private static final String DB_PASSWORD = "admin";
	
	public static Optional<Connection> getConnection() {
		try {
			return Optional.of(DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD));
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
}
