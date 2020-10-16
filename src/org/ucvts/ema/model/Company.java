package org.ucvts.ema.model;
import java.util.ArrayList;

public class Company {

	private static int cidCounter = 0;
	
	private int companyId;
	private String companyName;
	private ArrayList<User> employees;
	private User employer;
	
	public Company(String name, String username, String password, String firstName, String lastName) {
		companyName = name;
		companyId = cidCounter++; //TODO
		employees = new ArrayList<User>();
		employer = new User(firstName, lastName, username, UserGroup.EMPLOYER, companyId);
	}
	
	public String getName() {
		return companyName;
	}
	
	public int getId() {
		return companyId;
	}
	
	public ArrayList<User> employeeList() {
		return employees;
	}
	
	public int employeeCount() {
		return employees.size();
	}
	
	public User assign(String name, String username, String firstName, String lastName) {
		User u = new User(firstName, lastName, username, UserGroup.EMPLOYER, companyId);
		employees.add(u);
		return u;
	}
	
	public User getEmployer() {
		return employer;
	}
	
	
	
	
	
	
	
}
