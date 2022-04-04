package system;

/**
 * System class VehicleDelivery checks if a Vehicle has been delivered to
 * another depot by awakening a thread when the Manager enters in the valid credentials
 * in platform classes Entry and Sys on the console.
 * @author Matt Bailey, Joe Macneil, Liam Clarke
 * @version 1.0
 */

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
	
	/**
	 * Delcare a 'setSeconds' method to allow the private variable to be accessed and update the value
	 * by a java class.
	 * @param seconds of type Integer.
	 */
	
	public synchronized void setSeconds(Integer seconds) {
		// Milliseconds to seconds conversion
		delay = seconds * 1000;
	}
	
	/**
	 * Method run() will run the awakened thread called by the reassignVehicle() method in Sys.
	 */
	
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
