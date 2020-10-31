package org.ucvts.ema.model;

import org.ucvts.ema.app.Password;

public class User {
	
	private static final String defaultPassword = "password";

	private String firstName;
	private String lastName;
	private int cid;
	private String passwordHash;
	private byte[] salt;
	private String username;
	private Shift[] shifts = new Shift[7];
	private double salary;
	private UserGroup role; 
	private String notes;

	
	public User(String firstName, String lastName, String username, UserGroup role, int companyId) {
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		salt = Password.getSalt();
		passwordHash = Password.hash(defaultPassword, salt);	
		notes = "";
		for(int i = 0; i < shifts.length; i++) {
			shifts[i] = Shift.NONE;
		}
		
		cid = companyId;
//		uid = uidCounter++;
	}
	
	public User(String firstName, String lastName, String username, String password, UserGroup role, int companyId) {
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		salt = Password.getSalt();
		passwordHash = Password.hash(password, salt);	
		notes = "";
		for(int i = 0; i < shifts.length; i++) {
			shifts[i] = Shift.NONE;
		}
		
		cid = companyId;
//		uid = uidCounter++;
	}
	
	public User(String firstName, String lastName, String username, UserGroup role, Shift[] shifts, int companyId) {
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		salt = Password.getSalt();
		passwordHash = Password.hash(defaultPassword, salt);	
		notes = "";
		this.shifts = shifts;
		
		cid = companyId;
	}
	
//	public int getUID() { return uid; }
	
	public int getCID() { return cid; }
	
	public String getFName() { return firstName; }
	
	public String getLName() { return lastName; }
	
	public double getSalary() { return salary; }
	
	public UserGroup getRole() { return role; }
	
	public Shift[] getShifts() { return shifts; }
	
	public byte[] getSalt() { return salt; }
	
	public String getNotes() { return notes; }
	
	public String getPasswordHash() { return passwordHash; }
	
	public String getUsername() { return username; }
	
	public void setFName(String firstName) { this.firstName = firstName; }
	
	public void setLName(String lastName) { this.lastName = lastName; }
	
	public void setShifts(Shift[] shifts) { this.shifts = shifts; }
	
	public void setSalary(double salary) { this.salary = salary; }
	
	public void setRole(UserGroup role) { this.role = role; }
	
	public void setUsername(String username) { this.username = username; }
	
	public void setNotes(String notes) { this.notes = notes; }
	
	public void setPasswordHash(String password) { 
		this.salt = Password.getSalt();
		this.passwordHash = Password.hash(password, this.salt);
	}
	
	public void resetPassword() {
		this.setPasswordHash(defaultPassword);
	}
	
	
	
}
