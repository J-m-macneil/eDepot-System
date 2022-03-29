package system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Manager extends Driver {

	public Manager(String userName, String passWord) {
		super(userName, passWord);
	}
	
	public boolean verifyLogin(String userName, String passWord) {

		Scanner file = null;
		String tempUserName = "";
		String tempPassWord = "";
		boolean loggedOnAsManager = false;

		try {

			file = new Scanner(new FileReader("Managers.txt"));
			file.useDelimiter("[\n]");
			

			// Loop through the files data.
			while (file.hasNext() && !loggedOnAsManager) {
				tempUserName = file.next();
				tempPassWord = file.next();

				if (tempUserName.trim().equals(userName.trim()) && tempPassWord.trim().equals(passWord.trim())) 
				{
					loggedOnAsManager = true;
				}
				else {
					loggedOnAsManager = false;
				}
			}
			file.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return loggedOnAsManager;
	}
	
	

}