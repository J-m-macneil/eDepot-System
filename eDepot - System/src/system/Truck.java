package system;

public class Truck extends Vehicle {

	private int cargoCapacity;
	
	public Truck() {
		
	}

	public <T extends Vehicle> int getCargoCapacity() {
		return cargoCapacity;
	}

	public void setCargoCapacity(int cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}
	
	
}
