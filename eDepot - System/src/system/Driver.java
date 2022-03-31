package system;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Driver implements Schedulable {

	protected String username;
	protected String password;
	protected Boolean update = true;

	protected List<WorkSchedule> schedules = new LinkedList<WorkSchedule>();

	public Driver(String username, String passWord) {

		this.username = username;
		this.password = passWord;

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

	@Override
	public List<WorkSchedule> getSchedule() {
		return schedules;
	}

}