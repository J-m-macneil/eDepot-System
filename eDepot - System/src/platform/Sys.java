package platform;

//Used to serialize data
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Class imports
import system.Depot;
import system.Driver;
import system.Manager;
import system.Status;
import system.Tanker;
import system.Truck;
import system.Vehicle;
import system.VehicleDelivery;
import system.WorkSchedule;

/**
 * System class that acts as the outer shell and communicator with all other
 * classes. All I/O is used within this class
 *
 * @author Matt Bailey, Joe Macneil, Liam Clarke
 * 
 * @version 1.0
 */
public class Sys {

	private List<Depot> depots = new ArrayList<Depot>();
	private Depot depot;
	private Driver driver;
	private String currentUser;
	private String currentLocation;

	private static final Scanner input = new Scanner(System.in);

	/**
	 * Sole constructor. Will deserialze objects and load any relevant array lists
	 * that is required for the console application to operate
	 */
	public Sys() {
		deSerialize();

		// Adding all depots.

		/*
		 * depots.add(new Depot("Lpool")); depots.add(new Depot("Mchester"));
		 * depots.add(new Depot("Leeds"));
		 * 
		 * Driver glyn = new Manager("GlynofLpool", "GH1234");
		 * depots.get(0).makeDriver(glyn);
		 * 
		 * Driver sorren = new Manager("SorrenofMchester", "SH5678");
		 * depots.get(1).makeDriver(sorren);
		 * 
		 * Driver joe = new Manager("JoeofLeeds", "J1234");
		 * depots.get(2).makeDriver(joe);
		 * 
		 * // Adding all drivers. Driver ben = new Driver("Ben", "1234");
		 * depots.get(0).makeDriver(ben);
		 * 
		 * Driver alex = new Driver("Alex", "1234"); depots.get(1).makeDriver(alex);
		 * 
		 * Driver will = new Driver("Will", "1234"); depots.get(2).makeDriver(will);
		 * 
		 * // Adding all vehicles. Vehicle astra = new Truck("1", "astra", "1", 100,
		 * 200); depots.get(0).makeVehicle(astra); Vehicle mini = new Truck("2",
		 * "astra", "2", 100, 200); depots.get(0).makeVehicle(mini);
		 * 
		 * Vehicle kia = new Truck("3", "kia", "3", 100, 200);
		 * depots.get(1).makeVehicle(kia); Vehicle rangeRover = new Truck("4", "kia",
		 * "4", 100, 200); depots.get(1).makeVehicle(rangeRover);
		 * 
		 * Vehicle ford = new Tanker("5", "ford", "5", 100, 200, "oil");
		 * depots.get(2).makeVehicle(ford); Vehicle nissan = new Tanker("6", "nissan",
		 * "6", 100, 200, "water"); depots.get(2).makeVehicle(nissan);
		 * 
		 */
		// for (Depot d : depots) {
		// d.startCheck();
		// }
	}

	/**
	 * Initial I/O of repeating menu and is required for the user to choose to log
	 * on. Outer box/case of whole program and will serilaize data upon exit
	 * 
	 * @throws Exception
	 */
	public void entryMenu() throws Exception {

		String choice = "";
		// Repeating main menu till logOn successful
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
		// Code will eventually return here and serialize all updated objects to
		// drivers.ser
		serialize();
		System.exit(0);
	}

	/**
	 * Logs on the user and catches any I/O errors. Employs relevant I/O error
	 * catching and informs the user data has been entered incorrectly. Once logged
	 * on, will direct the user to menu depending on whether they are a manager or
	 * not.
	 * 
	 * @throws Exception
	 */
	private void logOn() throws Exception {

		System.out.println("\nPlease select one of the following depot locations:\n");
		displayDepots();

		System.out.print("\nLocation: ");
		String location = input.nextLine();
		// Confirms the entered location exists and selects depot as current
		depot = getDepotByLocation(location);
		currentLocation = location;
		if (depot != null) {
			System.out.print("Username : ");
			String username = input.nextLine();

			System.out.print("Password : ");
			String password = input.nextLine();
			// Retrieves driver object details by handing over the username
			driver = depot.getDriverByName(username);
			// Checks the driver exists and the password entered matches the one stored in
			// object data
			if (driver != null && driver.checkPassword(password)) {
				// If the driver isn't a manager print the driver menu
				if (!Manager.class.isInstance(driver)) {
					currentUser = username;
					System.out.println("\nCorrect! Logged on as driver: " + currentUser);
					driverMenu();
					// If the driver is a manager then print the manager menu
				} else {
					Manager.class.cast(driver);
					currentUser = username;
					System.out.println("\nCorrect! Logged on as manager: " + currentUser);
					managerMenu();
				}

			} else {
				System.err.println("\nInvalid credentials. Please try again!");
				logOn();
			}
		} else {
			System.err.println("\nInvalid location. Please try again!");
			logOn();
		}
	}

	/**
	 * Will print out the location of all instances of type Depot that is in the
	 * array depots. Data cannot be added to the array list within program, data
	 * will have to be hard coded then serilaized.
	 * 
	 * @return Location of all depots
	 */
	private void displayDepots() {
		System.out.println("-- DEPOTS --");
		// Loops through all depots and prints their location
		for (Depot depot : depots) {
			System.out.println(depot.getLocation());
		}
	}

	/**
	 * Will return the Depot object of type Depot from the location.
	 * 
	 * @param location
	 * @return Depot
	 */
	private Depot getDepotByLocation(String location) {
		for (Depot d : depots) {
			// Matches the depot with the location entered
			if (location.equals(d.getLocation())) {
				return d;
			}
		}
		return null;
	}

	/**
	 * Repeating menu that should only be accessed by users of type Driver. Allows
	 * the user to view work schedule using displaySchedule() and log off directing
	 * to logOn().
	 * 
	 * @throws Exception
	 */
	private void driverMenu() throws Exception {
		// Repeating main menu
		String choice = "";
		do {
			// currentUser should already be declared in logOn()
			System.out.println("\n-- " + currentUser + "'s MAIN MENU --");
			System.out.println("1 - View Work Schedule");
			System.out.println("L - Log Off");
			System.out.print("Pick : ");

			choice = input.nextLine();

			switch (choice) {
			case "1": {
				// Drivers can view their schedule that a Manager has created for them.
				displaySchedule(currentUser);
				break;
			}
			case "L": {
				// Returns to the logOn() menu
				entryMenu();
				break;
			}
			default:
				System.err.println("You have entered an incorrect value. Try again!");
			}
		} while (!choice.toUpperCase().equals("L"));
	}

	/**
	 * Repeating menu thats only accessible by managers(drivers of type Manager)
	 * Gateway to plenty of methods to manipulate drivers, vehicles and schedules
	 * via the menu.
	 * 
	 * @throws Exception
	 */
	private void managerMenu() throws Exception {

		String choice = "";
		do {
			// Repeating main menu, currentUser is declared in logOn()
			System.out.println("\n-- " + currentUser + "'s MAIN MENU --");

			System.out.println("1 - View Work Schedule");
			System.out.println("2 - Add Driver");
			System.out.println("3 - Add Vehicle");
			System.out.println("4 - Create Work Schedule");
			System.out.println("5 - Re-assign Vehicle");
			System.out.println("6 - View Depot Vehicles");
			System.out.println("7 - View drivers");
			System.out.println("L - Log Off");
			System.out.print("Pick : ");

			choice = input.nextLine();

			switch (choice) {
			case "1": {
				// Displays schedules exclusive to the currentUser using the parameter
				displaySchedule(currentUser);
				break;
			}
			case "2": {
				// Adds a driver to the drivers array list and in that depot, and later
				// serialized
				addDriver();
				break;
			}
			case "3": {
				// Adds a vehicle to the vehicles array list and in that depot, and later
				// serialized
				addVehicle();
				break;
			}
			case "4": {
				// Creates a schedule for drivers or self, using vehicles in that depot
				createSchedule();
				break;
			}
			case "5": {
				// Moves a vehicle from one depot to another at a specific time. MultiThread
				// included
				reassignVehicle();
				break;
			}
			case "6": {
				displayVehicles();
				break;
			}
			case "7": {
				displayDrivers();
				break;
			}
			case "L": {
				System.out.println("\n");
				entryMenu();
				break;
			}
			default:
				System.err.println("You have entered an incorrect value. Try again!");
			}
		} while (!choice.toUpperCase().equals("L"));
	}

	/**
	 * Adds a driver to the array list drivers for the current depot. Will ask for a
	 * username and password
	 */
	private void addDriver() {

		System.out.print("Driver's username: ");
		String username = input.nextLine();

		System.out.print("Driver's password: ");
		String password = input.nextLine();
		// Any drivers added are exclusive to the current depot object
		depot.addDriver(new Driver(username, password));
	}

	/**
	 * Adds a vehicle of type Truck or Tanker to current depot. Includes relevant
	 * I/O to gather necessary data
	 */
	private void addVehicle() {

		System.out.print("Vehicle registration number: ");

		String regNo = input.next().toLowerCase();

		System.out.print("Vehicle make: ");
		String make = input.next().toLowerCase();

		System.out.print("Vehicle model: ");
		String model = input.next().toLowerCase();

		System.out.print("Vehicle weight (kg): ");
		int weight = input.nextInt();

		System.out.println("Truck or Tanker:");
		String vehicleType = input.next().toLowerCase();
		// Checks if the vehicle entered in of type truck/tanker and adds adds relevant
		// data
		if (vehicleType.equals("truck")) {

			System.out.print("Vehicle cargo capacity: ");
			int cargoCapacity = input.nextInt();
			// Adds a vehicle in current depot of type Truck
			depot.addVehicle(new Truck(regNo, make, model, weight, cargoCapacity));
		} else if (vehicleType.equals("tanker")) {
			System.out.print("Liquid Capacity: ");
			int liquidCapacity = input.nextInt();

			System.out.print("Vehicle liquid type: ");
			String liquidType = input.next().toLowerCase();
			// Adds a vehicle in current depot of type Tanker
			depot.addVehicle(new Tanker(regNo, make, model, weight, liquidCapacity, liquidType));
		} else
			System.err.println("Incorrect Vehicle type!");
		input.nextLine();
	}

	/**
	 * Reads the serialized file(.ser) in the working directory and generates all
	 * objects necessary for the program to operate
	 */
	private void deSerialize() {
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(new FileInputStream("./depots.ser"));
			// Load all data from the serialized file into objects
			depots = (List<Depot>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Serializes the depots array list holding all depots that are of type Depot.
	 * The depots hold arrays of drivers, vehicles and schedules. The method will
	 * only read from the file in the working directory called .depots.ser
	 */
	private void serialize() {
		ObjectOutputStream oos;

		try {
			oos = new ObjectOutputStream(new FileOutputStream("./depots.ser"));
			// Writes the array list depots of type Depot into a serilaized file
			oos.writeObject(depots);

			oos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Displays all the current schedules for the current driver in the current
	 * depot.
	 * 
	 * @param driverUsername
	 * @throws Exception
	 */
	private void displaySchedule(String driverUsername) throws Exception {
		// New local list is equal to all schedules in the current depot
		List<WorkSchedule> schedules = depot.getSchedules();
		if (schedules.isEmpty()) {
			System.out.println("\nThere are no current schedules assigned.");
		} else {
			for (WorkSchedule s : schedules) {
				// Loops through the schedules and prints any schedules that match with the
				// driver username
				if (s.getDriver().getUserName().equals(driverUsername)) {
					System.out.println(s.toString());
					// Useless code?
					return;
				}
			}
		}
	}

	/**
	 * Displays all vehicles in the current depot, will list separate text if the
	 * vehicle is of type Truck or Tanker to cover extra variables
	 */
	private void displayVehicles() {
		// Retrieve all vehicles in current depot
		List<Vehicle> vehicles = depot.getVehicles();
		for (Vehicle v : vehicles)
			// if the vehicle is a truck, print out specific toString
			System.out.println(v.toString());
		/*
		 * if (Truck.class.isInstance(v)) { // Would like to call <TruckObject>.toString
		 * but cannot create truck object System.out.println(v.toTruckString()); } else
		 * System.out.println(v.toTankerString());
		 */
	}

	/**
	 * Displays all the drivers in the current object depot
	 * 
	 * @return driver.toString() displays all drivers in a format
	 */
	private void displayDrivers() {
		// Stores all the drivers from the current depot in a local list
		List<Driver> drivers = depot.getDrivers();
		for (Driver d : drivers)
			System.out.println(d.toString());
	}

	/**
	 * Creates and returns a formatted date and time, should be called whenever
	 * dates used in conjunction with LocalDateTime import
	 * 
	 * @param str text to specify why you are moving "move" or "remove"
	 * @return date Returns a parsed and formatted date
	 */
	private LocalDateTime createLocalDateTime(String str) {
		// str should specify why you are creating the date, to "move" or "remove"
		System.out.print("\nSpecify the " + str + " date [i.e. 1986-04-13]: ");
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

	/**
	 * Creates a schedule between a driver and vehicle in a specific depot and
	 * stores it in that depot. This method will handle all I/O errors and allows
	 * the user to go back to their main menu
	 * 
	 * @throws Exception
	 */
	private void createSchedule() throws Exception {
		// Will loop until a condition breaks the loop
		while (true) {
			System.out.println("\n-- CREATE SCHEDULE --\n");

			System.out.print("Clients name: ");
			String client = input.next();

			System.out.println("\nEnter the schedules start and end dates.");
			System.out.println("Make sure the start date is no less than 48 hours in advance.");
			System.out.println("Make sure the end date does not exceed 72 hours after the start date.");
			LocalDateTime startDate;
			LocalDateTime endDate;

			// Any date handling has to be in try catch for error handling
			try {
				// Creates the start and end date with a method call
				startDate = createLocalDateTime("start");
				endDate = createLocalDateTime("end");
				// Checks the start date has at least 48 hours notice
				if (startDate.isAfter(LocalDateTime.now().plusHours(48))) {
					// Checks the end date has maximum 72 hours duration AND end date is after start
					// date
					if (endDate.isBefore(startDate.plusHours(72)) && endDate.isAfter(startDate)) {
						// If the depot has no drivers in array throw error
						if (depot.getDrivers().isEmpty()) {
							input.nextLine();
							System.out.println("\n-- DRIVERS --Im sorry, there are no current drivers at this depot.");
							break;
						}

						displayDrivers();
						System.out.print("\nDrivers name: ");
						Driver driver = depot.getDriverByName(input.next());
						// If the depot has no vehicles in array throw error
						if (depot.getVehicles().isEmpty()) {
							input.nextLine();
							System.out.println(
									"\n-- VEHICLES --\nIm sorry, there are no current vehicles at this depot.");
							break;
						}
						displayVehicles();
						System.out.print("\nVehicle Registration number: ");

						Vehicle vehicle = depot.getVehicleByRegNo(input.next());
						// Creates a schedule in that depot thats initially pending
						depot.createSchedule(
								new WorkSchedule(client, startDate, endDate, driver, vehicle, Status.PENDING));
						input.nextLine(); // To ensure Manager's main menu is accepting null input
						System.out.println("\nSchedule created successfully!");
						break; // Manager can go back to their main menu after successful creation

					}

				} else {
					System.err.println("\nInvalid start/end dates entered. Try again!");
				}

			} catch (Exception e) {
				System.err.println("\nDate/time entry is out of bounds. Try again!");
				continue; // Manager is not kicked out to their main menu if they make a mistake
			}

		}

	}

	/**
	 * Will re-assign a vehicle from one depot to another, only reachable from
	 * drivers that are managers. handles all incorrect I/O and repeats giving the
	 * user opportunity's to correct input. Creates a new thread that will move the
	 * vehicle to another depot and sleeps for a hard coded amount of time
	 * 
	 * @throws Exception
	 */
	private void reassignVehicle() throws Exception {

		LocalDateTime moveDate;
		// Stores all schedules in current depot in a new list
		List<WorkSchedule> schedules = depot.getSchedules();
		// Will loop until a condition breaks the loop
		while (true) {

			System.out.println("\n-- RE-ASSIGN VEHICLE MENU --");
			// If there are no vehicles in the depot throw an error
			if (depot.getVehicles().isEmpty()) {
				System.out.println("There are currently no vehicles in depot to move");
				break;
			}

			displayVehicles();

			System.out.print("\nPlease enter the vehicle registration number: ");
			Vehicle vehicle = depot.getVehicleByRegNo(input.next());
			// Checks the vehicle exists and handles any I/O errors
			if (vehicle != null) {
				System.out.print("Vehicle selected.");
			} else {
				System.err.print("Invalid registration number or no vehciles in depot. Try again!");
				input.nextLine();
				// Allows the user to re enter the vehicle
				continue;
			}
			// Attempts to create a move date and handles errors in date parsing
			try {
				moveDate = createLocalDateTime("move");
				// Check that the Manager is not trying to bypass or enter a past date
				if ((!moveDate.isBefore(LocalDateTime.now()))) {
					if (moveDate != null) {
						System.out.print("\nMove date specified.\n");
					}

				} else {
					System.err.println("No date entered or date is in the past!");
					continue;
				}
			}

			catch (Exception e) {
				System.err.print("Date/time entry is out of bounds. Try again!");
				continue; // Manager is not kicked out to their main menu if they make a mistake
			}
			displayDepots();
			System.out.print("\nPlease specify a depot: ");

			String newLocation = input.next();
			// Creates a local object depot and stores the new depot in it
			Depot newDepot = getDepotByLocation(newLocation);
			// Error handing for incorrect depot location
			if (newDepot == null) {
				System.err.println("Invalid location!");
				input.nextLine();
				continue;
			}
			if (depot != null) {
				// Makes sure that the vehicle is not being moved to same depot
				if (!currentLocation.equals(newLocation)) {
					// if ((moveDate.equals(LocalDateTime.now())))
					System.out.println("Vehicle transfer in progress...");
					// Creates a new thread that will move the vehicle in a time specified in the
					// last argument
					new Thread(new VehicleDelivery(vehicle, depot, newDepot, 20)).start();
					input.nextLine();
					break;
				} else
					System.err.println("Depot locations the same!");
				input.nextLine();
				continue;
			} else {
				System.err.println("Invalid location.\nPlease try again...");
				continue;
			}

		}
		for (WorkSchedule s : schedules) {
			// Checks the schedules in the depot for pending or active
			depot.startCheck();
			if (s.getStatus().equals(Status.PENDING)) {
				if (s.getStatus().equals(Status.ACTIVE)) {
					System.err.println("Vehicle busy! Try again!");
					continue;
				}

			}
		}
	}
}
