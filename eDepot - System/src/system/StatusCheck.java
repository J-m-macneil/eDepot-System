package system;

import java.time.LocalDateTime;

import java.util.List;

/**
 * System class StatusCheck checks if the WorkSchedule class has a particular
 * status by running a background thread.
 * @author Matt Bailey, Joe Macneil, Liam Clarke
 * @version 1.0
 */

public class StatusCheck implements Runnable {

	private List<WorkSchedule> schedules;
	private Integer delay;
	

	public StatusCheck(List<WorkSchedule> schedules, Integer seconds) {
		// Declare a this.variable to allow the current object to be called.
		this.schedules = schedules;
		setSeconds(seconds);
	}
	
	/**
	 * Delcare a 'setSeconds' method to allow the private variable to be accessed and update the value
	 * by a java class.
	 * @param seconds of type Integer.
	 */

	public synchronized void setSeconds(Integer seconds) {
		delay = seconds * 1000;
	}
	
	/**
	 * Delcare a 'getSeconds' method to allow the private variable to be accessed 
	 * by a java class.
	 * @param seconds of type Integer.
	 */

	public Integer getSeconds() {
		return delay / 1000;
	}
	
	/**
	 * Method run() will run the background thread called by the StartCheck() method in Depot.
	 */

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