package org.ucvts.ema.app;
import java.awt.CardLayout;
import java.awt.Container;
import java.math.BigDecimal;

import org.ucvts.ema.EMA;
import org.ucvts.ema.model.Company;
import org.ucvts.ema.model.Shift;
import org.ucvts.ema.model.User;
import org.ucvts.ema.model.UserGroup;
import org.ucvts.ema.views.EmployeeView;
import org.ucvts.ema.views.EmployerView;
import org.ucvts.ema.views.LoginView;
import org.ucvts.ema.views.ModifyView;


public class Controller {

    private Container views;
    
    private User currentUser;
    private User modifiedUser;
    private Company currentCompany;
    
    private EMA ema = null;

    public Controller(Container views) {
        this.views = views;
        this.currentUser = null;
        this.currentCompany = null;
        this.modifiedUser = null;
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

    public void switchView(String view) {
    	
        
        if(view.equals("LOGIN_VIEW")) { 
        	LoginView lv = (LoginView) views.getComponents()[ema.LOGIN_VIEW_INDEX];
        	lv.updateCard(); 
    	}
        if(view.equals("EMPLOYEE_VIEW")) { 
        	EmployeeView ev = (EmployeeView) views.getComponents()[ema.EMPLOYEE_VIEW_INDEX];
        	ev.updateCard(); 
    	}
        if(view.equals("EMPLOYER_VIEW")) { 
        	EmployerView ev = (EmployerView) views.getComponents()[ema.EMPLOYER_VIEW_INDEX];
        	ev.updateCard(); 
    	}
        if(view.equals("MODIFY_VIEW")) { 
        	ModifyView ev = (ModifyView) views.getComponents()[ema.MODIFY_VIEW_INDEX];
        	ev.updateCard(); 
    	}
       
        ((CardLayout) views.getLayout()).show(views, view);
        //TODO when switching views do it here
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
				   lv.showErrorMessage("Successful login", true); //TODO delete later
				   //TODO uncomment when employer and employee main views finished
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
 			   //TODO delete these later (only for testing)
 			   ema.addUser(c.assign("a", "A", "a"));
 			   ema.addUser(c.assign("b", "B", "b"));
 			   ema.addUser(c.assign("c", "C", "c"));
 			   this.currentCompany = c;
 			   lv.showErrorMessage("Successful creation", true);
 			   switchView(ema.EMPLOYER_VIEW);
 		   }
 	   }
 	   catch(Exception e) { e.printStackTrace(); }
    }

    public void modifyAddEmployee(User u) {
    	this.modifiedUser = u;
    	switchView(ema.MODIFY_VIEW);
    }
    
    public void updateProfileInformation(boolean resetPass, Shift[] shifts, double salary) {

		if(currentUser.getRole() == UserGroup.EMPLOYER) {
			if(resetPass == true) { modifiedUser.resetPassword(); }
			modifiedUser.setShifts(shifts);
			modifiedUser.setSalary(salary);
			modifiedUser = null;
			switchView(ema.LOGIN_VIEW);
    		switchView(ema.EMPLOYER_VIEW);
			
		}
		
    }
    
    public void updateProfileInformation(String fname, String lname, String password) {
    	if(currentUser == modifiedUser) { 
			modifiedUser.setPasswordHash(password);
			modifiedUser.setFName(fname);
			modifiedUser.setLName(lname);
		}
    	switchView(ema.LOGIN_VIEW);
    	modifiedUser = null;
    	if(currentUser.getRole() == UserGroup.EMPLOYER) { switchView(ema.EMPLOYER_VIEW); }
    	else { switchView(ema.EMPLOYEE_VIEW); }
    	
    }
    
    
    public void cancelUpdate() {
    	switchView(ema.LOGIN_VIEW);
    	modifiedUser = null;
    	if(currentUser.getRole() == UserGroup.EMPLOYER) { switchView(ema.EMPLOYER_VIEW); }
    	else { switchView(ema.EMPLOYEE_VIEW); }
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
    
    public void logout() {
    	this.currentUser = null;
    	this.currentCompany = null;
    	switchView(ema.LOGIN_VIEW);
        //TODO need to add more stuff probably
    }
}
