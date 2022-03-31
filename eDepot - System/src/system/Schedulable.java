package system;

import java.time.LocalDateTime;
import java.util.List;

public interface Schedulable {

	public boolean isAvailable(LocalDateTime startDate, LocalDateTime endDate);
	public void addSchedule(WorkSchedule schedule);
	public List<WorkSchedule>getSchedule();
}
