package platform;

import java.util.Scanner;

import system.Depot;

public class Sys {

	public Depot getDepot() {
		return null; //needs changing

	}

	// Declare the Scanner for keyboard input, to allow the user to input through
	// the console.
	private static final Scanner input = new Scanner(System.in);
	
	// Declare an new array list to store the 'driver.txt' data.
	// private static final ArrayList<Driver> Driver = new ArrayList<Driver>();

	public void run() {
		
		Depot depot = new Depot();
		// Set a default value to choice, to allow user input.
		String choice = "";

		// Declare a do while loop, to repeat through the room booking systems main
		// menu.
		do {
			// Print a repeating main menu, inside the do while loop.
			System.out.println("\n-- MAIN MENU --");
			System.out.println("1 - Login");
			System.out.println("2 - View Work Schedule");
			System.out.println("3 - Create Work Schedule");
			System.out.println("4 - Re-assign Vehicle");
			System.out.println("Q - Quit");
			System.out.print("Pick : ");

			// Allow the user to specify which option on they would like to select.
			// Allow the lower case value 'q' to be entered when exiting the program.
			choice = input.next().toUpperCase();

			// Declare a switch statement, to select one of the menu code blocks to be
			// executed.
			switch (choice) {

			case "1": {
				System.out.print("Please enter your username: ");
				String userName = input.next();
				System.out.print("Please enter your password: ");
				String passWord = input.next();
				depot.logOn(userName, passWord);
				// Set a choice for the method 'LogOn' to be executed.
				// logOn();
				break;
			}
			case "2": {
				// Set a choice for the method 'createSchedule' to be executed.
				// displaySchedule
				break;
			}
			case "3": {
				// Set a choice for the method 'createSchedule' to be executed.
				// createSchedule
				break;
			}
			case "4": {
				// Set a choice for the method 'createSchedule' to be executed.
				// re-assignVehile
				break;
			}
			case "Q": {
				// Set the inputed data to save to the file on exit.
				// saveToFile();
				// Print a message to the console when the application is quit.
				System.out.println("-- GOODBYE --");
				break;
			}
			// Set a default value, for when errors occur in the console application.
			default: {
				// Set a default message, to allow the user to know when an incorrect value has
				// been entered.
				System.out.println("Im sorry you have entered an incorrect value, please try again:");
			}

			}
			// Declare a while loop, to loop through the menu until the program is quit.
		} while (!choice.equals("Q"));
	}

}
