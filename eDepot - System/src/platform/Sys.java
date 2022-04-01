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

public class Sys {

	private List<Depot> depots = new ArrayList<Depot>();
	private Depot depot;
	private Driver driver;
	private Manager manager;
	private String currentUser;

	private static final Scanner input = new Scanner(System.in);

	public Sys() {
		//deSerialize();

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
		System.out.println("GoodBye!");
		System.exit(0);
	}

	private void logOn() throws Exception {
		System.out.println("\nLiverpool\nManchester\nLeeds\n");

		System.out.print("Location: ");
		String location = input.nextLine();

		System.out.print("Username : ");
		String username = input.nextLine();

		System.out.print("Password : ");
		String password = input.nextLine();
		
		depot = getDepotLocation(location);
		if (depot != null) {
			
			driver = depot.getDriverByName(username);
			if (driver != null) {
				if (driver.checkPassword(password)) {
					if (Manager.class.isInstance(driver)) {
						manager = Manager.class.cast(driver);
						currentUser = username;
						System.out.println("Correct! Logged on as manager: " + currentUser);
						managerMenu();
					}
					else if (driver.checkPassword(password)){
						currentUser = username;
						System.out.println("Correct! Logged on as driver: " + currentUser);
						driverMenu();
					} else {
						System.out.println("Invalid login!");
						entryMenu();
					}
				}
			}	
		}	
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

	private Depot getDepotLocation(String location) {
		for (Depot d : depots) {
			if (location.equals(d.getLocation())) {
				return d;
			}
		}
		return null;
	}

	private void driverMenu() {
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
			choice = input.nextLine();
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

	}
	
	private LocalDateTime createLocalDateTime(String str) {
		// This will be used for the createSchedule() method to create startDate and endDate
		System.out.print("Specify the " + str + " date [i.e. 1986-04-13]:  ");
		String tempDate = input.next();
		
		System.out.print("Specify the time [i.e. 12:30]: ");
		String tempTime = input.next();
		
		String tempDateTime = tempDate + " " + tempTime;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime date = LocalDateTime.parse(tempDateTime, formatter);
		return date;
	}

	private void createSchedule() throws Exception {
		System.out.println("\n-- CREATE SCHEDULE --\n");

		System.out.print("Clients name: ");
		String client = input.next();

		LocalDateTime startDate = createLocalDateTime("start");
		LocalDateTime endDate = createLocalDateTime("end");

		System.out.print("Drivers name: ");
		Driver driver = depot.getDriverByName(input.next());

		System.out.print("Vehicle Registration number: ");
		Vehicle vehicle = depot.getVehicleByRegNo(input.next());

		depot.createSchedule(new WorkSchedule(client, startDate, endDate, driver, vehicle));
		System.out.println("Work Schedule created:\n" + WorkSchedule.toString(client, startDate, endDate, driver, vehicle));
		
	}

	private void reassignVehicle() {
		while (true) {
			System.out.println("\n-- RE-ASSIGN VEHICLE MENU --\n");
			System.out.println("\n-- Please specify what type of Vehicle you are moving --\n");
			System.out.print("Pick (Either 'Truck' or 'Tanker'): ");
			String type = input.nextLine();
			if (type.equals("Truck")) {
				System.out.print("Enter Truck's registration number : ");
				String regNo = input.nextLine();
				if (depot.getVehicleByRegNo(regNo) != null) {
					System.out.println("Vehicle Found!");
					System.out.println(depot.getVehicleByRegNo(regNo) + "exists in system");
				} else {
					System.err.println("Invalid input. Try again");
					continue;
				}

				// Not working - check getVehicleByMake() in Depot class

			} else if (type.equals("Tanker")) {

			} else {
				System.err.println("Invalid input. Try again");
				continue; // Manager is not kicked out to main menu if they make a mistake
			}
		}
	}

}
