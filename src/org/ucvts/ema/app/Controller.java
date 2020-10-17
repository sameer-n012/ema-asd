package org.ucvts.ema.app;
import java.awt.CardLayout;
import java.awt.Container;
import java.math.BigDecimal;

import org.ucvts.ema.EMA;
import org.ucvts.ema.model.Company;
import org.ucvts.ema.model.User;
import org.ucvts.ema.model.UserGroup;
import org.ucvts.ema.views.EmployeeView;
import org.ucvts.ema.views.EmployerView;
import org.ucvts.ema.views.LoginView;
import org.ucvts.ema.views.ModifyView;


public class Controller {

    private Container views;
    
    private User currentUser;
    private Company currentCompany;
    
    private EMA ema = null;

    public Controller(Container views) {
        this.views = views;
        this.currentUser = null;
        ema = EMA.getInstance();
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public Company getCurrentCompany() {
    	return currentCompany;
    }

    public void switchView(String view) {
    	
        ((CardLayout) views.getLayout()).show(views, view);
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
				   lv.showErrorMessage("Successful login", true); //TODO delete later
				   //TODO uncomment when employer and employee main views finished
				   if(u.getRole() == UserGroup.EMPLOYER) { switchView(ema.EMPLOYER_VIEW); }
				   //else if(u.getRole() == UserGroup.EMPLOYEE) { switchView(ema.EMPLOYEE_VIEW); }
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
 		   else {
 			   Company c = new Company(companyName);
 			   ema.addCompany(c);
 			   User u = c.addEmployer(username, password, "First", "Last");
 			   ema.addUser(u);
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

    public void logout() {
    	this.currentUser = null;
    	this.currentCompany = null;
    	switchView(ema.LOGIN_VIEW);
        //TODO need to add more stuff
    }
}
