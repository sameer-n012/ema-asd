package org.ucvts.ema.app;
import java.awt.CardLayout;
import java.awt.Container;
import java.math.BigDecimal;

import org.ucvts.ema.EMA;
import org.ucvts.ema.model.Company;
import org.ucvts.ema.model.User;
import org.ucvts.ema.views.LoginView;


public class Controller {

    private Container views;
    private User activeUser;
    
    private EMA ema = null;

    public Controller(Container views) {
        this.views = views;
        this.activeUser = null;
        ema = EMA.getInstance();
    }
    
    public User getActiveUser() {
        return activeUser;
    }

    public void switchTo(String view) {
        ((CardLayout) views.getLayout()).show(views, view);
    }

    
    public void login(String username, String password) {
	   LoginView lv = (LoginView) views.getComponents()[EMA.LOGIN_VIEW_INDEX];
	   
	   try {
		   if(ema.existsUser(username)) {
			   User u = ema.getUser(username);
			   if(Password.checkPassword(password, u.getPasswordHash(), u.getSalt())) {
				   ema.currentUser = u;
				   lv.showErrorMessage("Successful login", true); //TODO delete later
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
    	LoginView lv = (LoginView) views.getComponents()[EMA.LOGIN_VIEW_INDEX];
    	
    	try {
 		   if(ema.existsUser(username)) {
 			   lv.showErrorMessage("Username already exists.", true);
 		   }
 		   else if(ema.existsCompany(companyName)){
 				lv.showErrorMessage("Company Name already exists.", true);
 		   }
 		   else {
 			   Company c = new Company(companyName);
 			   ema.addCompany(c);
 			   User u = c.addEmployer(username, password, "First", "Last");
 			   ema.addUser(u);
 			   lv.showErrorMessage("Successful creation", true);
 		   }
 	   }
 	   catch(Exception e) { e.printStackTrace(); }
    }

    public void logout() {
        
    }
}
