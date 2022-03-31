package system;

import java.time.LocalDateTime;

public class WorkSchedule {

	// Declare an new array list 'Schedule', to store the 'schedule.txt' data.
	// private static final ArrayList<WorkSchedule> Schedule = new
	// ArrayList<WorkSchedule>();

	private String client;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Status status;

	public WorkSchedule(String client, LocalDateTime startDate, LocalDateTime endDate, Status status) {

		// Declare a this.variable to allow the current object to be called.
		this.client = client;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	// Declare a 'toStringheader' to print and format a message alongside the variable types when executed.
	public String SaveScheduleToFile() {
		return "Client Name: " + client + "    " + "Start Date: " + startDate + "    " + "End Date: " + endDate;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
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
}