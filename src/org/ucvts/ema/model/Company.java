package org.ucvts.ema.model;
import java.util.ArrayList;

import org.ucvts.ema.EMA;

public class Company {

	private static int cidCounter = 0;
	
	private int companyId;
	private String companyName;
	private ArrayList<User> employees;
	private User employer;
	private Chat chat;
	
	public Company(String name) {
		companyName = name;
		companyId = cidCounter++; //TODO
		employees = new ArrayList<User>();
		employer = null;
		this.chat = new Chat(companyId);
	}
	
	public User addEmployer(String username, String password, String firstName, String lastName) {
		employer = new User(firstName, lastName, username, password, UserGroup.EMPLOYER, companyId);
		return employer;
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
		return employees.size();  // returns employee count not including the employer
	}
	
	public User assign(String username, String firstName, String lastName) {
		User u = new User(firstName, lastName, username, UserGroup.EMPLOYEE, companyId);
		employees.add(u);
		return u;
	}
	
	public User getEmployer() {
		return employer;
	}
	
	public boolean removeEmployee(User u) {
		return employees.remove(u);
		
	}
	
	public Chat getChat() {
		return chat;
	}
	
	
	
	
}
