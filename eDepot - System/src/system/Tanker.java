package system;

public class Tanker extends Vehicle {

	//Declared private variables that can be accessed outside the class, if public getter methods are present in the class.
	private int liquidCapacity;
	private String liquidType;

	public Tanker(String regNo, String make, String model, int weight, int liquidCapacity, String liquidType) {
		super(regNo, make, model, weight);

		// Declare a this.variable to allow the current object to be called.
		this.liquidCapacity = liquidCapacity;
		this.liquidType = liquidType;

	}

	//Declares a 'getLiquidCapacity' of class 'Tanker', to allow the private variable to be accessed by a java class.
	public <T extends Vehicle> int getLiquidCapacity() {
		return liquidCapacity;
	}

	//Declare a 'setLiquidCapacity', to allow the private variable to be accessed and update the value in a java class. 
	public void setLiquidCapacity(int liquidCapacity) {
		this.liquidCapacity = liquidCapacity;
	}

	//Declares a 'getLiquidType' of class 'Tanker', to allow the private variable to be accessed by a java class.
	public <T extends Vehicle > String getLiquidType() {
		return liquidType;
	}

	//Declare a 'setLiquidType', to allow the private variable to be accessed and update the value in a java class. 
	public void setLiquidType(String liquidType) {
		this.liquidType = liquidType;
	}

}