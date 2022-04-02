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
import system.Manager;
import system.Status;
import system.Vehicle;
import system.WorkSchedule;

public class Sys implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Depot> depots = new ArrayList<Depot>();
	private Depot depot = new Depot();;
	private Driver driver;
	private Manager manager;
	private String currentUser;

	private static final Scanner input = new Scanner(System.in);

	public Sys() {
		deSerialize();

		//depots.add(new Depot("Liverpool"));
		//depots.add(new Depot("Manchester"));
		//depots.add(new Depot("Leeds"));
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
		System.out.println("--GOODBYE--");
		serialize();
		System.exit(0);
	}

	private void logOn() throws Exception {
		System.out.println("\nPlease select one of the following depot locations:\n\n[Liverpool/Manchester/Leeds]\n");

		System.out.print("Location: ");
		String location = input.nextLine();

		System.out.print("Username : ");
		String username = input.nextLine();

		System.out.print("Password : ");
		String password = input.nextLine();
		
		depot = getDepotByLocation(location);
		if (depot != null) {

			driver = depot.getDriverByName(username);
			if (driver != null) {
				if (driver.checkPassword(password)) {
					if (Manager.class.isInstance(driver)) {
						manager = Manager.class.cast(driver);
						currentUser = username;
						System.out.println("\nCorrect! Logged on as manager: " + currentUser);
						managerMenu();
					} else if (driver.checkPassword(password)) {
						currentUser = username;
						System.out.println("\nCorrect! Logged on as driver: " + currentUser);
						driverMenu();
					} else System.out.println("\nInvalid credentials. Please try again!");
					logOn();
				}
			}
		}
		System.out.println("\nInvalid location. Please try again!");
		logOn();
	}

//		depot = getDepotLocation(location);
//		if (depot.logOn(username, password)) {
//			currentUser = username;
//			System.out.println("Correct! Logged on as user: " + currentUser);
//			driverMenu();
//		} else if (depot.logOnAsManager(username, password)) {
//			currentUser = username;
//			System.out.println("Correct! Logged on as manager: " + currentUser);
//			managerMenu();
//		} else {
//			System.out.println("Invalid login!");
//			entryMenu();
//		}
//	}

	private Depot getDepotByLocation(String location) {
		for (Depot d : depots) {
			//Works
			if (location.equals(d.getLocation())) {
				return d;
			}
		}
		return null;
	}

	private void driverMenu() throws Exception {
		// Declare a do while loop, to repeat through the depot systems main menu.
		// Set a default value to choice, to allow user input.
		String choice = "";
		do {
			// Print a repeating main menu, inside the do while loop.
			System.out.println("\n-- " + currentUser + "'s MAIN MENU --");
			System.out.println("1 - View Work Schedule");
			System.out.println("L - Log Off");
			System.out.print("Pick : ");

			// Allow the user to specify which option on they would like to select.
			// Allow the upper case value 'Q' to be entered when exiting the program.
			choice = input.nextLine();
			// Declare a switch statement, to select one of the menu code blocks to be
			// executed.
			switch (choice) {
			case "1": {
				// Drivers can view their schedule that a Manager has created for them.
				displaySchedule();
				break;
			}
			case "L": {
				System.out.println("\n");
				entryMenu();
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
		} while (!choice.toUpperCase().equals("L"));

	}

	private void managerMenu() throws Exception {
		// Declare a do while loop, to repeat through the depot systems main menu.
		// Set a default value to choice, to allow user input.
		String choice = "";
		do {
			// Print a repeating main menu, inside the do while loop.
			System.out.println("\n-- " + currentUser + "'s MAIN MENU --");
			System.out.println("1 - View Work Schedule");
			System.out.println("2 - Create Work Schedule");
			System.out.println("3 - Re-assign Vehicle");
			System.out.println("L - Log Off");
			System.out.print("Pick : ");

			// Allow the user to specify which option on they would like to select.
			// Allow the upper case value 'Q' to be entered when exiting the program.
			choice = input.nextLine();

			// Declare a switch statement, to select one of the menu code blocks to be
			// executed.
			switch (choice) {
			case "1": {
				// displaySchedule() method executed, as a Manager is a Driver.
				displaySchedule();
				break;
			}
			case "2": {
				// createSchedule() method executed, for Managers to make schedules for
				// themselves
				// or other Drivers.
				createSchedule();
				break;
			}
			case "3": {
				// reassignVehicle() method executed, for Managers to move a vehicle to another
				// Depot.
				reassignVehicle();
				break;
			}
			case "L": {
				System.out.println("\n");
				entryMenu();
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
		} while (!choice.toUpperCase().equals("L"));

	}

	private void deSerialize() {
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(new FileInputStream("./depots.ser"));

			depots = (List<Depot>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void serialize() {
		ObjectOutputStream oos;

		try {
			oos = new ObjectOutputStream(new FileOutputStream("./depots.ser"));
			oos.writeObject(depots);
			// We could do with putting this in finally, but we then need a throws about
			// everywhere
			oos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void displaySchedule() {
		// No need to ask the Driver for their name or anything here - they are logged
		// in.
		// Just return their data, whoever's doing this. Could be me lol - Matt

	}

	private LocalDateTime createLocalDateTime(String str) {
		System.out.print("\nSpecify the " + str + " date [i.e. 1986-04-13]:  ");
		String tempDate = input.next();

		System.out.print("Specify the time [i.e. 12:30]: ");
		String tempTime = input.next();

		// LocalDateTime user input needs to be separate hence the concatenation
		String tempDateTime = tempDate + " " + tempTime;

		// Parsing user input to the correct String formatting of type LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime date = LocalDateTime.parse(tempDateTime, formatter);
		return date;

	}

	private void createSchedule() throws Exception {
		while (true) {
			System.out.println("\n-- CREATE SCHEDULE --");

			System.out.print("Clients name: ");
			String client = input.next();

			LocalDateTime startDate;
			LocalDateTime endDate;

			try {
				startDate = createLocalDateTime("start");
				endDate = createLocalDateTime("end");
				System.out.print("\nDrivers name: ");
				Driver driver = depot.getDriverByName(input.next());

				System.out.print("\nVehicle Registration number: ");
				Vehicle vehicle = depot.getVehicleByRegNo(input.next());
				depot.createSchedule(new WorkSchedule(client, startDate, endDate, driver, vehicle));
				input.nextLine(); // To ensure Manager's main menu is accepting null input
				System.out.println("\nSchedule created successfully!");
				break; // Manager can go back to their main menu after successful creation
						
			} catch (Exception e) {
				System.err.println("\nDate/time entry is out of bounds. Try again!");
				continue; // Manager is not kicked out to their main menu if they make a mistake
			}

		}

	}

	private void reassignVehicle() {

		LocalDateTime moveDate;

		while (true) {
			System.out.println("\n-- RE-ASSIGN VEHICLE MENU --");
			System.out.println("\nPlease enter the vehicle registration number: ");
			Vehicle vehicle = depot.getVehicleByRegNo(input.next());

			if (vehicle != null) {
				System.out.println("Vehicle selected.");
			} else {
				System.err.println("Invalid registration number.\nPlease try again...");
				continue;
			}

			try {
				System.out.println("\nPlease specify a move date: ");
				moveDate = createLocalDateTime("move");
				if (moveDate != null) {
					System.out.println("Move date specified.");
				}
			} catch (Exception e) {
				System.err.println("Date/time entry is out of bounds. Try again!");
				continue; // Manager is not kicked out to their main menu if they make a mistake
			}

			System.out.println("Please specify a depot: \n[Liverpool/Manchester/Leeds]\n");
			depot = getDepotByLocation(input.next());
			if (depot != null) {
				System.out.println("\nDepot location specified.");
			} else {
				System.err.println("Invalid location.\nPlease try again...");
				continue;
			}

		}

	}
}
