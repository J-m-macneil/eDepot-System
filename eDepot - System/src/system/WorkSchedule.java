package system;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WorkSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Declare an new array list 'Schedule', to store the 'schedule.txt' data.
	// private static final ArrayList<WorkSchedule> Schedule = new
	// ArrayList<WorkSchedule>();

	private String client;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Driver driver;
	private Vehicle vehicle;
	private Status status = Status.PENDING;

	public WorkSchedule(String client, LocalDateTime startDate, LocalDateTime endDate, Driver driver, Vehicle vehicle) {

		// Declare a this.variable to allow the current object to be called.
		this.client = client;
		this.startDate = startDate;
		this.endDate = endDate;

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

	// Declare a 'toStringheader' to print and format a message alongside the
	// variable types when executed.
	public String toStringSchedule() {
		return "\nClient Name: " + client + "    " + "Start Date: " + startDate + "    " + "End Date: " + endDate + " "
				+ " Driver: " + driver.username + " " + "Vehicle: " + vehicle.regNo;
	}
	
	public Driver getDriver() {
		return driver;
	}

	public String getClient() {
		return client;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;

	}

	public void activate() {
		this.status = Status.ACTIVE;
	}

	public void archive() {
		this.status = Status.ARCHIVED;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
}