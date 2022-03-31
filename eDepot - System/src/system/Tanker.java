package system;

public class Tanker extends Vehicle {

	private int liquidCapacity;
	private int liquidType;

	public Tanker(String make, String model, int weight, String regNo, int liquidCapacity, int liquidType) {
		super(make, model, weight, regNo);

		this.liquidCapacity = liquidCapacity;
		this.liquidType = liquidType;

	}

	public int getLiquidCapacity() {
		return liquidCapacity;
	}

	public void setLiquidCapacity(int liquidCapacity) {
		this.liquidCapacity = liquidCapacity;
	}

	public int getLiquidType() {
		return liquidType;
	}

	public void setLiquidType(int liquidType) {
		this.liquidType = liquidType;
	}

}