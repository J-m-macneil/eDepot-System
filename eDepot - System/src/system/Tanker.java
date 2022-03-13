package system;

public class Tanker extends Vehicle {

	private int liquidCapacity;
	private int liquidType;
	
	public Tanker() {
		
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