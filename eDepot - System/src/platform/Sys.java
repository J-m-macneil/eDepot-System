package platform;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

import system.Depot;
import system.Driver;

public class Sys {

	public Depot getDepot() {
		return null; // may need changing

	}

	public Driver getDriver(String username, String password) {
		return null; // may need changing

	}

	// Declare the Scanner for keyboard input, to allow the user to input through
	// the console.
	private static final Scanner input = new Scanner(System.in);

	public void run() throws FileNotFoundException {

		boolean loggedOn = false;
		while (!loggedOn) {
			Depot depot = new Depot();
			System.out.println("\n-- Login --\n");
			System.out.print("Please enter your username: ");
			String userName = input.next();
			System.out.print("Please enter your password: ");
			String password = input.next();
			loggedOn = depot.logOn(userName, password);
		}
		// Declare a do while loop, to repeat through the depot systems main menu.
		// Set a default value to choice, to allow user input.
		String choice = "";
		
		do {
			// Print a repeating main menu, inside the do while loop.
			System.out.println("\n-- MAIN MENU --");
			System.out.println("1 - View Work Schedule");
			System.out.println("2 - Create Work Schedule");
			System.out.println("3 - Re-assign Vehicle");
			System.out.println("Q - Quit");
			System.out.print("Pick : ");

			// Allow the user to specify which option on they would like to select.
			// Allow the upper case value 'Q' to be entered when exiting the program.
			choice = input.next().toUpperCase();

			// Declare a switch statement, to select one of the menu code blocks to be
			// executed.
			switch (choice) {
			case "1": {
				// Set a choice for the method 'createSchedule' to be executed.
				// displaySchedule
				break;
			}
			case "2": {
				Depot depot = new Depot();
				System.out.println("\n-- Create Work Schedule --\n");
				System.out.print("Please enter your clients name: ");
				String client = input.next();
				System.out.print("Please the schedule start date: ");
				//LocalDate startDate = input.next();
				System.out.print("Please the schedule end date: ");
				//LocalDate endDate = input.next();
				depot.saveToFile(client, null, null);
				// Set a choice for the method 'createSchedule' to be executed.
				// createSchedule
				break;
			}
			case "3": {
				// Set a choice for the method 'createSchedule' to be executed.
				// re-assignVehile
				break;
			}
			case "Q": {
				// Print a message to the console when the application is quit.
				System.out.println("-- GOODBYE --");
				break;
			}
			// Set a default value, for when errors occur in the console application.
			default: {
				// Set a default message, to allow the user to know when an incorrect value has been entered.
				System.out.println("Im sorry you have entered an incorrect value, please try again:");
			}
			}
			// Declare a while loop, to loop through the menu until the program is quit.
		} while (!choice.equals("Q"));
	}
}
