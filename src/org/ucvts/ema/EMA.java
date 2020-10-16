package org.ucvts.ema;

import java.awt.CardLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.ucvts.ema.app.Controller;
import org.ucvts.ema.model.Company;
import org.ucvts.ema.model.User;
import org.ucvts.ema.views.LoginView;



@SuppressWarnings("serial")
public class EMA extends JFrame{
	
	public static HashMap<String, User> userDirectory = null;
	public static HashMap<Integer, Company> companyDirectory = null;
	
	public static void main(String[] args) { //TODO
		
		userDirectory = new HashMap<String, User>();
		companyDirectory = new HashMap<Integer, Company>();
				
		SwingUtilities.invokeLater(new Runnable() {
			
            public void run() {
                try { new EMA().initialize(); } 
                catch (Exception e) { e.printStackTrace(); }
            }
            
        });
		
		
	}
	
    private void initialize() {
    	JPanel views = new JPanel(new CardLayout());
        Controller controller = new Controller(views);

        // add child views to the parent container

        views.add(new LoginView(controller), "Login View");
        //views.add(new TransactionView(controller), TRANSACTION_VIEW);

        // configure the application frame

        this.add(views);
        this.setBounds(100, 100, 500, 500);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
	
	public static boolean checkUserDuplicates(String s) { //TODO
		return userDirectory.get(s)==null ? false : true; //No duplicates: returns false
	}
	
	public static boolean checkCompanyDuplicates(String s) { //TODO
		return companyDirectory.get(s)==null ? false : true; //No duplicates: returns false
	}
	
	public Company getCompany(Integer i) {
		return companyDirectory.get(i);
	}
	
	public User getUser(String s) {
		return userDirectory.get(s);
	}
	
	public void addCompany(Integer i, Company c) {
		companyDirectory.put(i, c);
	}
	
	public void addUser(String s, User u) {
		userDirectory.put(s, u);
	}

}
