package system;

/**
 * System class Manager inherits from the Driver class, which extends the
 * Driver class variables and declares them as super.
 * 
 * @author Matt Bailey, Joe Macneil, Liam Clarke.
 * @version 1.0.
 */

public class Manager extends Driver {

	private static final long serialVersionUID = 1L;

	public Manager(String userName, String passWord) {
		//Declared the super with the variables extended from 'Driver'.
		super(userName, passWord);
	}

}