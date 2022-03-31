package system;

import java.util.LinkedList;
import java.util.List;

public abstract class Vehicle implements Schedulable {

	protected String make;
	protected String model;
	protected int weight;
	protected String regNo;
	
	protected List<WorkSchedule> schedule = new LinkedList<WorkSchedule>();
	
	public Vehicle(String make, String model, int weight, String regNo) {
	
	this.make = make;
	this.model = model;
	this.weight = weight;
	this.regNo = regNo;
		
	}

	public boolean isAvailable() {
		return false; // needs changing
		
	}

	public void setSchedule() {
		
	}
	
	public List<WorkSchedule> getSchedule() {
		return schedule;
		
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

}