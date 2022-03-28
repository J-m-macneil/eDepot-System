package system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Depot {

	final static Scanner input = new Scanner(System.in);

	final static List<Driver> drivers = new ArrayList<Driver>();
	final static List<Manager> managers = new ArrayList<Manager>();
	final static List<WorkSchedule> schedule = new ArrayList<WorkSchedule>();
	final static List<String> depots = new ArrayList<String>();

	public void loadDepot() throws FileNotFoundException {

		// Declaring a Scanner with 'driver.txt' source, to allow the system to read
		// depot data.
		Scanner file = new Scanner(new FileReader("src//Depot.txt"));
		// Loop through the files data.
		while (file.hasNextLine()) {
			// Set the depotName to the next String in the file.
			String depotName = file.nextLine();
			depots.add(depotName);
			// Close the scanner file.
		}
	}

	// Declare a 'loadDriver' method, to allow the user data to be access through
	// the method.
	public void loadDriver() throws FileNotFoundException {

		// Declaring a Scanner with 'driver.txt' source, to allow the system to read the
		// user data.
		// Scanner file = null;
		Scanner file = new Scanner(new FileReader("src//Drivers.txt"));
		// Loop through the files data.
		while (file.hasNextLine()) {
			// Set the username to the next String in the file.
			String userName = file.nextLine();
			// Set the password to the next String in the file.
			String passWord = file.nextLine();
			// Add the variables to the 'drivers' array list.
			drivers.add(new Driver(userName, passWord));
		}
	}

	public void loadManager() throws FileNotFoundException {

		// Declaring a Scanner with 'driver.txt' source, to allow the system to read the
		// user data.
		// Scanner file = null;
		Scanner file = new Scanner(new FileReader("src//Managers.txt"));
		// Loop through the files data.
		while (file.hasNextLine()) {
			// Set the username to the next String in the file.
			String userName = file.nextLine();
			// Set the password to the next String in the file.
			String passWord = file.nextLine();
			// Add the variables to the 'drivers' array list.
			managers.add(new Manager(userName, passWord));
		}
	}

	// Declare a 'logOn' method.
	public boolean logOn(String depotName,String userName, String passWord) throws FileNotFoundException {
		loadDriver();
		loadManager();
		loadDepot();
		boolean correctUserName = false;
		boolean loggedOn = false;
		Driver driver = null;
		Manager manager = null;

		// Loop through the Depot file data.
		for (int i = 0; i < depots.size(); i++) {
			if (depotName.equals(depotName)) {
			}
		}

		// Loop through the Driver file data.
		for (int i = 0; i < drivers.size(); i++) {
			// Get the Driver file data.
			driver = drivers.get(i);
			// Declare an if statement to match the username and password, against the
			// Driver file data.
			if (driver.checkUserName(userName)) {
				correctUserName = true;
			}
			if (driver.verifyLogin(userName, passWord)) {
				// Print a message for the true value, allowing the user to know when they have
				// logged on.
				correctUserName = true;
				loggedOn = true;
				System.out.print("\nThankyou " + userName + " you have logged on!\n");
				return true;
			}
		}
		// Loop through the Manager file data
		for (int i = 0; i < managers.size(); i++) {
			// Get the Manager file data.
			manager = managers.get(i);
			// Declare an if statement to match the username and password, against the
			// Manager file data.
			if (manager.checkUserName(userName)) {
				correctUserName = true;
			}
			if (manager.verifyLogin(userName, passWord)) {
				// Print a message for the true value, allowing the user to know when they have
				// logged on.
				correctUserName = true;
				loggedOn = true;
				System.out.print("\nThankyou " + userName + " you have logged on!\n");
				return true;
			}
		}
		if (!correctUserName) {
			// Print a message for the boolean 'coorectUsername' false value.
			System.out.println("\nYour username does not have a match on the system.\n");
		} else if (!loggedOn) {
			// Print a message for the boolean 'correctUsername' and 'correctPassword' false
			// value.
			loggedOn = false;
			System.out.println("\nSorry " + userName + " your username and password do not match!\n");
		}
		if (drivers.size() == 0) {
			System.out.println("\nEveryone's on the roads!");
		}
		return false;

	}

	public void loadSchedule() throws FileNotFoundException {

		// Declaring a Scanner with 'driver.txt' source, to allow the system to read the
		// user data.
		Scanner file = new Scanner(new FileReader("src//Schedule.txt"));
		// Loop through the files data.
		while (file.hasNextLine()) {
			// Set the username to the next String in the file.
			String client = file.nextLine();
			// Set the password to the next String in the file.
			// LocalDate startDate = file.nextLine();
			// Set the password to the next String in the file.
			// LocalDate endDate = file.nextLine();
			// Add the variables to the 'User' array list.
			schedule.add(new WorkSchedule(client, null, null));
			// Close the scanner file.
		}
		file.close();
	}

	public void setUpWorkSchedule() throws FileNotFoundException { // add method body
		loadSchedule();
		saveToFile(null, null, null);

	}

	// Declare a saveToFile method, which loops through the schedule arraylist and
	// maps the data to the Schedule.txt file.
	public void saveToFile(String client, LocalDate startDate, LocalDate endDate) {
		// Declare a try statement, to allow the saveToFile data to be tested for errors
		// while it is being executed.
		try {
			// Declare a 'FileWriter' to write the data to the 'schedule.txt'
			FileWriter writer = new FileWriter("Schedule.txt");
			// Declare a for loop to loop through the rooms array list data.
			for (int i = 0; i < schedule.size(); i++) {
				// Declare a writer to write the text back to the schedule array list.
				writer.write(schedule.get(i).SaveScheduleToFile() + "\n");
			}
			// Close the 'FileWriter' stream.
			writer.close();
			// Set a catch statement, to handle any exception of the try statement.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}