package system;

public class Driver {

	protected String username;
	protected String password;
	
	public Driver() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setSchedule() {
		
	}
	
	public boolean isAvailable() {
		return false; // needs changing
	}
	
	public boolean checkPassword() {
		return false; // needs changing
		
	}
}