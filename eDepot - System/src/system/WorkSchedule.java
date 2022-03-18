package system;

import java.time.LocalDate;

public class WorkSchedule {

	// Declare an new array list 'Schedule', to store the 'schedule.txt' data.
	// private static final ArrayList<WorkSchedule> Schedule = new
	// ArrayList<WorkSchedule>();

	private String client;
	private LocalDate startDate;
	private LocalDate endDate;

	public WorkSchedule(String client, LocalDate startDate, LocalDate endDate) {

		// Declare a this.variable to allow the current object to be called.
		this.client = client;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	// Declare a 'toStringheader' to print and format a message alongside the
	// variable types when executed.
	public String toString() {
		return "Client Name: " + client + "    " + "Start Date: " + startDate + "    " + "End Date: " + endDate;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}