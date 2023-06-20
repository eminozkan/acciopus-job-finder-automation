package service;


public class Logger {
	
	public void debug(String message) {
		System.out.println( "🐞 DEBUG : " + message);
	}
	
	public void information(String message) {
		System.out.println("❓ INFORMATION : " + message);
	}
	
	public void error(String message) {
		System.out.println("❗ ERROR : " + message);
	}
	
}
