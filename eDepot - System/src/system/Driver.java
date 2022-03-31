package system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Driver {

	protected String username;
	protected String password;

	public Driver(String username, String passWord) {

		this.username = username;
		this.password = passWord;

	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassWord() {
		return password;
	}

	public void setPassWord(String passWord) {
		this.password = passWord;
	}

	public void setSchedule() {

	}

	public boolean isAvailable() {
		return false; // needs changing
	}



	public boolean verifyLogin(String userName, String passWord) {

		Scanner file = null;
		String tempUserName = "";
		String tempPassWord = "";
		boolean loggedOn = false;

		try {

			file = new Scanner(new FileReader("Drivers.txt"));
			file.useDelimiter("[\n]");
			

			// Loop through the files data.
			while (file.hasNext() && !loggedOn) {
				tempUserName = file.nextLine();
				tempPassWord = file.nextLine();

				if (tempUserName.trim().equals(userName.trim()) && tempPassWord.trim().equals(passWord.trim())) 
				{
					loggedOn = true;
				}
				else {
					loggedOn = false;
				}
			}
			file.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return loggedOn;
	}
}