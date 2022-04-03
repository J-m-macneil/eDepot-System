package system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Depot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String PATH = "";

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

	public boolean logOn(String username, String password) {
		// Moving through all driver details
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