package system;

import java.time.LocalDateTime;

public class Truck extends Vehicle {

	private int cargoCapacity;

	public Truck(String make, String model, int weight, String regNo, int cargoCapacity) {
		super(make, model, weight, regNo);

		this.cargoCapacity = cargoCapacity;

	}

	public <T extends Vehicle> int getCargoCapacity() {
		// generic placeholder in method - remove if needed
		return cargoCapacity;
	}

	public void setCargoCapacity(int cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}

	@Override
	public boolean isAvailable(LocalDateTime startDate, LocalDateTime endDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addSchedule(WorkSchedule schedule) {
		// TODO Auto-generated method stub
		
	}

}
