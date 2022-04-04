package system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * System class Depot adds and verifies information for platform classes Entry
 * and Sys.
 * 
 * @author Matt Bailey, Joe Macneil, Liam Clarke.
 * @version 1.0.
 */

public class Depot implements Serializable {

	private static final long serialVersionUID = 1L;

	// Collections of serialized data stored in the relative depots.ser file

	List<Driver> drivers = new ArrayList<Driver>();
	// final List<Manager> managers = new ArrayList<Manager>();
	final List<WorkSchedule> schedules = Collections.synchronizedList(new ArrayList<WorkSchedule>());
	final List<Vehicle> vehicles = new ArrayList<Vehicle>();
	private String location;

	// Vehicles broken down into two separate ArrayLists of its children, to enable
	// instantiation
	final static List<Truck> trucks = new ArrayList<Truck>();
	final static List<Tanker> tankers = new ArrayList<Tanker>();

	// Constructor creates a new depot

	public Depot(String depot) {
		this.location = depot;
	}

	/**
	 * Validates drivers credentials that wish to login to the eDepot System.
	 * 
	 * @param username of reference type String, to compare username with stored
	 *                 list of drivers.
	 * @param password of reference type String, to compare password with stored
	 *                 list of drivers.
	 * @return Validate the parameters against the object data.
	 */

	public boolean logOn(String username, String password) {
		// For loop ensures any driver at a given index in the stored data can have
		// their credentials verified
		for (int i = 0; i < drivers.size(); i++)
			if (drivers.get(i).username.equals(username) && drivers.get(i).password.equals(password)) {
				return true;
			}
		return false;
	}

	/**
	 * Declare a 'setLocation', to allow the private variable to be accessed and
	 * update the value in a java class.
	 * 
	 * @param location of reference type String to set a depot's location.
	 */

	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Declare a 'getLocation', to allow the private variable to be accessed by a
	 * java class.
	 * 
	 * @return gets existing depot location.
	 */

	public String getLocation() {
		return location;
	}

	/**
	 * Creates a work schedule and adds to the array of work schedules. Gets the
	 * current user/driver and adds the schedule to their account
	 * 
	 * @param workSchedule a variable of type WorkSchedule
	 */

	public synchronized void createSchedule(WorkSchedule workSchedule) {
		schedules.add(workSchedule);
		workSchedule.getDriver().addSchedule(workSchedule);

	}

	/**
	 * Adds a driver to the array of drivers for the current depot. Requires a
	 * variable of type Driver as argument
	 * 
	 * @param driver of type Driver
	 */
	public void makeDriver(Driver driver) {
		// ToDo : Validate ?
		drivers.add(driver);
	}

	/**
	 * Adds a vehicle to the array of vehicles for the current depot. Requires a
	 * variable of type Vehicle as argument
	 * 
	 * @param vehicle of type Vehicle
	 */
	public void makeVehicle(Vehicle vehicle) {
		// ToDo : Validate ?
		vehicles.add(vehicle);
	}

	/**
	 * Enhanced for loop used to traverse collection of drivers to return their name
	 * for use in platform application classes Entry and Sys when a value is entered
	 * on the console and compared to a local variable there.
	 * 
	 * @param name of reference type String, acts as a placeholder.
	 * @return the local variable being entered in platform class Sys or null.
	 */

	public Driver getDriverByName(String name) {
		for (Driver d : drivers) {
			if (d.getUserName().equals(name)) {
				return d;
			}
		}
		return null;
	}
	
	/**
	 * Enhanced for loop used to traverse collection of vehicles to return their name
	 * for use in platform application classes Entry and Sys when a value is entered
	 * on the console and compared to a local variable there.
	 * 
	 * @param regNo of type String, acts as a placeholder.
	 * @return the local variable being entered in platform class Sys or null.
	 */

	public Vehicle getVehicleByRegNo(String regNo) {
		for (Vehicle v : vehicles) {
			if (v.getRegNo().equals(regNo)) {
				return v;
			}
		}
		return null;
	}
	
	/**
	 * Declare a List of type vehicle,'getVehicles', to allow the private variable to be accessed by a
	 * java class.
	 * @return list of all vehicles.
	 */

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * Declare a List of type vehicle,'getTrucks', to allow the private variable to be accessed by a
	 * java class.
	 * @return list of all trucks.
	 */
	
	public List<Truck> getTrucks() {
		return trucks;
	}
	
	/**
	 * Declare a List of type vehicle,'getTankers', to allow the private variable to be accessed by a
	 * java class.
	 * @return list of all tankers.
	 */

	public List<Tanker> getTankers() {
		return tankers;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void addDriver(Driver driver) {
		drivers.add(driver);
	}

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	public void removeVehicle(Vehicle vehicle) {
		vehicles.remove(vehicle);
	}

	public List<WorkSchedule> getSchedules() {
		return schedules;
	}

	public void startCheck() {
		// ToDo : Safety ?
		new Thread(new StatusCheck(schedules, 10)).start();
	}

}