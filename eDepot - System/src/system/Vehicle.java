package system;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public abstract class Vehicle implements Schedulable, Serializable {

	//Declared private variables that can be accessed outside the class, if public getter methods are present in the class.
	private static final long serialVersionUID = 1L;
	//Declared protected variables that can be accessed and used by the classes that inherit from 'Vehicle'. 
	protected String regNo;
	protected String make;
	protected String model;
	protected int weight;

	// Declare an new array list of the class'Work Schedule', to store the all schedule data.
	protected List<WorkSchedule> schedules = new LinkedList<WorkSchedule>();

	public Vehicle(String regNo, String make, String model, int weight) {

		// Declare a this.variable to allow the current object to be called.
		this.regNo = regNo;
		this.make = make;
		this.model = model;
		this.weight = weight;

	}

	//Declares a 'getSchedule' of class 'Vehicle', to allow the protected variable to be accessed by a java class.
	public List<WorkSchedule> getSchedule() {
		return schedules;

	}

	//Declares a 'getMake', to allow the protected variable to be accessed by a java class.
	public String getMake() {
		return make;
	}

	//Declare a 'setMake', to allow the protected variable to be accessed and update the value in a java class. 
	public void setMake(String make) {
		this.make = make;
	}

	//Declares a 'getModel', to allow the private variable to be accessed by a java class.
	public String getModel() {
		return model;
	}

	//Declare a 'setModel', to allow the protected variable to be accessed and update the value in a java class. 
	public void setModel(String model) {
		this.model = model;
	}

	//Declares a 'getWeight', to allow the protected variable to be accessed by a java class.
	public int getWeight() {
		return weight;
	}

	//Declare a 'setWeight', to allow the protected variable to be accessed and update the value in a java class. 
	public void setWeight(int weight) {
		this.weight = weight;
	}

	//Declares a 'getRegNo', to allow the protected variable to be accessed by a java class.
	public String getRegNo() {
		return regNo;
	}

	//Declare a 'setRegNo', to allow the protected variable to be accessed and update the value in a java class. 
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	
	// Declare a 'toStringheader' to print and format a message alongside the variable types when executed.
	public String toTruckString() {
		return "Reg No: " + regNo + " | Make: " + make + " | Model: " + model + " | Weight: " + weight;
	}

	// Declare a 'toStringheader' to print and format a message alongside the variable types when executed.
	public String toTankerString() {
		return "Reg No: " + regNo + " | Make: " + make + " | Model: " + model + " | Weight: " + weight;
	}

	@Override
	public boolean isAvailable(LocalDateTime startDate, LocalDateTime endDate) {
		for (WorkSchedule s : schedules) {
			if ((s.getStartDate().isBefore(startDate)) && (s.getEndDate().isAfter(startDate))) {
				return false;
			}
			if ((s.getStartDate().isBefore(endDate)) && (s.getEndDate().isAfter(endDate))) {
				return false;
			}
			if ((s.getStartDate().isAfter(startDate)) && (s.getEndDate().isBefore(endDate))) {
				return false;
			}
		}
		return true;
	}

	
	@Override
	public void addSchedule(WorkSchedule workSchedule) {
		schedules.add(workSchedule);

	}

}