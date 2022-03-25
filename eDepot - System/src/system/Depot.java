package system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Depot {

	final static Scanner input = new Scanner(System.in);

	final static ArrayList<Driver> drivers = new ArrayList<Driver>();

	// public Depot() {
	// }

	// Declare a 'loadDriver' method, to allow the user data to be access through
	// the method.
	public void loadDriver() {

		// Declaring a Scanner with 'driver.txt' source, to allow the system to read the
		// user data.
		Scanner file = null;
		try {
			file = new Scanner(new FileReader("src//Drivers.txt"));
			// Loop through the files data.
			while (file.hasNext()) {
				// Set the username to the next String in the file.
				String username = file.nextLine();
				// Set the password to the next String in the file.
				String password = file.nextLine();
				// Add the variables to the 'User' array list.
				drivers.add(new Manager(username, password));
				// Close the scanner file.
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} file.close();
	}

	// Declare a 'logOn' method.
	public void logOn(String userName, String password) throws FileNotFoundException {
		loadDriver();
		boolean correctUsername = false;
		boolean loggedOn = false;
		Driver logOn = null;
		// Loop through the User file data.
		for (int i = 0; i < drivers.size(); i++) {
			// Get the User file data.
			logOn = drivers.get(i);
			// Declare an if statement to match the username and password, against the
			// Driver file data.
			if (logOn.checkUsername(userName)) {
				correctUsername = true;
			}
			if (logOn.verifyLogin(userName, password)) {
				// Print a message for the true value, allowing the user to know when they have
				// logged on.
				loggedOn = true;
				System.out.print("\nThankyou " + userName + " you have logged on!\n");	
			}
		}
		if (!correctUsername) {
			// Print a message for the boolean 'coorectUsername' false value.
			System.out.println("\nYour username does not have a match on the system\n");
		}
		if (!loggedOn) {
			// Print a message for the boolean 'correctUsername' and 'correctPassword' false
			// value.
			loggedOn = false;
			System.out.println("\nSorry " + userName + " your username and password do not match!\n");
		}
		if (drivers.size() == 0) {
			System.out.println("Everyone's on the roads!");
		}

	}

	public void setUpWorkSchedule() { // add method body

	}
}