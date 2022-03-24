package system;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Depot {

	
	public Depot() {
	
	} 

	
	public void logOn(String username, String password) {
		boolean found = false;
		String tempUsername = "";
		String tempPassword = "";
	    try {
	    	Scanner file = new Scanner(new File("Managers.txt"));
	    	Scanner input = new Scanner(System.in);
	    	file.useDelimiter("(,\n)");
	        
	        while (input.hasNext() && file.hasNext() && !found) {
	        	tempUsername = file.next();
	        	tempPassword = file.next();
	            if (tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim())) {
	               // Mainemenu menu = new Mainemenu();
	               //  menu.AdminMenu();
	               // Above code is a template to let us add different menus but for now see below
	            	System.out.println("Login Successful!");
	            	found = true;
	            	
	            } else {
	                System.out.println("--Error--! Press 'Enter' to continue...");
	                input.nextLine();
	            }
	            input.close();
            	file.close();
	        }
	    } catch (IOException e) {
	        e.getCause();
	    }
	    
	}

	public void setUpWorkSchedule() { // add method body
		
	}
}