package ems.app;

import java.util.HashMap;
import javax.swing.SwingUtilities;



public class EMA {
	
	public static HashMap<String, User> userDirectory = null;
	public static HashMap<Integer, Company> companyDirectory = null;
	
	public static void main(String[] args) { //TODO
		
		userDirectory = new HashMap<String, User>();
		companyDirectory = new HashMap<Integer, Company>();
		
		SwingUtilities.invokeLater(new Runnable() {
			
            public void run() {
                try { new EMA().initialize(); } 
                catch (Exception e) { e.printStackTrace(); }
            }
            
        });
		
		
	}
	
    private void initialize() {
    	
    }
	
	public boolean checkDuplicates(String s) { //TODO
		return userDirectory.get(s)==null ? false : true;
	}
	
	public Company getCompany(Integer i) {
		return companyDirectory.get(i);
	}
	
	public User getUser(String s) {
		return userDirectory.get(s);
	}
	
	public void addCompany(Integer i, Company c) {
		companyDirectory.put(i, c);
	}
	
	public void addUser(String s, User u) {
		userDirectory.put(s, u);
	}

}
