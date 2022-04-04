package system;

import java.time.LocalDateTime;
import java.util.List;

/**
 * System interface schedulable allows system classes to formally implement it
 * and is a mechanism to achieve abstraction. There are three abstract methods,
 * with just headers in the Java interface, not the method body.
 * 
 * @author Matt Bailey, Joe Macneil, Liam Clarke.
 * @version 1.0.
 */
public interface Schedulable {

	public boolean isAvailable(LocalDateTime startDate, LocalDateTime endDate);

	public void addSchedule(WorkSchedule schedule);

	public List<WorkSchedule> getSchedule();
}
