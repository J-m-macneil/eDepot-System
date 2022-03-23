package system;

import java.util.Scanner;

public class Driver {
	
	private static final Scanner input = new Scanner(System.in);

	protected String username;
	protected String password;
	
	public Driver(String username, String password) {
		
	this.username = username;
	this.password = password;
		
		input.close();
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setSchedule() {
		
	}
	
	public boolean isAvailable() {
		return false; // needs changing
	}
	
	public boolean checkUsername(String username) {
		if (this.getUsername().equals(username)) {
			return true;
		}
		return false; 
		
	}
	
	public boolean checkPassword(String password) {
		if (this.getPassword().equals(password)) {
			return true;
		}
		return false; 
		
	}
	
	public boolean checkUsernameAndPassword (String username, String password) {
		if (this.getUsername().equals(username) && this.getPassword().equals(password)) {
			return true;
		}
		return false;	
	}
}