package system;

public abstract class Vehicle {

	protected String make;
	protected String model;
	protected int weight;
	protected String regNo;
	
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
	
	public void getSchedule() {
		
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