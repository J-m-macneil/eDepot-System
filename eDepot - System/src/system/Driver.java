package system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Driver {

	protected String userName;
	protected String passWord;

	public Driver(String userName, String passWord) {

		this.userName = userName;
		this.passWord = passWord;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
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