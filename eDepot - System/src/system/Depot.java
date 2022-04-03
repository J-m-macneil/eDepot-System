package system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		//deSerialize();
		
	// Adding all mangers to the serialized data.
//		drivers.add(new Manager("GlynofLpool", "GH1234"));
//		drivers.add(new Manager("SorrenofMchester", "SH5678"));
//		drivers.add(new Manager("JoeofLeeds", "J1234"));
			
		
		
//			Driver ben = new Driver("Ben", "1234");
//			depots.get(0).makeSchedule(ben);
//			
//			Driver alex = new Driver("Alex", "1234");
//			depots.get(1).makeSchedule(alex);
//			
//			Driver will = new Driver("Will", "1234");
//			depots.get(2).makeSchedule(will);
		
//		// Adding all drivers to the serialized data.
//		drivers.add(new Driver("Mark", "MK123"));
//		drivers.add(new Driver("Kirsty", "KY456"));
//		drivers.add(new Driver("Andy", "AY789"));
//
//		// Adding all vehicles to the serialized data - only need one of each type to
//		// test.
//		vehicles.add(new Truck("1234", "astra", "1", 100, 200));
//		vehicles.add(new Tanker("2345", "ford", "2", 100, 200, "oil"));
//		vehicles.add(new Tanker("3456", "kia", "3", 100, 200, "petrol")); 
		

		this.location = depot;
	}

	private void deSerialize() {
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(new FileInputStream("./depots.ser"));

			drivers = (List<Driver>) ois.readObject();
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

/*	public boolean logOnAsManager(String username, String password) {
		for (int i = 0; i < managers.size(); i++)
			if (managers.get(i).username.equals(username) && managers.get(i).password.equals(password)) {
				return true;
			}
		return false;
	} */
	
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
	
//	// NOTE : Safety Necessary ?
//		public synchronized void makeBooking(Booking booking) {
//			// ToDo : Validate ?
//			bookings.add(booking);
//
//			// ToDo Two-Way Linking ?
//			booking.getPet().makeBooking(booking);
//		}
	
	public void makeDriver(Driver driver) {
		// ToDo : Validate ?
		drivers.add(driver);
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

	public void addDriver(Driver driver) {
		drivers.add(driver);
	}

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	public List<WorkSchedule> getSchedules() {
		return schedules;
	}
	
	public void startCheck() {
		// ToDo : Safety ?
		new Thread(new StatusCheck(schedules, 30)).start();
	}

}