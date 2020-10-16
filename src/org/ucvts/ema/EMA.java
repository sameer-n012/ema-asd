package org.ucvts.ema;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
	
	public static final int LOGIN_VIEW_INDEX = 0;
	public static final int EMPLOYEE_VIEW_INDEX = 1;
	public static final int EMPLOYER_VIEW_INDEX = 2;
	public static final int MODIFY_VIEW_INDEX = 3;
	public static final String LOGIN_VIEW = "LOGIN_VIEW";
	public static final String EMPLOYEE_VIEW = "EMPLOYEE_VIEW";
	public static final String EMPLOYER_VIEW = "EMPLOYER_VIEW";
	public static final String MODIFY_VIEW = "MODIFY_VIEW";
	
	private Controller controller = null;
	
	private static EMA instance = null;
	
	
	public static EMA getInstance() {
		if(instance==null) { instance = new EMA(); }
		return instance;
	}
	
	public Controller getController() {
		return controller;
	}
	
	
	public static void main(String[] args) { //TODO
		
		userDirectory = new HashMap<String, User>();
		companyDirectory = new HashMap<Integer, Company>();
		
		Company c = new Company("First Inc.");
		companyDirectory.put(c.getId(), c);
		
		
		userDirectory.put("sn", c.addEmployer("sn", "default", "sameer", "narendran"));
		userDirectory.put("jd", c.assign("jd", "john", "doe"));
				
		SwingUtilities.invokeLater(new Runnable() {
			
            public void run() {
                try { EMA.getInstance().initialize(); } 
                catch (Exception e) { e.printStackTrace(); }
            }
            
        });
		
		
	}
	
    private void initialize() {
    	JPanel views = new JPanel(new CardLayout());
        controller = new Controller(views);

        // add child views to the parent container

        views.add(new LoginView(controller), LOGIN_VIEW);
        //views.add(new EmployeeView(controller), EMPLOYEE_VIEW);
        //views.add(new EmployerView(controller), EMPLOYER_VIEW);
        //views.add(new ModifyView(controller), MODIFY_VIEW);

        // configure the application frame

        this.add(views);
        this.setBounds(100, 100, 600, 600);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
	
	public boolean existsUser(String s) { //TODO
		return userDirectory.get(s)==null ? false : true; //No duplicates: returns false
	}
	
	public boolean existsCompany(String s) { //TODO
		for(Company val : companyDirectory.values()) {
			if(val.getName().equalsIgnoreCase(s)) { return true; }
		}
		return false; //No duplicates: returns false
	}
	
	public Company getCompany(Integer i) {
		return companyDirectory.get(i);
	}
	
	public User getUser(String s) {
		return userDirectory.get(s);
	}
	
	public void addCompany(Company c) {
		companyDirectory.put(c.getId(), c);
	}
	
	public void addUser(User u) {
		userDirectory.put(u.getUsername(), u);
	}
	
	public static void printMap(Map mp) {
	    Iterator it = mp.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	    }
	}

}
