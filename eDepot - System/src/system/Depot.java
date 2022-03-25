package system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Depot {
	
	final static Scanner input = new Scanner(System.in);
	
	final static ArrayList<Manager> manager = new ArrayList<Manager>();

	
	public Depot() {
	}
	
	// Declare a 'loadDriver' method, to allow the user data to be access through the method.
			public void loadManager() throws FileNotFoundException {
				
				// Declaring a Scanner with 'driver.txt' source, to allow the system to read the user data.
				Scanner file = new Scanner(new FileReader("Managers.txt"));

				// Loop through the files data.
				while (file.hasNext()) {
					// Set the username to the next String in the file.
					String username = file.next();
					// Set the password to the next String in the file.
					String password = file.next();
					// Add the variables to the 'User' array list.
					manager.add(new Manager(username, password));
					
				}
				// Close the scanner file.
				file.close();
			}

			// Declare a 'logOn' method.
			public void logOn() {
				// Print to the console the array list 'Rooms', to show the full list of rooms data.
				System.out.println("\n-- Login --");
				System.out.print("Please enter your username:");
				// Set the username to the next String, typed in the console application.
				String username = input.next();
				System.out.print("Enter your password:");
				// Set the password to the next String, typed in the console application.
				String password = input.next().toLowerCase();
				boolean correctUsername = false;
				boolean correctPassword = false;
				boolean loggedOn = false;
				// Loop through the User file data.
				for (int i = 0; i < manager.size(); i++) {
					// Get the User file data.
					Manager logOn = manager.get(i);
					// Declare an if statement to match the username and password, against the Driver file data.
					if (logOn.checkUsername(username)) {
						correctUsername = true;
					}
					if (logOn.checkPassword(password)) {
						correctPassword = true;
					}
					// Declare an if statement to match the username and password, against the Driver file data.
					if (logOn.checkUsernameAndPassword(username,password, loggedOn)) {
						// Print a message for the true value, allowing the user to know when they have logged on.
						loggedOn = true;
						System.out.print("\nThankyou " + username + " you have logged on!");
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
					// Print a message for the boolean 'correctUsername' and 'correctPassword' false value.
					loggedOn = false;
					System.out.print("\nYour username and password is invalid");
				}
			}
		}	
	

	public void setUpWorkSchedule() { // add method body
		
	}
}