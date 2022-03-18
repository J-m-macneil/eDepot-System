package system;

public class Truck extends Vehicle {

	private int cargoCapacity;
	
	public Truck(String make, String model, int weight, String regNo, int cargoCapacity) {
		super(make, model, weight, regNo);
		
	this.cargoCapacity = cargoCapacity;
	
	}

	public <T extends Vehicle> int getCargoCapacity() {
		return cargoCapacity;
	}

	public void setCargoCapacity(int cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}
	
	
}
