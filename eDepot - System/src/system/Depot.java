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

	private boolean correctUserName = false;
	private boolean loggedOn = false;
	private boolean loggedOnAsManager = false;
	private String depot;

	final static Scanner input = new Scanner(System.in);

	final static List<Driver> drivers = new ArrayList<Driver>();
	final static List<Manager> managers = new ArrayList<Manager>();
	final static List<WorkSchedule> schedule = new ArrayList<WorkSchedule>();
	final static List<String> depots = new ArrayList<String>();
	
	public Depot(String depot) {
		this.depot = depot;
	}

	public Depot() {
	}

	public void loadDepot() throws FileNotFoundException {

		// Declaring a Scanner with 'driver.txt' source, to allow the system to read
		// depot data.
		Scanner file = new Scanner(new FileReader("Depots.txt"));
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
		Scanner file = new Scanner(new FileReader("Drivers.txt"));
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
		Scanner file = new Scanner(new FileReader("Managers.txt"));
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
	public boolean logOn(String depotName, String userName, String passWord) throws FileNotFoundException {
		loadDepot();
		loadDriver();
		Driver driver = null;
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
				loggedOn = true;
				System.out.print("\nThankyou " + userName + " you have logged on!\n");
				return true;
			}

		}

		if (!correctUserName) {
			// Print a message for the boolean 'coorectUsername' false value.
			correctUserName = false;
			System.out.println("\nYour username does not have a match on the system.\n");
		} else if (!loggedOn) {
			// Print a message for the boolean 'correctUsername' and 'correctPassword' false
			// value.
			loggedOn = false;
			System.out.println("\nSorry " + userName + " your username and password do not match!\n");
		}
		return loggedOn;
		

	}

	public boolean managerLogOn(String depotName, String userName, String passWord) throws FileNotFoundException {
		loadDepot();
		loadManager();
		Manager manager = null;
		// Loop through the Depot file data.
		for (int i = 0; i < depots.size(); i++) {
			if (depotName.equals(depotName)) {
			}
		}

		// Loop through the Manager file data.
		for (int i = 0; i < managers.size(); i++) {
			// Get the Driver file data.
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
				loggedOnAsManager = true;
				System.out.print("\nThankyou " + userName + " you have logged on!\n");
				return true;
			}

		}

		if (!correctUserName) {
			// Print a message for the boolean 'coorectUsername' false value.
			// correctUserName = false;
			System.out.println("\nYour username does not have a match on the system.\n");
		} else if (!loggedOnAsManager) {
			// Print a message for the boolean 'correctUsername' and 'correctPassword' false
			// value.
			loggedOnAsManager = false;
			System.out.println("\nSorry " + userName + " your username and password do not match!\n");
		}
		return loggedOnAsManager;
	}

	public void loadSchedule() throws FileNotFoundException {

		// Declaring a Scanner with 'driver.txt' source, to allow the system to read the
		// user data.
		Scanner file = new Scanner(new FileReader("Schedules.txt"));
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
			FileWriter writer = new FileWriter("Schedules.txt");
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