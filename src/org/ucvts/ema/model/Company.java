package org.ucvts.ema.model;

import java.util.ArrayList;

public class Company {

	private static int cidCounter = 0;
	
	
	private int companyId;
	private String companyName;
	private ArrayList<User> employees;
	private User employer;
	private Chat chat;
	private ArrayList<Log> logs;
	private int logCounter;
	
	public Company(String name) {
		companyName = name;
		companyId = cidCounter++;
		employees = new ArrayList<User>();
		employer = null;
		this.chat = new Chat(companyId);
		logs = new ArrayList<Log>();
		logCounter = 0;
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
	
	public ArrayList<User> getEmployeeList() {
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
	
	public ArrayList<Log> getLogs() {
		return logs;
	}
	
	public ArrayList<Log> addLog(Log l) {
		logs.add(l);
		l.setID(logCounter++);
		l.setCID(this.companyId);
		return logs;
	}
	
	
	public boolean removeLog(Log l) {
		for(int i = 0; i < logs.size(); i++) {
			if(l == logs.get(i)) {
				logs.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public Log getLog(int logID) {
		for(int i = 0; i < logs.size(); i++) {
			Log l = logs.get(i);
			if(logID == l.getID()) {
				return l;
			}
		}
		return null;
	}
	
	
	
}
