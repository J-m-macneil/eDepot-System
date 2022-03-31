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

	// Vehicles broken down into two separate ArrayLists of its children, to enable
	// instantiation
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
		// Adding all vehicles to the serialized data - only need one of each type to
		// test
		trucks.add(new Truck("Scania", "V8 730S", 21000, "EHS26N", 13000));
		tankers.add(new Tanker("DAF", "FAN 75", 26000, "PF19TKZ", 18000, "Diesel"));
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

	public Vehicle getVehicleByMake(String make) {
		for (Truck truck : trucks) {
			if (truck.getMake().equals(make)) {
				return truck;
			}
			for (Tanker tanker : tankers) {
				if (tanker.getMake().equals(make)) {
					return tanker;
				}
			}

		}
		return null;
	}

}