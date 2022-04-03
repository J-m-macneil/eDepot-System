package system;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Driver implements Schedulable, Serializable {

	private static final long serialVersionUID = 1L;
	protected String username;
	protected String password;
	protected Boolean update = false;

	protected List<WorkSchedule> schedule = new LinkedList<WorkSchedule>();
	Vehicle vehicle;

	public Driver(String username, String passWord) {

		this.username = username;
		this.password = passWord;

	}
	@Override
	public String toString() {
		return "Username: " + username;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassWord() {
		return password;
	}

	public void setPassWord(String passWord) {
		this.password = passWord;
	}

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

	@Override
	public void addSchedule(WorkSchedule schedule) {

	}

	@Override
	public List<WorkSchedule> getSchedule() {

		return schedule;
	}

	public boolean checkPassword(String password) {
		if (password.equals(this.password)) {
			return true;
		}
		return false;
	}

	public void addDriver(Driver driver) {

	}

}