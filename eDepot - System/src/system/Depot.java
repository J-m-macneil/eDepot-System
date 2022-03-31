package system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
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
	final static List<Vehicle> vehicles = new ArrayList<Vehicle>();

	public Depot() {
		deSerialize();
		// Adding all drivers to the serialized data
		managers.add(new Manager("GlynofLpool", "GH1234"));
		managers.add(new Manager("SorrenofMchester", "SH5678"));
		drivers.add(new Driver("Mark", "MK123"));
		drivers.add(new Driver("Kirsty", "KY456"));
		drivers.add(new Driver("Andy", "AY789"));
		//

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
		for (int i = 0; i < drivers.size(); i++)
			if (drivers.get(i).username.equals(username) && drivers.get(i).password.equals(password)) {
				return true;
			}
		return false;
	}

	public void createSchedule(WorkSchedule workSchedule) {
		schedules.add(workSchedule);

	}

	public List<Driver> getDriver() {
		return drivers;

	}

	public List<Vehicle> getVehicle() {
		return vehicles;
	}

}