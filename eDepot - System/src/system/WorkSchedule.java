package system;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * System class Work Schedule to store schedule specific variables.
 */

public class WorkSchedule implements Serializable {

	
	private static final long serialVersionUID = 1L;

	//Declared private variables that can be accessed outside the class, if public getter methods are present in the class.
	private String client;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Driver driver;
	private Vehicle vehicle;
	//Declared the status variable to be set as pending on default. 
	private Status status = Status.PENDING;

	
	public WorkSchedule(String client, LocalDateTime startDate, LocalDateTime endDate, Driver driver, Vehicle vehicle, Status status) {

		// Declare a this.variable to allow the current object to be called.
		this.client = client;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;

		setVehicle(vehicle);
		setDriver(driver);
	}

	public WorkSchedule(String client, LocalDateTime startDate, LocalDateTime endDate, Vehicle vehicle)
			throws Exception {

		// Declare a this.variable to allow the current object to be called.
		this.client = client;
		this.startDate = startDate;
		this.endDate = endDate;

		setVehicle(vehicle);
	}

	public WorkSchedule(String client, LocalDateTime startDate, LocalDateTime endDate, Driver driver) throws Exception {

		// Declare a this.variable to allow the current object to be called.
		this.client = client;
		this.startDate = startDate;
		this.endDate = endDate;

		setDriver(driver);
	}

	public WorkSchedule(String client, LocalDateTime startDate, LocalDateTime endDate) throws Exception {

		// Declare a this.variable to allow the current object to be called.
		this.client = client;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	// Declare a 'toStringheader' to print and format a message alongside the variable types when executed.
	@Override
	public String toString() {
		return "\nClient Name: " + client + "    " + "Start Date: " + startDate + "    " + "End Date: " + endDate + " "
				+ " Driver: " + driver.username + " " + "Vehicle: " + vehicle.regNo;
	}
	
	//Declares a Driver 'getDriver', to allow the driver class and private variable to be accessed by a java class.
	public Driver getDriver() {
		return driver;
	}

	//Declares a 'getClient', to allow the private variable to be accessed by a java class.
	public String getClient() {
		return client;
	}

	//Declares a 'getStartDate', to allow the private variable to be accessed by a java class.
	public LocalDateTime getStartDate() {
		return startDate;
	}

	//Declare a 'setStartDate', to allow the private variable to be accessed and update the value in a java class. 
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	//Declares a 'getEndDate', to allow the private variable to be accessed by a java class.
	public LocalDateTime getEndDate() {
		return endDate;
	}

	//Declare a 'setEndDate', to allow the private variable to be accessed and update the value in a java class. 
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	//Declares a 'getStatus', to allow the private variable to be accessed by a java class.
	public Status getStatus() {
		return status;
	}

	//Declare a 'setStatus', to allow the private variable to be accessed and update the value in a java class. 
	public void setStatus(Status status) {
		this.status = status;

	}

	//Declare a 'activate', to allow the private variable to be accessed and update the value in a java class. 
	public void activate() {
		this.status = Status.ACTIVE;
	}

	//Declare a 'archive', to allow the private variable to be accessed and update the value in a java class. 
	public void archive() {
		this.status = Status.ARCHIVED;
	}

	//Declare a 'setVehicle', to allow the private variable to be accessed and update the value in a java class. 
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	//Declare a 'setDriver', to allow the private variable to be accessed and update the value in a java class. 
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
}