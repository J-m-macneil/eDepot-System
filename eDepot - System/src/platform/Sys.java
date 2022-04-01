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
	//private final String PATH = "E:\\University\\Work\\Year 2\\Semester 2\\5104 - Object Orientated Systems\\Graded\\Assignments\\Assignment 2\\Serialized data\\";
	// This path is exclusive to Joe and is used for testing.
	private final String PATH = "D:\\OOP Coursework\\";
	
	private List<Depot> depots = new ArrayList<Depot>();
	private Depot depot;
	private String currentUser;
	
	private static final Scanner input = new Scanner(System.in);

	public Sys() {
		deSerialize();

		depots.add(new Depot("Liverpool"));
		depots.add(new Depot("Manchester"));
		depots.add(new Depot("Leeds"));
	}

	public void entryMenu() throws Exception {

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
			}
		} while (!choice.toUpperCase().equals("Q"));
		serialize();
	}
	
	public void logOn() throws Exception {	
		System.out.println("\nLiverpool\nManchester\nLeeds\n");
		
		System.out.print("Location: ");
		String location = input.nextLine();
		
		System.out.print("Username : ");
		String username = input.nextLine();
		
		System.out.print("Password : ");
		String password = input.nextLine();
		
		depot = getDepotLocation(location);
		if (depot.logOn(username, password)) {
			currentUser = username;
			System.out.println("Correct! Logged on as user: " + currentUser);
			driverMenu();
		} else if (depot.logOnAsManager(username, password)) {
			currentUser = username;
			System.out.println("Correct! Logged on as manager: "+ currentUser);
			managerMenu();
		} else {
			System.out.println("Invalid login!");
			entryMenu();
		}
	}

	public Depot getDepotLocation(String location) {
		for (Depot d : depots) {
			if (location.equals(d.getLocation())) {
				return d;
			}
		}
		return null;
	}

	public void driverMenu() {
		// Declare a do while loop, to repeat through the depot systems main menu.
		// Set a default value to choice, to allow user input.
		String choice = "";
		do {
			// Print a repeating main menu, inside the do while loop.
			System.out.println("\n-- " + currentUser + "'s MAIN MENU --");
			System.out.println("1 - View Work Schedule");
			System.out.println("2 - Log Off");
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
		} while (!choice.equals("2"));

	}

	public void managerMenu() throws Exception {
		// Declare a do while loop, to repeat through the depot systems main menu.
		// Set a default value to choice, to allow user input.
		String choice = "";
		do {
			// Print a repeating main menu, inside the do while loop.
			System.out.println("\n-- " + currentUser + "'s MAIN MENU --");
			System.out.println("1 - View Work Schedule");
			System.out.println("2 - Create Work Schedule");
			System.out.println("3 - Re-assign Vehicle");
			System.out.println("4 - Log Off");
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
				// Set a choice for the method 'createSchedule' to be executed.
				createSchedule();
				break;
			}
			case "3": {
				// Set a choice for the method 'reassignVehicle' to be executed.
				reassignVehicle();
				break;
			}
			case "4": {
				// Print a message to the console when the application is quit.
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
		} while (!choice.equals("4"));

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
		
	}

	private void createSchedule() throws Exception {
		System.out.println("\n-- CREATE SCHEDULE --\n");
		
		System.out.print("Clients name: ");
		String client = input.next();

		System.out.print("Schedules DateTime [i.e 5 Oct 18 09:30]: ");
		LocalDateTime startDate = LocalDateTime.parse(input.next(), DateTimeFormatter.ofPattern("d MMM yy HH:mm"));

		System.out.print("Schedules DateTime [i.e 5 Oct 18 09:30]: ");
		LocalDateTime endDate = LocalDateTime.parse(input.next(), DateTimeFormatter.ofPattern("d MMM yy HH:mm"));

		System.out.print("Drivers name: ");
		String driver = input.next();
		
		System.out.print("Vehicle Registration number: ");
		String vehicle = input.next();
		
		depot.createSchedule(new WorkSchedule(client, startDate, endDate, driver, vehicle));
	}

	private void reassignVehicle() {
		System.out.println("--\nRE-ASSIGN VEHICLE MENU--\n");
		System.out.print("");
	}

}
