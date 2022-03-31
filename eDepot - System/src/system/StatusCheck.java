package system;

import java.time.LocalDateTime;
import java.util.List;

public class StatusCheck implements Runnable {

	private List<WorkSchedule> schedules;
	private Integer delay;

	public StatusCheck(List<WorkSchedule> schedules, Integer seconds) {
		this.schedules = schedules;
		setSeconds(seconds);
	}

	public synchronized void setSeconds(Integer seconds) {
		delay = seconds * 60;
	}

	public Integer getSeconds() {
		return delay / 60;
	}

	@Override
	public void run() {
		while (true) {
			try {

				// This thread will be dormant for the delay specified in setSeconds
				// May need debugging
				Thread.sleep(delay);

				synchronized (schedules) {
					for (WorkSchedule schedules : schedules) {
						// To check for PENDING status:
						if (schedules.getStartDate().minusDays(1).isBefore(LocalDateTime.now())) {
							schedules.setStatus(Status.PENDING);
							// To check for ACTIVE status:
						} else if (schedules.getStartDate().equals(LocalDateTime.now())) {
							schedules.setStatus(Status.ACTIVE);
							// To check for ARCHIVED status:
						} else if (schedules.getEndDate().equals(LocalDateTime.now())) {
							schedules.setStatus(Status.ARCHIVED);
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}