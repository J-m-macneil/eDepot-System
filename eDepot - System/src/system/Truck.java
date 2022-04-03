package system;

public class Truck extends Vehicle {

	private int cargoCapacity;

	public Truck(String regNo, String make, String model, int weight, int cargoCapacity) {
		super(regNo, make, model, weight);

		this.cargoCapacity = cargoCapacity;

	}

	public <T extends Vehicle> int getCargoCapacity() {
		return cargoCapacity;
	}

	public void setCargoCapacity(int cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}
	@Override
	public String toString() {
		return "Reg No: " + regNo + " | Make: " + make + " | Model: " + model + " | Weight: " + weight + " | Cargo Capacity: " + cargoCapacity;
	}

}
