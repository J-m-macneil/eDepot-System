package system;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public abstract class Vehicle implements Schedulable {

	protected String make;
	protected String model;
	protected int weight;
	protected String regNo;
	
	protected List<WorkSchedule> schedules = new LinkedList<WorkSchedule>();
	
	public Vehicle(String make, String model, int weight, String regNo) {
	
	this.make = make;
	this.model = model;
	this.weight = weight;
	this.regNo = regNo;
		
	}

	public boolean isAvailable() {
		return false; // needs changing
		
	}

	public void setSchedule() {
		
	}
	
	public List<WorkSchedule> getSchedule() {
		return schedules;
		
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
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