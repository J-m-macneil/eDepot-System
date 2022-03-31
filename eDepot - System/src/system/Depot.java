package system;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Depot {

	private final String PATH = "";

	final static Scanner input = new Scanner(System.in);

	final static List<Driver> drivers = new ArrayList<Driver>();
	final static List<Manager> managers = new ArrayList<Manager>();
	final static List<WorkSchedule> schedules = Collections.synchronizedList(new ArrayList<WorkSchedule>());
	static List<Depot> depots = new ArrayList<Depot>();
	
	// Vehicles broken down into two separate ArrayLists of its children, to enable instantiation 
	final static List<Truck> trucks = new ArrayList<Truck>();
	final static List<Tanker> tankers = new ArrayList<Tanker>();

	public Depot() {
		deSerialize();
		// Adding all drivers to the serialized data
		managers.add(new Manager("GlynofLpool", "GH1234"));
		managers.add(new Manager("SorrenofMchester", "SH5678"));
		drivers.add(new Driver("Mark", "MK123"));
		drivers.add(new Driver("Kirsty", "KY456"));
		drivers.add(new Driver("Andy", "AY789"));
		// Adding all vehicles to the serialized data - only need one of each type to test
		trucks.add(new Truck("Scania", "", 2, "", 4));
		tankers.add(new Tanker("","",2,"",4, ""));
	}

	private void deSerialize() {
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(new FileInputStream(PATH + "drivers.ser"));

			depots = (List<Depot>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean logOn(String username, String password) {
		// Moving through all driver details
		for (int i = 0; i < drivers.size(); i++) {
			if (drivers.get(i).username.equals(username) && drivers.get(i).password.equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean logOnAsManager(String username, String password) {
		for (int i = 0; i < managers.size(); i++) {
			if (managers.get(i).username.equals(username) && managers.get(i).password.equals(password)) {
				return true;
			}
		}
		return false;
	}

	public void createSchedule(WorkSchedule workSchedule) {
		schedules.add(workSchedule);

	}

	public List<Driver> getDriver() {
		return drivers;

	}

	public List<Truck> getTruck() {
		return trucks;
	}
	
	public List<Tanker> getTanker() {
		return tankers;
	}

}