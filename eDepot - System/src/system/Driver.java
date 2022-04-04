package system;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * System class Driver is an abstract class, which allows Manager to
 * inherit from it. The system class implements the system class Schedulable and
 * Serializable. Serializable allows the variable data to be serialized and deSerialized.
 * 
 * @author Matt Bailey, Joe Macneil, Liam Clarke.
 * @version 1.0.
 */
public class Driver implements Schedulable, Serializable {

	private static final long serialVersionUID = 1L;
	// Declared protected variables that can be accessed and used by the classes
	// that inherit from 'Vehicle'.
	protected String username;
	protected String password;
	protected Boolean update = false;
	Vehicle vehicle;

	// Declare an new array list of the class'Work Schedule', to store the all
	// schedule data.
	protected List<WorkSchedule> schedule = new LinkedList<WorkSchedule>();

	public Driver(String username, String passWord) {

		// Declare a this.variable to allow the current object to be called.
		this.username = username;
		this.password = passWord;

	}

	/**
	 * Declare a 'toStringheader' to print and format a message alongside the
	 * variable types when executed.
	 */
	@Override
	public String toString() {
		return "Username: " + username;
	}

	/**
	 * Declares a 'getModel', to allow the protected variable to be accessed by a
	 * java class.
	 */
	public String getUserName() {
		return username;
	}

	/**
	 * Declare a 'setUsername', to allow the protected variable to be accessed and
	 * update the value in a java class.
	 */
	public void setUserName(String userName) {
		this.username = userName;
	}

	/**
	 * Declares a 'getPassword', to allow the protected variable to be accessed by a
	 * java class.
	 */
	public String getPassWord() {
		return password;
	}

	/**
	 * Declare a 'setPassword', to allow the protected variable to be accessed and
	 * update the value in a java class.
	 * 
	 * @param passWord
	 */
	public void setPassWord(String passWord) {
		this.password = passWord;
	}

	/**
	 * Declared a 'isAvailable' method, to allow data to be checked whether the
	 * current object, such as Driver is in a current PENDING or ACTIVE schedule due
	 * to its parameters 'startDate' and 'endDate'. However, this method is not used
	 * in our program at this current point, but could be implemented in the future.
	 */
	@Override
	public boolean isAvailable(LocalDateTime startDate, LocalDateTime endDate) {
		for (WorkSchedule s : schedule) {
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
	public void addSchedule(WorkSchedule schedule) {

	}

	/**
	 * Declared a  'getSchedule' of class 'Driver', to allow the protected variable
	 * to be accessed by a java class.
	 */
	@Override
	public List<WorkSchedule> getSchedule() {
		return schedule;
	}

	/**
	 * Validate the password parameter entered against the object password data.
	 */
	public boolean checkPassword(String password) {
		if (password.equals(this.password)) {
			return true;
		}
		return false;
	}
	
	/** 
	 *  Declared a 'addDriver' method, which is called when adding a new driver with a username and password to the depot system. 
	 * @param driver
	 */
	public void addDriver(Driver driver) {
		
	}

}