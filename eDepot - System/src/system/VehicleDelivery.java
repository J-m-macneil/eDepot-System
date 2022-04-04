package system;

import java.time.LocalDateTime;

public class VehicleDelivery implements Runnable{
	
	private Vehicle vehicle;
	private Depot oldDepot;
	private Depot newDepot;
	private LocalDateTime moveDate;
	private Integer delay;
	
	public VehicleDelivery(Vehicle vehicle, Depot oldDepot, Depot newDepot, LocalDateTime moveDate, Integer seconds) {
		this.vehicle = vehicle;
		this.oldDepot = oldDepot;
		this.newDepot = newDepot;
		this.moveDate = moveDate;
		setSeconds(seconds);
	}
	
	public synchronized void setSeconds(Integer seconds) {
		// Milliseconds to seconds conversion
		delay = seconds * 1000;
	}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(delay);
			
			synchronized(vehicle) {
				newDepot.addVehicle(vehicle);
				// input.nextLine();
				System.out.println("\nVechice moved from " + oldDepot.getLocation() + " to " + newDepot.getLocation() + "!");
				System.out.println("\nVehicle moved on " + moveDate);
				oldDepot.removeVehicle(vehicle);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
