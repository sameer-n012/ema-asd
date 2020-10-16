package org.ucvts.ema.model;

public class User {
	
//	private static int uidCounter = 0;
	private static final String defaultPassword = "password";

	//TODO Will need to change some to protected
	private String firstName;
	private String lastName;
	private int cid;
//	private int uid;
	private String password;
	private String username;
	private Shift[] shifts = new Shift[7];
	private double salary;
	private UserGroup role; 

	
	public User(String firstName, String lastName, String username, UserGroup role, int companyId) {
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		password = defaultPassword; //TODO Hash this	
		cid = companyId;
//		uid = uidCounter++;
	}
	
//	public int getUID() { return uid; }
	
	public int getCID() { return cid; }
	
	public String getFName() { return firstName; }
	
	public String getLName() { return lastName; }
	
	public double getSalary() { return salary; }
	
	public UserGroup getRole() { return role; }
	
	public Shift[] getShifts() { return shifts; }
	
	public void setFName(String firstName) { this.firstName = firstName; }
	
	public void setLName(String lastName) { this.lastName = lastName; }
	
	public void setShifts(Shift[] shifts) { this.shifts = shifts; }
	
	public void setSalary(double salary) { this.salary = salary; }
	
	public void setRole(UserGroup role) { this.role = role; }
	
	public void resetPassword() { password = defaultPassword; } //TODO Hash this
	
	
}
