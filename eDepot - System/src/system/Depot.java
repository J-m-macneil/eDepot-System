package system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 
 * System class Depot adds and verifies information for platform classes Entry and Sys
 * @author Matt Bailey, Joe Macneil, Liam Clarke
 * @version 1.0
 */

public class Depot implements Serializable {

	
	private static final long serialVersionUID = 1L;


	List<Driver> drivers = new ArrayList<Driver>();
	// final List<Manager> managers = new ArrayList<Manager>();
	final List<WorkSchedule> schedules = Collections.synchronizedList(new ArrayList<WorkSchedule>());
	final List<Vehicle> vehicles = new ArrayList<Vehicle>();
	private String location;

	// Vehicles broken down into two separate ArrayLists of its children, to enable
	// instantiation.
	final static List<Truck> trucks = new ArrayList<Truck>();
	final static List<Tanker> tankers = new ArrayList<Tanker>();
		
		public Depot(String depot) {
		
		this.location = depot;
	}
	
	/**
	 * Validates drivers credentials that wish to login to the eDepot System.
	 * 
	 * @param username of reference type String, to compare username
	 * with stored list of drivers.
	 * @param password of reference type String, to compare password
	 * with stored list of drivers.
	 * @return  Validate the parameters against the object data.
	 */

	public boolean logOn(String username, String password) {
		// For loop ensures any driver at a given index in the stored data can have their credentials verified
		for (int i = 0; i < drivers.size(); i++)
			if (drivers.get(i).username.equals(username) && drivers.get(i).password.equals(password)) {
				return true;
			}
		return false;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public synchronized void createSchedule(WorkSchedule workSchedule) {
		schedules.add(workSchedule);
		workSchedule.getDriver().addSchedule(workSchedule);

	}
	
	public void makeDriver(Driver driver) {
		// ToDo : Validate ?
		drivers.add(driver);
	}
	
	public void makeVehicle(Vehicle vehicle) {
		// ToDo : Validate ?
		vehicles.add(vehicle);
	}
	
	/**
	 * Enhanced for loop used to traverse collection of drivers to return their name
	 * for use in platform application classes Entry and Sys when a value is entered on the
	 * console and compared to a local variable there.
	 * @param name of reference type String, acts as a placeholder.
	 * @return  the local variable being entered in platform class Sys or null.
	 */
	
	public Driver getDriverByName(String name) {
		for (Driver d : drivers) {
			if (d.getUserName().equals(name)) {
				return d;
			}
		}
		return null;
	}

	public Vehicle getVehicleByRegNo(String regNo) {
		for (Vehicle v : vehicles) {
			if (v.getRegNo().equals(regNo)) {
				return v;
			}
		}
		return null;
	}
	
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	
	public List<Truck> getTrucks() {
		return trucks;
	}
	
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