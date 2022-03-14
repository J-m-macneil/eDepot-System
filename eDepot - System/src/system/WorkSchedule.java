package system;

import java.time.LocalDate;


public class WorkSchedule {
	
	// Declare an new array list 'Schedule', to store the 'schedule.txt' data.
	// private static final ArrayList<WorkSchedule> Schedule = new ArrayList<WorkSchedule>();  

	private String client;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public WorkSchedule() {
		
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