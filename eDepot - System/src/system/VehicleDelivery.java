package system;

public class VehicleDelivery implements Runnable{
	
	// Declared private variables that can be accessed and used by the class, if public getter methods are present in the class.
	private Vehicle vehicle;
	private Depot oldDepot;
	private Depot newDepot;
	private Integer delay;
	
	public VehicleDelivery(Vehicle vehicle, Depot oldDepot, Depot newDepot, Integer seconds) {
		
		// Declare a this.variable to allow the current object to be called.
		this.vehicle = vehicle;
		this.oldDepot = oldDepot;
		this.newDepot = newDepot;
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
				oldDepot.removeVehicle(vehicle);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
