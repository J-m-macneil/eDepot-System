package system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Depot {
	
	final static Scanner input = new Scanner(System.in);
	
	final static ArrayList<Driver> User = new ArrayList<Driver>();

	
	public Depot() {
	}
	
	
//	public void logOn(String username, String password) {
//		boolean found = false;
//		String tempUsername = "";
//		String tempPassword = "";
//	    try {
//	    	Scanner file = new Scanner(new File("Managers.txt"));
//	    	Scanner input = new Scanner(System.in);
//	    	file.useDelimiter("(,\n)");
//	        
//	        while (input.hasNext() && file.hasNext() && !found) {
//	        	tempUsername = file.next();
//	        	tempPassword = file.next();
//	            if (tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim())) {
//	               // Mainemenu menu = new Mainemenu();
//	               //  menu.AdminMenu();
//	               // Above code is a template to let us add different menus but for now see below
//	            	System.out.println("Login Successful!");
//	            	found = true;
//	            	
//	            } else {
//	                System.out.println("--Error--! Press 'Enter' to continue...");
//	                input.nextLine();
//	            }
//	            input.close();
//            	file.close();
//	        }
//	    } catch (IOException e) {
//	        e.getCause();
//	    }
//	    
//	}
	
	// Declare a 'loadDriver' method, to allow the user data to be access through the method.
			public void loadDriver() throws FileNotFoundException {
				
				// Declaring a Scanner with 'driver.txt' source, to allow the system to read the user data.
				Scanner file = new Scanner(new FileReader("Managers.txt"));

				// Loop through the files data.
				while (file.hasNext()) {
					// Set the username to the next String in the file.
					String username = file.next();
					// Set the password to the next String in the file.
					String password = file.next();
					// Add the variables to the 'User' array list.
					User.add(new Driver(username, password));
					
				}
				// Close the scanner file.
				file.close();
			}

			// Declare a 'logOn' method.
			public void logOn(String username, String password) {
				// Print to the console the array list 'Rooms', to show the full list of rooms data.
				System.out.println("\n-- Login --");
				System.out.print("Please enter your username:");
				// Set the username to the next String, typed in the console application.
				username = input.next();
				System.out.print("Enter your password:\n");
				// Set the password to the next String, typed in the console application.
				password = input.next().toLowerCase();
				boolean correctUsername = false;
				boolean correctPassword = false;
				boolean loggedOn = false;
				

				// Loop through the User file data.
				for (int i = 0; i < User.size(); i++) {
					// Get the User file data.
					Driver logOn = User.get(i);
					// Declare an if statement to match the username and password, against the Driver file data.
					if (logOn.checkUsername(username)) {
						correctUsername = true;
					}
					if (logOn.checkPassword(password)) {
						correctPassword = true;
					}
					// Declare an if statement to match the username and password, against the Driver file data.
					if (logOn.checkUsernameAndPassword(username,password)) {
						loggedOn = true;
						// Print a message for the true value, allowing the user to know when they have logged on.
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
					System.out.print("\nYour username and password is invalid");
				}
			}
		}	
	

	public void setUpWorkSchedule() { // add method body
		
	}
}