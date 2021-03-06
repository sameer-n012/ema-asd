package org.ucvts.ema.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Company {

	private static int cidCounter = 0;
	
	
	private int companyId;
	private String companyName;
	private ArrayList<User> employees;
	private User employer;
	private ArrayList<Log> logs;
	private int logCounter;
	
	public Company(String name) {
		companyName = name;
		companyId = cidCounter++;
		employees = new ArrayList<User>();
		employer = null;
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
		sortEmployees();
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
	
	public User assign(User u) {
		employees.add(u);
		sortEmployees();
		
		return u;
	}
	
	public User getEmployer() {
		return employer;
	}
	
	public boolean removeEmployee(User u) {
		return employees.remove(u);
		
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
	
	private void sortEmployees() {
		Collections.sort(employees, getCompByName());	
	}
	
	public static Comparator<User> getCompByName() {
		Comparator<User> comp = new Comparator<User>(){
	     
			@Override
			public int compare(User s1, User s2) { 
				int i = s1.getLName().toLowerCase().compareTo(s2.getLName().toLowerCase()); 
				if(i == 0) { i = s1.getFName().toLowerCase().compareTo(s2.getFName().toLowerCase()); }
				return i;
			}
			
			
		};
	 
		return comp;
	} 
	
	
	
}
