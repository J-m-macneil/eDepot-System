package platform;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
// Used to serialize data
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
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
import system.StatusCheck;
import system.Tanker;
import system.Truck;
import system.Vehicle;
import system.WorkSchedule;

public class Sys {

	private List<Depot> depots = new ArrayList<Depot>();
	private Depot depot;
	private Depot newDepot;
	private Driver driver;
	private String currentUser;
	private String currentLocation;
	private String newLocation;

	private static final Scanner input = new Scanner(System.in);

	public Sys() {
		deSerialize();

		// Adding all depots.
//		depots.add(new Depot("Lpool"));
//		depots.add(new Depot("Mchester"));
//		depots.add(new Depot("Leeds"));
//
//		Driver glyn = new Manager("GlynofLpool", "GH1234");
//		depots.get(0).makeDriver(glyn);
//
//		Driver sorren = new Manager("SorrenofMchester", "SH5678");
//		depots.get(1).makeDriver(sorren);
//
//		Driver joe = new Manager("JoeofLeeds", "J1234");
//		depots.get(2).makeDriver(joe);
//
//		// Adding all drivers.
//		Driver ben = new Driver("Ben", "1234");
//		depots.get(0).makeDriver(ben);
//
//		Driver alex = new Driver("Alex", "1234");
//		depots.get(1).makeDriver(alex);
//
//		Driver will = new Driver("Will", "1234");
//		depots.get(2).makeDriver(will);
//
//		// Adding all vehicles.
//		Vehicle astra = new Truck("1", "astra", "1", 100, 200);
//		depots.get(0).makeVehicle(astra);
//		Vehicle mini = new Truck("2", "astra", "2", 100, 200);
//		depots.get(0).makeVehicle(mini);
//
//		Vehicle kia = new Truck("3", "kia", "3", 100, 200);
//		depots.get(1).makeVehicle(kia);
//		Vehicle rangeRover = new Truck("4", "kia", "4", 100, 200);
//		depots.get(1).makeVehicle(rangeRover);
//
//		Vehicle ford = new Tanker("5", "ford", "5", 100, 200, "oil");
//		depots.get(2).makeVehicle(ford);
//		Vehicle nissian = new Tanker("6", "nissian", "6", 100, 200, "water");
//		depots.get(2).makeVehicle(nissian);

		
		for (Depot d : depots) {
			d.startCheck();
		}
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
		/*
		depot = depots.get(0);
		depot = depots.get(1);
		depot = depots.get(2);
		*/
		
		System.out.println("\nPlease select one of the following depot locations:\n");
		displayDepots();

		System.out.print("\nLocation: ");
		String location = input.nextLine();

		System.out.print("Username : ");
		String username = input.nextLine();

		System.out.print("Password : ");
		String password = input.nextLine();

		depot = getDepotByLocation(location);
		if (depot != null) {
			currentLocation = location;
			depot.setLocation(currentLocation);
			driver = depot.getDriverByName(username);
			if (driver != null) {
				if (driver.checkPassword(password)) {
					if (!Manager.class.isInstance(driver)) {
						currentUser = username;
						System.out.println("\nCorrect! Logged on as driver: " + currentUser);
						driverMenu();
					}
					if (Manager.class.isInstance(driver)) {
//						if (username.contains(location)) {
						Manager.class.cast(driver);
						currentUser = username;
						System.out.println("\nCorrect! Logged on as manager: " + currentUser);
						managerMenu();
					}

				} else {
					System.out.println("\nInvalid credentials. Please try again!");
					logOn();
				}

			}

		}
		System.out.println("\nInvalid location. Please try again!");
		logOn();
	}

	private void displayDepots() {
		System.out.println("-- DEPOTS --");

		for (Depot depot : depots) {
			System.out.println(depot.getLocation());
		}
	}

	private Depot getDepotByLocation(String location) {
		for (Depot d : depots) {
			// Works
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
				displaySchedule(currentUser);
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
				System.out.println("2 - Add Driver");
				System.out.println("3 - Add Vehicle");
				System.out.println("4 - Create Work Schedule");
				System.out.println("5 - Re-assign Vehicle");
				System.out.println("6 - View Depot Vehicles");
				System.out.println("7 - View drivers");
				System.out.println("L - Log Off");
				System.out.print("Pick : ");

				// Allow the user to specify which option on they would like to select.
				// Allow the upper case value 'Q' to be entered when exiting the program.
				choice = input.nextLine();

				// Declare a switch statement, to select one of the menu code blocks to be
				// executed.
				switch (choice) {
				case "1": {
					// displaySchedule() method executed, as a Manager is a Driver
					displaySchedule(currentUser);
					break;
				}
				case "2": {
					// addDriver() method executed, for Managers to add new Drivers to their depot
					addDriver();
					break;
				}
				case "3": {
					// addVehicle() method executed, for Managers to add new Vehicles to their depot
					addVehicle();
					break;
				}
				case "4": {
					// createSchedule() method executed, for Managers to make schedules for
					// themselves
					// or other Drivers.
					createSchedule();
					break;
				}
				case "5": {
					// reassignVehicle() method executed, for Managers to move a vehicle to another
					// Depot.
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


	private void addDriver() {

		System.out.print("Driver's username: ");
		String username = input.nextLine();

		System.out.print("Driver's password: ");
		String password = input.nextLine();

		depot.addDriver(new Driver(username, password));
	}

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
		if (vehicleType.equals("truck")) {

			System.out.print("Vehicle cargo capacity: ");
			int cargoCapacity = input.nextInt();

			depot.addVehicle(new Truck(regNo, make, model, weight, cargoCapacity));
		} else if (vehicleType.equals("tanker")) {
			System.out.print("Liquid Capacity: ");
			int liquidCapacity = input.nextInt();

			System.out.print("Vehicle liquid type: ");
			String liquidType = input.next().toLowerCase();

			depot.addVehicle(new Tanker(regNo, make, model, weight, liquidCapacity, liquidType));
		} else
			System.out.println("Incorrect Vehicle type!");
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

	private void displaySchedule(String driverUsername) throws Exception {

		List<WorkSchedule> schedules = depot.getSchedules();
		for (WorkSchedule s : schedules) {
			if (s.getDriver().getUserName().equals(driverUsername)) {
				System.out.println(s.toStringSchedule());
			}
		}
		return;
	}

	private void displayVehicles() {
		// Retrieve all vehicles in current depot
		List<Vehicle> vehicles = depot.getVehicles();
		for (Vehicle v : vehicles)
			// if the vehicle is a truck, print out specific toString
			if (Truck.class.isInstance(v)) {
				// Would like to call <TruckObject>.toString but cannot create truck object
				System.out.println(v.toTruckString());
			} else System.out.println(v.toTankerString());
	}

	private void displayDrivers() {
		List<Driver> drivers = depot.getDrivers();
		for (Driver d : drivers)
			System.out.println(d.toString());
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
			System.out.println("\n-- CREATE SCHEDULE --\n");
			
			
			System.out.print("\nClients name: ");
			String client = input.next();

			LocalDateTime startDate;
			LocalDateTime endDate;

			try {
				startDate = createLocalDateTime("start");
				endDate = createLocalDateTime("end");
				
				displayDrivers();
				System.out.print("\nDrivers name: ");
				Driver driver = depot.getDriverByName(input.next());

				System.out.print("\nVehicle Registration number: ");
				displayVehicles();
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

	private void reassignVehicle() throws Exception {
		
		LocalDateTime moveDate;
		List<WorkSchedule> schedules = depot.getSchedules();

		while (true) {
			
			System.out.println("\n-- RE-ASSIGN VEHICLE MENU --");
			
			 if (depot.getVehicles() != null) {
				 displayVehicles();
			 } else {
				 System.out.println("Im sorry, there are no current vehicles at this depot.");
			 } 
			 
			System.out.print("\nPlease enter the vehicle registration number: ");
			Vehicle vehicle = depot.getVehicleByRegNo(input.next());

			if (vehicle != null) {
				System.out.print("Vehicle selected.");
			} else {
				System.err.print("Invalid registration number.\nPlease try again...");
				continue;
			}

			try {
				moveDate = createLocalDateTime("move");
				if (moveDate != null) {
					System.out.print("\nMove date specified.\n");
				}
				displayDepots();
				System.out.print("\nPlease specify a depot: ");

				newLocation = input.next();
				Depot newDepot = getDepotByLocation(newLocation);

				if (depot != null) {
					if ((!currentLocation.equals(newLocation))) {
						newDepot.addVehicle(vehicle);
						input.nextLine();
						System.out.println(
								"\nVechice moved from " + depot.getLocation() + " to " + newDepot.getLocation() + "!");
						depot.removeVehicle(vehicle);
						break;
					}

					else {
						System.out.println("Invalid location.\nPlease try again...");
						continue;
					}

				}

			} catch (Exception e) {
				System.err.print("Date/time entry is out of bounds. Try again!");
				continue; // Manager is not kicked out to their main menu if they make a mistake
			}

			for (WorkSchedule s : schedules) {
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
}

