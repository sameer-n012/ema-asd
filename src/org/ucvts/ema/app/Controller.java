package org.ucvts.ema.app;
import java.awt.CardLayout;
import java.awt.Container;
import java.util.Date;

import org.ucvts.ema.EMA;
import org.ucvts.ema.model.Company;
import org.ucvts.ema.model.Log;
import org.ucvts.ema.model.Shift;
import org.ucvts.ema.model.User;
import org.ucvts.ema.model.UserGroup;
import org.ucvts.ema.views.AddLogView;
import org.ucvts.ema.views.EmployeeView;
import org.ucvts.ema.views.EmployerView;
import org.ucvts.ema.views.LogView;
import org.ucvts.ema.views.LoginView;
import org.ucvts.ema.views.ModifyView;


public class Controller {

    private Container views;
    
    private User currentUser;
    private User modifiedUser;
    private Company currentCompany;
    private Log currentLog;
    
    private EMA ema = null;

    public Controller(Container views) {
        this.views = views;
        this.currentUser = null;
        this.currentCompany = null;
        this.modifiedUser = null;
        this.currentLog = null;
        ema = EMA.getInstance();
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public Company getCurrentCompany() {
    	return currentCompany;
    }
    
    public User getModifiedUser() {
    	return modifiedUser;
    }
    
    public Log getCurrentLog() {
    	return currentLog;
    }

    public void switchView(String view) {
    	
        
        if(view.equals("LOGIN_VIEW")) { 
        	LoginView v = (LoginView) views.getComponents()[ema.LOGIN_VIEW_INDEX];
        	v.updateCard(); 
    	}
        if(view.equals("EMPLOYEE_VIEW")) { 
        	EmployeeView v = (EmployeeView) views.getComponents()[ema.EMPLOYEE_VIEW_INDEX];
        	v.updateCard(); 
    	}
        if(view.equals("EMPLOYER_VIEW")) { 
        	EmployerView v = (EmployerView) views.getComponents()[ema.EMPLOYER_VIEW_INDEX];
        	v.updateCard(); 
    	}
        if(view.equals("MODIFY_VIEW")) { 
        	ModifyView v = (ModifyView) views.getComponents()[ema.MODIFY_VIEW_INDEX];
        	v.updateCard(); 
    	}
        if(view.equals("LOG_VIEW")) {
        	LogView v = (LogView) views.getComponents()[ema.LOG_VIEW_INDEX];
        	v.updateCard(); 
    	}
        if(view.equals("ADD_LOG_VIEW")) { 
        	AddLogView v = (AddLogView) views.getComponents()[ema.ADD_LOG_VIEW_INDEX];
        	v.updateCard(); 
    	}
       
        ((CardLayout) views.getLayout()).show(views, view);
        
    }

    
    public void login(String username, String password) {
	   LoginView lv = (LoginView) views.getComponents()[ema.LOGIN_VIEW_INDEX];
	   
	   try {
		   if(ema.existsUser(username)) {
			   User u = ema.getUser(username);
			   if(Password.checkPassword(password, u.getPasswordHash(), u.getSalt())) {
				   this.currentUser = u;
				   this.currentCompany = ema.getCompany(u.getCID());
				   this.modifiedUser = u;
				   lv.showErrorMessage("Successful login", true);
				   //try { u.printUser(); } catch(Exception e) {System.out.println("Null User"); }
				   if(u.getRole() == UserGroup.EMPLOYER) { switchView(ema.EMPLOYER_VIEW); }
				   else if(u.getRole() == UserGroup.EMPLOYEE) { switchView(ema.EMPLOYEE_VIEW); }
			   }
			   else {
				   lv.showErrorMessage("Invalid password", true);
			   }
			   
		   }
		   else {
			   lv.showErrorMessage("Username does not exist.", true);
		   }
	   }
	   catch(Exception e) { e.printStackTrace(); }
    }
    
    public void createCompany(String username, String password, String companyName) {
    	LoginView lv = (LoginView) views.getComponents()[ema.LOGIN_VIEW_INDEX];
    	
    	try {
 		   if(ema.existsUser(username)) {
 			   lv.showErrorMessage("Username already exists.", true);
 		   }
 		   else if(ema.existsCompany(companyName)){
 			   lv.showErrorMessage("Company name already exists.", true);
 		   }
 		   else if(username == null || username.equals("") || password == null || password.equals("") || companyName == null || companyName.equals("")) {
 			   lv.showErrorMessage("Credentials cannot be empty.", true);
 		   }
 		   else {
 			   Company c = new Company(companyName);
 			   ema.addCompany(c);
 			   User u = c.addEmployer(username, password, "First", "Last");
 			   ema.addUser(u);
 			   this.currentCompany = c;
 			   this.currentUser = u;
 			   lv.showErrorMessage("Successful creation", true);
 			   switchView(ema.EMPLOYER_VIEW);
 		   }
 	   }
 	   catch(Exception e) { e.printStackTrace(); }
    }

    public void modifyAddEmployee(User u) {
    	//try { u.printUser(); } catch(Exception e) {System.out.println("Null User"); }
    	this.modifiedUser = u;
    	switchView(ema.MODIFY_VIEW);
    }
    
    public void updateProfileInformation(boolean resetPass, Shift[] shifts, double salary, String notes) {

    	ModifyView mv = (ModifyView) views.getComponents()[ema.MODIFY_VIEW_INDEX];
    	EmployeeView ev = (EmployeeView) views.getComponents()[ema.EMPLOYEE_VIEW_INDEX];
    	
		if(currentUser.getRole() == UserGroup.EMPLOYER) {
			if(salary < 0) {
				mv.showErrorMessage("Invalid values.", true);
				ev.showErrorMessage("Invalid values.", true);
			}
			
			else {
				if(resetPass == true) { modifiedUser.resetPassword(); }
				modifiedUser.setShifts(shifts);
				modifiedUser.setSalary(salary);
				modifiedUser.setNotes(notes);
				modifiedUser = null;
				switchView(ema.LOGIN_VIEW);
	    		switchView(ema.EMPLOYER_VIEW);
			}
			
		}
		
    }
    
    public void updateProfileInformation(String fname, String lname, String password, String notes, Shift[] shifts, double salary) {
    	ModifyView mv = (ModifyView) views.getComponents()[ema.MODIFY_VIEW_INDEX];
    	EmployeeView ev = (EmployeeView) views.getComponents()[ema.EMPLOYEE_VIEW_INDEX];
    	if(password == null || password.equals("")) {
    		mv.showErrorMessage("Credentials cannot be empty.", true);
    		ev.showErrorMessage("Credentials cannot be empty.", true);
    	}
    	else {
    		if(shifts.length == 7) { modifiedUser.setShifts(shifts); }
    		if(salary >= 0) { modifiedUser.setSalary(salary); }
	    	if(currentUser == modifiedUser) {
	    		if(!password.equals("******")) {
	    			modifiedUser.setPasswordHash(password);
	    		}
				modifiedUser.setFName(fname);
				modifiedUser.setLName(lname);
				modifiedUser.setNotes(notes);
			}
	    	switchView(ema.LOGIN_VIEW);
	    	modifiedUser = null;
	    	if(currentUser.getRole() == UserGroup.EMPLOYER) { switchView(ema.EMPLOYER_VIEW); }
	    	else { switchView(ema.EMPLOYEE_VIEW); }
    	}
    	
    }
    
    public void addProfileInformation(String fname, String lname, String uname, UserGroup uG, Shift[] shifts, int cId, String notes, double salary) {
    	ModifyView mv = (ModifyView) views.getComponents()[ema.MODIFY_VIEW_INDEX];
    	if(ema.existsUser(uname)) {
    		mv.showErrorMessage("Username already exists.", true);
    	}
    	else if(uname == null || uname.equals("")) {
    		mv.showErrorMessage("Credentials cannot be empty.", true);
    	}
    	else {
			User u = new User(fname, lname, uname, uG, shifts, cId);
			u.setNotes(notes);
			u.setSalary(salary);
			ema.getCompany(cId).assign(u);
			ema.addUser(u);
			switchView(ema.EMPLOYER_VIEW);
    	}
    }
    
    public void cancelUpdate() {
    	switchView(ema.LOGIN_VIEW);
    	modifiedUser = null;
    	if(currentUser.getRole() == UserGroup.EMPLOYER) { switchView(ema.EMPLOYER_VIEW); }
    	else { switchView(ema.EMPLOYEE_VIEW); }
    }
    
    public void cancelLogUpdate() {
    	switchView(ema.LOGIN_VIEW);
    	currentLog = null;
    	if(getCurrentUser().getRole() == UserGroup.EMPLOYER) {
    		switchView(ema.LOG_VIEW);
    	}
    	else {
    		switchView(ema.EMPLOYEE_VIEW);
    	}
    }
    
    public void deleteEmployee(User u) {
    	String s1 = u.getUsername();
    	String s2 = currentUser.getUsername();
    	ema.removeUser(u);
    	u = null;
    	if(!(currentUser.getRole() == UserGroup.EMPLOYER) || s1.equals(s2)) {
    		switchView(ema.LOGIN_VIEW);
    	} else {
    		switchView(ema.LOGIN_VIEW);
    		switchView(ema.EMPLOYER_VIEW);
    	}
    }
    
    public void deleteLog(Log l) {
    	Company c = ema.getCompany(l.getCID());
    	c.removeLog(l);
    	this.currentLog = null;
    	switchView(ema.LOGIN_VIEW);
		switchView(ema.LOG_VIEW);
    }
    
    public void gotoAddLog(Log l) {
    	if(l == null) {
    		this.currentLog = null;
    		switchView(ema.ADD_LOG_VIEW);
    	}
    	else {
    		this.currentLog = l;
    		switchView(ema.ADD_LOG_VIEW);
    	}
    }
    
    
    public void gotoViewLogs() {
    	this.currentLog = null;
    	switchView(ema.LOG_VIEW);
    }
    
    public void gotoEmployerHome() {
    	this.currentLog = null;
    	switchView(ema.EMPLOYER_VIEW);
    }
    
    public void addLog(Date start, Date stop, String desc, boolean ver) {
    	AddLogView lv = (AddLogView) views.getComponents()[ema.ADD_LOG_VIEW_INDEX];
    	
    	if(getCurrentUser() != null) {
	    	Log l = new Log(getCurrentUser(), start, stop, desc);
	    	getCurrentCompany().addLog(l);
	    	this.currentLog = null;
	    	
	    	if(getCurrentUser().getRole() == UserGroup.EMPLOYER) {
	    		switchView(ema.LOG_VIEW);
	    	}
	    	else {
	    		switchView(ema.EMPLOYEE_VIEW);
	    	}
	    	
    	}
    	else { lv.showErrorMessage("Invalid user", true); }
    }
    
    public void verifyLog(Log l) {
    	l.toggleVerify();
    	if(getCurrentUser().getRole() == UserGroup.EMPLOYER) {
    		switchView(ema.LOG_VIEW);
    	}
    	else {
    		switchView(ema.EMPLOYEE_VIEW);
    	}
    }
    
    public void logout() {
    	this.currentUser = null;
    	this.currentCompany = null;
    	this.modifiedUser = null;
    	switchView(ema.LOGIN_VIEW);
        
    }
}
