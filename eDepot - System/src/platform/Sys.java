package platform;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
// Used to serialize data
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.List;
// import java.time.LocalDate;
import java.util.Scanner;
import system.Depot;
import system.Driver;
import system.Status;
import system.WorkSchedule;

public class Sys {

	// This path is exclusive to me Liam and is used for testing
	private final String PATH = "E:\\University\\Work\\Year 2\\Semester 2\\5104 - Object Orientated Systems\\Graded\\Assignments\\Assignment 2\\Serialized data\\";

	private String userName;
	private String passWord;
	private List<Depot> depots = new ArrayList<Depot>();
	private Depot depot;

	private static final Scanner input = new Scanner(System.in);

	public Sys() {
		deSerialize();

		// depots.add(new Depot("Liverpool"));
		// depots.add(new Depot("Manchester"));
		// depots.add(new Depot("Leeds"));
	}

	public void entryMenu() {

		String choice = "";
		do {
			System.out.println(" -- Entry Menu -- ");
			System.out.println("1 - LogOn");
			System.out.println("Q - Quit");
			System.out.print("Pick: ");
			choice = input.nextLine();
			switch (choice) {
			case "1":
				logOn();
				break;
			default:
				System.out.println("Invalid input!");
				break;
			}
		} while (!choice.equals("Q"));

		serialize();

		System.exit(0);
	}

	public void logOn() {
		Depot depot = new Depot();
		System.out.println("Liverpool\nManchester\nLeeds");
		System.out.print("Location : ");
		String location = input.nextLine();
		System.out.print("Username : ");
		userName = input.nextLine();
		System.out.print("Password : ");
		passWord = input.nextLine();
		// if (depot.logOn(userName, passWord)) {

		// depot = getDepot(location);
		if (depot.logOn(userName, passWord)) {
			System.out.println("Correct!");
			driverMenu();
		} else if (depot.logOnAsManager(userName, passWord)){
			System.out.println("Correct!");
			managerMenu();
		} else {
			System.out.println("Invalid login!");
			entryMenu();
		}
	}
	// }

	/*
	 * public Depot getDepot(String location) { for (Depot d : depots) { if
	 * (location.equals(d)) { return d; } } return depot;
	 * 
	 * }
	 */

	public void driverMenu() {
		// Declare a do while loop, to repeat through the depot systems main menu.
		// Set a default value to choice, to allow user input.
		String choice = "";
		do {
			// String userName = null;
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

	public void managerMenu() {
		// Declare a do while loop, to repeat through the depot systems main menu.
		// Set a default value to choice, to allow user input.
		String choice = "";
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
				// Call displaySchedule() method.
				displaySchedule();
				break;
			}
			case "2": {
				// Call createSchedule() method.
				createSchedule();
				break;
			}
			case "3": {
				// Call reassignVehicle() method.
				reassignVehicle();
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

	private void deSerialize() {
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(new FileInputStream(PATH + "depots.ser"));

			depots = (List<Depot>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void serialize() {
		ObjectOutputStream oos;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(PATH + "depots.ser"));
			oos.writeObject(depots);
			// We could do with putting this in finally, but we then need a throws about
			// everywhere
			oos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void displaySchedule() {
		// Make case 1 in here

	}

	private void createSchedule() {
		System.out.println("Clients name: ");
		String client = input.nextLine();

		System.out.println("Schedules DateTime [i.e 5 Oct 18 09:30] : ");
		LocalDateTime startDate = LocalDateTime.parse(input.nextLine(), DateTimeFormatter.ofPattern("d MMM yy HH:mm"));

		System.out.println("Schedules DateTime [i.e 5 Oct 18 09:30] : ");
		LocalDateTime endDate = LocalDateTime.parse(input.nextLine(), DateTimeFormatter.ofPattern("d MMM yy HH:mm"));

		depot.createSchedule(new WorkSchedule(client, startDate, endDate, Status.PENDING));
	}

	private void reassignVehicle() {
		System.out.println("--\nRE-ASSIGN VEHICLE MENU--\n");
		System.out.print("");
	}

}
