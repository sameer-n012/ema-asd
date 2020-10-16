package org.ucvts.ema.app;
import java.awt.CardLayout;
import java.awt.Container;
import java.math.BigDecimal;

import org.ucvts.ema.EMA;
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
				   lv.showErrorMessage("Successful Login", true); //TODO delete later
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

    public void logout() {
        
    }
}
