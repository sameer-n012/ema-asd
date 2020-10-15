package ems.app;
import java.awt.CardLayout;
import java.awt.Container;
import java.math.BigDecimal;


public class Controller {

    private Container views;
    private User activeUser;

    public Controller(Container views) {
        this.views = views;
        this.activeUser = null;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void switchTo(String view) {
        ((CardLayout) views.getLayout()).show(views, view);
    }

    
    public void login(String accountNumber, char[] pin) {
	   
    }

    public void logout() {
        
    }
}
