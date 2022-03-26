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
		boolean loggedOn = false;

		try {

			file = new Scanner(new FileReader("src//Managers.txt"));
			file.useDelimiter("[\n]");
			

			// Loop through the files data.
			while (file.hasNext() && !loggedOn) {
				tempUserName = file.next();
				tempPassWord = file.next();

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loggedOn;
	}
	
	

}