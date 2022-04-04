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
		// Milliseconds to seconds conversion
		delay = seconds * 1000;
	}

	public Integer getSeconds() {
		return delay / 1000;
	}

	@Override
	public void run() {
		while (true) {
			try {

				// This thread will be dormant for the delay specified in setSeconds
				// May need debugging
				// Thread.sleep(delay);

				synchronized (schedules) {
					for (WorkSchedule schedules : schedules) {

						// To check for ACTIVE status:
						if (schedules.getStartDate().equals(LocalDateTime.now())) {
							schedules.setStatus(Status.ACTIVE);
							System.out.println("The schedule\n" + schedules.toString() + "\n has been set to active");
							// To check for ARCHIVED status:
						} else if (schedules.getEndDate().equals(LocalDateTime.now())) {
							schedules.setStatus(Status.ARCHIVED);
							System.out.println("The schedule\n" + schedules.toString() + "\n has been set to archived");
						
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}