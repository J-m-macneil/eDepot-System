package system;

import java.util.ArrayList;

public class Depot {

	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	private ArrayList<WorkSchedule> workSchedules = new ArrayList<WorkSchedule>();
	
	public Depot() {
		
	}
	
	public void logOn(String userName, String passWord) {
		
	}
	
	public ArrayList<Vehicle> getVehicle() {
		return vehicles; // may need changing
		
	}
	
	public ArrayList<Driver> getDriver() {
		return drivers; // may need changing
		
	}
	
	public ArrayList<WorkSchedule> getWorkSchedule() {
		return workSchedules; // may need changing
	}
	
	public void setUpWorkSchedule() { // add method body
		
	}
}