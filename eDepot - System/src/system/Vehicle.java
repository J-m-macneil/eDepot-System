package system;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * System class Vehicle is an abstract class, which allows Truck and Tanker to
 * inherit from it. The system class implements the system class Schedulable and
 * Serializable. Serializable allows the variable data to be serialized and deSerialized.
 * 
 * @author Matt Bailey, Joe Macneil, Liam Clarke.
 * @version 1.0.
 */
public abstract class Vehicle implements Schedulable, Serializable {

	// Declared private variables that can be accessed outside the class, if public
	// getter methods are present in the class.
	private static final long serialVersionUID = 1L;
	// Declared protected variables that can be accessed and used by the classes
	// that inherit from 'Vehicle'.
	protected String regNo;
	protected String make;
	protected String model;
	protected int weight;

	// Declare an new array list of the class 'Work Schedule', to store the all
	// schedule data.
	protected List<WorkSchedule> schedules = new LinkedList<WorkSchedule>();

	public Vehicle(String regNo, String make, String model, int weight) {

		// Declare a this.variable to allow the current object to be called.
		this.regNo = regNo;
		this.make = make;
		this.model = model;
		this.weight = weight;

	}

	/**
	 * Declares a 'getSchedule' of class 'Vehicle', to allow the protected variable
	 * to be accessed by a java class.
	 */
	public List<WorkSchedule> getSchedule() {
		return schedules;

	}

	/**
	 * Declares a 'getMake', to allow the protected variable to be accessed by a
	 * java class.
	 * @return Gets vehicle make.
	 */
	public String getMake() {
		return make;
	}

	/**
	 * Declare a 'setMake', to allow the protected variable to be accessed and
	 * update the value in a java class.
	 * 
	 * @param make Sets vehicle make.
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * Declares a 'getModel', to allow the private variable to be accessed by a java
	 * class.
	 * 
	 * @return Gets vehicle model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Declare a 'setModel', to allow the protected variable to be accessed and
	 * update the value in a java class.
	 * 
	 * @param model Sets vehicle model.
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Declares a 'getWeight', to allow the protected variable to be accessed by a
	 * java class.
	 * 
	 * @return Gets vehicle weight.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Declare a 'setWeight', to allow the protected variable to be accessed and
	 * update the value in a java class.
	 * 
	 * @param weight Sets vehicle weight.
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Declares a 'getRegNo', to allow the protected variable to be accessed by a
	 * java class.
	 * 
	 * @return regNo Gets vehicle registration number.
	 */
	public String getRegNo() {
		return regNo;
	}

	/**
	 * Declare a 'setRegNo', to allow the protected variable to be accessed and
	 * update the value in a java class.
	 * 
	 * @param regNo Sets registration number.
	 */
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	/**
	 * Declared a 'isAvailable' method, to allow data to be checked whether the
	 * current object, such as Vehicle or Driver is in a current PENDING or ACTIVE
	 * schedule due to its parameters 'startDate' and 'endDate'. However, this
	 * method is not used in our program at this current point, but could be
	 * implemented in the future.
	 */

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

	/**
	 * Declared a 'addSchedule' method, to allow the parameter 'workSchedule' to be
	 * added to the schedules array list.
	 */
	@Override
	public void addSchedule(WorkSchedule workSchedule) {
		schedules.add(workSchedule);

	}

}