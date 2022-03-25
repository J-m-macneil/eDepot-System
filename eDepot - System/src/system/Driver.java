package system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	private static final Scanner input = new Scanner(System.in);
	final static ArrayList<Driver> drivers = new ArrayList<Driver>();

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

	public boolean verifyLogin(String username, String password, boolean loggedOn) {

		Scanner file = null;
		String tempUsername = "";
		String tempPassword = "";

		try {

			file = new Scanner(new FileReader("src//Drivers.txt"));
			file.useDelimiter("[\n]");
			

			// Loop through the files data.
			while (file.hasNext() && !loggedOn) {
				tempUsername = file.next();
				tempPassword = file.next();

				if (tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim())) 
				{
					loggedOn = true;
				}
				else loggedOn = false;
			}
			file.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loggedOn;
	}
}