package platform;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
// Used to serialize data
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.List;
// import java.time.LocalDate;
import java.util.Scanner;
import system.Depot;
import system.Driver;

public class Sys {
	
	// This path is exclusive to me Liam and is used for testing
	private final String PATH = "E:\\University\\Work\\Year 2\\Semester 2\\5104 - Object Orientated Systems\\Graded\\Assignments\\Assignment 2\\Serialized data";
	
	private boolean loggedOn = false;
	private boolean loggedOnAsManager = false;
	private boolean correctDepot = false;
	private String userName = null;
	private String passWord;
	private String depotName;
	private String choice = "";
	private List<Depot> depots = new ArrayList<Depot>();
	
	private static final Scanner input = new Scanner(System.in);

	public Sys() {
		//deSerialize();
		
		depots.add(new Depot("Liverpool"));
		depots.add(new Depot("Manchester"));
		depots.add(new Depot("Leeds"));
	}

	public void entryMenu() throws FileNotFoundException {
		Depot depot = new Depot();
		while (!loggedOn || !loggedOnAsManager) {
			System.out.println("\n-- LOGIN --\n");
			System.out.print("Please enter your username: ");
			userName = input.next();
			System.out.print("Please enter your password: ");
			passWord = input.next();
			loggedOn = depot.logOn(userName, passWord);
			loggedOnAsManager = depot.managerLogOn(userName, passWord);
			if(!loggedOn && !loggedOnAsManager) {
				System.out.println("\nYour credentials are incorrect. Try again.\n");	
			}
			if (loggedOn) {
				System.out.print("\nThankyou " + userName + " you have logged on!\n");
				depotDriverMenu();
			} else if (loggedOnAsManager) {
				System.out.print("\nThankyou " + userName + " you have logged on!\n");
				depotManagerMenu();
			}
		}

		serialize();

	}

	private void deSerialize() {
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(new FileInputStream(PATH + "depots.ser"));

			depots = (List<Depot>)ois.readObject();
			ois.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void serialize() {
		ObjectOutputStream oos;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(PATH + "depots.ser"));
			oos.writeObject(depots);
			// We could do with putting this in finally, but we then need a throws about everywhere
			oos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Depot getDepot() {
		return null; // may need changing
	}

	public void depotDriverMenu() throws FileNotFoundException {
		// Declare a do while loop, to repeat through the depot systems main menu.
		// Set a default value to choice, to allow user input.

		do {
			// Print a repeating main menu, inside the do while loop.
			System.out.println("\n--" + userName + "'s MAIN MENU --");
			System.out.println("1 - View Work Schedule");
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
			case "Q": {
				System.out.println("--GOODBYE--");
				System.exit(0);
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

	public void depotManagerMenu() throws FileNotFoundException {
		// Declare a do while loop, to repeat through the depot systems main menu.
		// Set a default value to choice, to allow user input.

		do {
			// Print a repeating main menu, inside the do while loop.
			System.out.println("\n--" + userName + "'s MAIN MENU --");
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
				// LocalDate startDate = input.next();
				System.out.print("Please the schedule end date: ");
				// LocalDate endDate = input.next();
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
				System.exit(0);
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
