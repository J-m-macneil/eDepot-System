package system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Driver implements Schedulable {

	protected String username;
	protected String password;
	protected Boolean update = true;
	
	protected List<WorkSchedule> schedule = new LinkedList<WorkSchedule>();

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

	@Override
	public boolean isAvailable(LocalDateTime startDate, LocalDateTime endDate) {
		for(WorkSchedule s : schedule) {
			if((s.getStartDate().isBefore(startDate)) && (s.getEndDate().isAfter(startDate))) {
				return false;
			}
			if((s.getStartDate().isBefore(endDate)) && (s.getEndDate().isAfter(endDate))) {
				return false;
			}
			if((s.getStartDate().isAfter(startDate)) && (s.getEndDate().isBefore(endDate))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void addSchedule(WorkSchedule schedule) {
		
		
	}

	@Override
	public List<WorkSchedule> getSchedule() {
		
		return null;
	}
	
	
}