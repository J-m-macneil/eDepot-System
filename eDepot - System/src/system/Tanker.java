package system;

public class Tanker extends Vehicle {

	private int liquidCapacity;
	private String liquidType;

	public Tanker(String regNo, String make, String model, int weight, int liquidCapacity, String liquidType) {
		super(regNo, make, model, weight);

		this.liquidCapacity = liquidCapacity;
		this.liquidType = liquidType;

	}

	public <T extends Vehicle> int getLiquidCapacity() {
		return liquidCapacity;
	}

	public void setLiquidCapacity(int liquidCapacity) {
		this.liquidCapacity = liquidCapacity;
	}

	public <T extends Vehicle > String getLiquidType() {
		return liquidType;
	}

	public void setLiquidType(String liquidType) {
		this.liquidType = liquidType;
	}

}