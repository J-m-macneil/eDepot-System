package system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Depot {

	final static Scanner input = new Scanner(System.in);

	final static ArrayList<Manager> manager = new ArrayList<Manager>();

	// public Depot() {
	// }

	// Declare a 'loadDriver' method, to allow the user data to be access through
	// the method.
	public void loadManager() {

		// Declaring a Scanner with 'driver.txt' source, to allow the system to read the
		// user data.
		Scanner file;
		try {
			file = new Scanner(new FileReader("Managers.txt"));
			// Loop through the files data.
			while (file.hasNext()) {
				// Set the username to the next String in the file.
				String username = file.next();
				// Set the password to the next String in the file.
				String password = file.next();
				// Add the variables to the 'User' array list.
				manager.add(new Manager(username, password));
				// Close the scanner file.
				file.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Declare a 'logOn' method.
	public void logOn(String userName, String passWord) {
		loadManager();
		boolean correctUsername = false;
		boolean correctPassword = false;
		boolean loggedOn = false;
		// Loop through the User file data.
		for (int i = 0; i < manager.size(); i++) {
			// Get the User file data.
			Manager logOn = manager.get(i);
			// Declare an if statement to match the username and password, against the
			// Driver file data.
			if (logOn.checkUsername(userName)) {
				correctUsername = true;
			}
			if (logOn.checkPassword(passWord)) {
				correctPassword = true;
			}
			// Declare an if statement to match the username and password, against the
			// Driver file data.
			if (logOn.checkUsernameAndPassword(userName, passWord, loggedOn)) {
				// Print a message for the true value, allowing the user to know when they have
				// logged on.
				loggedOn = true;
				System.out.print("\nThankyou " + userName + " you have logged on!");
			}
			if (!correctUsername) {
				// Print a message for the boolean 'coorectUsername' false value.
				System.out.println("\nYour username does not have a match on the system");
			}
			if (!correctPassword) {
				// Print a message for the boolean 'correctPassword' false value.
				System.out.print("\nYour password does not have a match on the system");
			}
			if (!correctUsername && !correctPassword) {
				// Print a message for the boolean 'correctUsername' and 'correctPassword' false
				// value.
				loggedOn = false;
				System.out.print("\nYour username and password is invalid");
			}
		}
		if (manager.size() == 0) {
			System.out.println("No managers enrolled in depot!");
		}

	}

	public void setUpWorkSchedule() { // add method body

	}
}